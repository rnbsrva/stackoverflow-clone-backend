package com.akerke.stackoverflow.domain.entity;

import com.akerke.stackoverflow.common.constants.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Document(collection = "user_")
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements UserDetails {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;

    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String password;
    @DBRef(lazy = true)
    private List<Question> questions;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    private String resetPasswordToken;

    public User(String name, String surname, String email, String password, List<Question> questions, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.questions = questions;
        this.role = role;
        this.resetPasswordToken = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var l = List.of(new SimpleGrantedAuthority(role.name()));
        System.out.println(l.get(0).getAuthority());
        return l;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (!(obj instanceof User otherCard))
            return false;

        if (!Objects.equals(otherCard.name, this.name))       return false;
        if (!Objects.equals(otherCard.email, this.email))     return false;
        if (!otherCard.role.equals(this.role)) return false;
        if (!otherCard.surname.equals(this.surname))   return false;
        return otherCard.password.equals(this.password);
    }
}

package com.akerke.stackoverflow.domain.mapper;

import com.akerke.stackoverflow.common.constants.Role;
import com.akerke.stackoverflow.domain.dto.UserDTO;
import com.akerke.stackoverflow.domain.dto.UserUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Question;
import com.akerke.stackoverflow.domain.entity.User;
import org.mapstruct.*;

import java.util.ArrayList;

@Mapper(imports = {Role.class, Question.class, ArrayList.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    @Mapping(target = "role", expression = "java(Role.ROLE_USER)")
    @Mapping(target = "questions", expression = "java(new ArrayList<Question>())")
    @Mapping(target = "password", expression = "java(password)")
    User toModel (UserDTO userDTO, String password);

    UserDTO toDTO (User user);

    UserUpdateDTO toUserUpdateDTO(User user);

    @Mapping(target = "id", ignore = true)
    void update (UserUpdateDTO userUpdateDTO, @MappingTarget User user);


}

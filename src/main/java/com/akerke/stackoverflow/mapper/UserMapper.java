package com.akerke.stackoverflow.mapper;

import com.akerke.stackoverflow.constants.Role;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.User;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Map;

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

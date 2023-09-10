package com.akerke.stackoverflow.mapper;

import com.akerke.stackoverflow.dto.TagDTO;
import com.akerke.stackoverflow.dto.TagUpdateDTO;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.Tag;
import org.mapstruct.*;

import java.util.ArrayList;

@Mapper(imports = {Question.class, ArrayList.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TagMapper {

    @Mapping(target = "questions", expression = "java(new ArrayList<Question>())")
    Tag toModel (TagDTO tagDTO);

    TagDTO toDTO (Tag tag);

    @Mapping(target = "id", ignore = true)
    void update (TagUpdateDTO tagUpdateDTO, @MappingTarget Tag tag);

}

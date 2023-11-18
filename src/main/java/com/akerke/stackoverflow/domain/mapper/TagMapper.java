package com.akerke.stackoverflow.domain.mapper;

import com.akerke.stackoverflow.domain.dto.TagDTO;
import com.akerke.stackoverflow.domain.dto.TagUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Question;
import com.akerke.stackoverflow.domain.entity.Tag;
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

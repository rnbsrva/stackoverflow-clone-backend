package com.akerke.stackoverflow.domain.service;

import com.akerke.stackoverflow.domain.dto.TagDTO;
import com.akerke.stackoverflow.domain.dto.TagUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Tag;

import java.util.List;

public interface TagService {

    Tag save (TagDTO tagDTO);

    Tag getById(Long id);

    List<Tag> getAll();

    void update(TagUpdateDTO tagUpdateDTO, Long id);

    void delete(Long id);

}

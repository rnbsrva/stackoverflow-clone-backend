package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.TagDTO;
import com.akerke.stackoverflow.dto.TagUpdateDTO;
import com.akerke.stackoverflow.entity.Tag;

import java.util.List;

public interface TagService {

    Tag save (TagDTO tagDTO);

    Tag getById(Long id);

    List<Tag> getAll();

    void update(TagUpdateDTO tagUpdateDTO, Long id);

    void delete(Long id);

}

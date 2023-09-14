package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.dto.TagDTO;
import com.akerke.stackoverflow.dto.TagUpdateDTO;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mapper.TagMapper;
import com.akerke.stackoverflow.entity.Tag;
import com.akerke.stackoverflow.repository.TagRepository;
import com.akerke.stackoverflow.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Tag save(TagDTO tagDTO) {
        return tagRepository.save(tagMapper.toModel(tagDTO));
    }

    @Override
    public Tag getById(Long id) {
        return tagRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Tag.class, id));
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public void update(TagUpdateDTO tagUpdateDTO, Long id) {
        Tag tag = this.getById(id);
        tagMapper.update(tagUpdateDTO, tag);
        tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        Tag tag = this.getById(id);
        tagRepository.delete(tag);
    }
}

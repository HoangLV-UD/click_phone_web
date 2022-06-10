package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.entity.ColorEntity;
import com.example.word_phone_web.repo.ColorRepo;
import com.example.word_phone_web.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepo repo;

    @Override
    public String findById(Long id) {
        ColorEntity entity = repo.findByDeleteFlagIsFalseAndId(id);
        if(entity != null){
            return entity.getValueColor();
        }
        return null;
    }
}

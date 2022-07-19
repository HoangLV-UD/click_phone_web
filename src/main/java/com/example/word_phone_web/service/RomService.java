package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.respone.rom.RomRespone;

import java.util.List;

public interface RomService {
    List<RomRespone> findByProductId(Long id);
}

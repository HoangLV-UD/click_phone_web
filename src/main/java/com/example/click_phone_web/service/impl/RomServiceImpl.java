package com.example.click_phone_web.service.impl;

import com.example.click_phone_web.dto.respone.rom.RomRespone;
import com.example.click_phone_web.entity.RomEntity;
import com.example.click_phone_web.repo.RomRepo;
import com.example.click_phone_web.service.ProductPropertyService;
import com.example.click_phone_web.service.RomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RomServiceImpl implements RomService {
    private final RomRepo repo;

    private final ProductPropertyService productPropertyService;

    @Override
    public List<RomRespone> findByProductId(Long id) {
        List<RomRespone> list = new ArrayList<>();
        List<RomEntity> romEntities = repo.findByProductId(id);
        romEntities.forEach(o -> {
            if (productPropertyService.findByRomId(o.getId()) != null && productPropertyService.findByRomId(o.getId()).size() > 0){
                list.add(RomRespone.builder()
                        .name(o.getName())
                        .id(String.valueOf(o.getId()))
                        .productPropertyRespones(productPropertyService.findByRomId(o.getId()))
                        .build());
            }

        });

        return list;
    }

    private RomRespone mapToEntity(RomEntity entity){
        RomRespone respone = new RomRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        return respone;
    }
}

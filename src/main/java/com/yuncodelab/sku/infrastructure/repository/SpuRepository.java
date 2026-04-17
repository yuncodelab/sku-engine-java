package com.yuncodelab.sku.infrastructure.repository;

import com.yuncodelab.sku.infrastructure.entity.SpuEntity;
import com.yuncodelab.sku.infrastructure.mapper.SpuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpuRepository {

    /**
     * 根据 ID 获取 SPU 详情
     */
    private final SpuMapper spuMapper;

    public SpuEntity getById(Long id) {
        return spuMapper.selectById(id);
    }
}

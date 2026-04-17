package com.yuncodelab.sku.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuncodelab.sku.infrastructure.entity.SkuEntity;
import com.yuncodelab.sku.infrastructure.entity.SkuSpecRelEntity;
import com.yuncodelab.sku.infrastructure.mapper.SkuMapper;
import com.yuncodelab.sku.infrastructure.mapper.SkuSpecRelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkuRepository {

    private final SkuMapper skuMapper;
    private final SkuSpecRelMapper skuSpecRelMapper;

    /**
     * 根据 SPU ID 获取所有 SKU
     */
    public List<SkuEntity> findBySpuId(Long spuId) {
        return skuMapper.selectList(new LambdaQueryWrapper<SkuEntity>()
            .eq(SkuEntity::getSpuId, spuId));
    }

    /**
     * 批量查询 SKU 的规格关联属性
     *
     * @param skuIds SKU ID 集合
     * @return 关联关系列表，若输入为空则返回空集合
     */
    public List<SkuSpecRelEntity> findRelsBySkuIds(List<Long> skuIds) {
        if (CollectionUtils.isEmpty(skuIds)) return Collections.emptyList();

        return skuSpecRelMapper.selectList(new LambdaQueryWrapper<SkuSpecRelEntity>()
            .in(SkuSpecRelEntity::getSkuId, skuIds));
    }
}

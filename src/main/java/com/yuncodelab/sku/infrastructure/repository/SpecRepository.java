package com.yuncodelab.sku.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuncodelab.sku.infrastructure.entity.SpecKeyEntity;
import com.yuncodelab.sku.infrastructure.entity.SpecValueEntity;
import com.yuncodelab.sku.infrastructure.mapper.SpecKeyMapper;
import com.yuncodelab.sku.infrastructure.mapper.SpecValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpecRepository {

    private final SpecKeyMapper specKeyMapper;
    private final SpecValueMapper specValueMapper;

    /**
     * 批量获取规格键定义（如：颜色、大小）
     */
    public List<SpecKeyEntity> findKeysByIds(List<Long> specKeyIds) {
        if (CollectionUtils.isEmpty(specKeyIds)) {
            return Collections.emptyList();
        }
        return specKeyMapper.selectList(new LambdaQueryWrapper<SpecKeyEntity>()
            .in(SpecKeyEntity::getId, specKeyIds));
    }

    /**
     * 根据规格ID列表批量查询其下所有的规格值
     */
    public List<SpecValueEntity> findValuesBySpecIds(List<Long> specKeyIds) {
        if (CollectionUtils.isEmpty(specKeyIds)) {
            return Collections.emptyList();
        }
        return specValueMapper.selectList(new LambdaQueryWrapper<SpecValueEntity>()
            .in(SpecValueEntity::getSpecId, specKeyIds));
    }
}

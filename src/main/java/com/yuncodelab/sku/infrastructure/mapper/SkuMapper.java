package com.yuncodelab.sku.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuncodelab.sku.infrastructure.entity.SkuEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkuMapper extends BaseMapper<SkuEntity> {
}

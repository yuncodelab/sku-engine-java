package com.yuncodelab.sku.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * SKU 核心表：存储价格、库存等库存单位信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_sku")
public class SkuEntity extends BaseEntity {
    private Long spuId;
    private BigDecimal price;
    private Integer stock;
}

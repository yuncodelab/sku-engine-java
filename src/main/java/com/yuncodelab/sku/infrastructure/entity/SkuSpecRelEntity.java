package com.yuncodelab.sku.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SKU 与 规格值 关联表（中间表）
 * 记录某个 SKU 到底是由哪些规格值组合而成的
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_sku_spec_rel")
public class SkuSpecRelEntity extends BaseEntity {
    private Long skuId;
    private Long specId;
    private Long specValueId;
}

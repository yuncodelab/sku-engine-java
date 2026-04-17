package com.yuncodelab.sku.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SPU 商品表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_spu")
public class SpuEntity extends BaseEntity {
    private String name;
    private Long defaultSkuId;
}

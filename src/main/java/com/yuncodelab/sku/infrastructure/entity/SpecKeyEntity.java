package com.yuncodelab.sku.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规格键表：定义规格维度
 * 例如：specCode="color", specName="颜色"
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_spec_key")
public class SpecKeyEntity extends BaseEntity {
    private String specCode;
    private String specName;
}

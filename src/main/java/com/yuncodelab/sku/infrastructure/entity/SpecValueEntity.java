package com.yuncodelab.sku.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规格值表：定义具体的规格选项
 * 例如：specId 关联“颜色”，valueName 为“珍珠白”
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_spec_value")
public class SpecValueEntity extends BaseEntity {
    private Long specId;     // 关联 t_spec_key.id
    private String valueCode;
    private String valueName;
}

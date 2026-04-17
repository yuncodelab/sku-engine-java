SET
FOREIGN_KEY_CHECKS = 0;

-- 1. 商品表
DROP TABLE IF EXISTS `t_spu`;
CREATE TABLE `t_spu`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `name`           varchar(100) NOT NULL COMMENT '商品名称',
    `default_sku_id` bigint(20) DEFAULT NULL COMMENT '默认展示的SKU ID',
    `create_time`    datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品主体表';

-- 2. 规格名表
DROP TABLE IF EXISTS `t_spec_key`;
CREATE TABLE `t_spec_key`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `spec_code` varchar(50) NOT NULL COMMENT '规格标识: style, color',
    `spec_name` varchar(50) NOT NULL COMMENT '规格名称: 款式, 颜色',
    `create_time`    datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_spec_code` (`spec_code`) -- 保证业务标识唯一
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格名表';

-- 3. 规格值表
DROP TABLE IF EXISTS `t_spec_value`;
CREATE TABLE `t_spec_value`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `spec_id`    bigint(20) NOT NULL COMMENT '关联 t_spec_key.id',
    `value_code` varchar(50) NOT NULL COMMENT '规格值标识: sport, black',
    `value_name` varchar(50) NOT NULL COMMENT '规格值名称: 运动款, 黑色',
    `create_time`    datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_value_code` (`spec_id`, `value_code`), -- 同一规格下标识唯一
    INDEX        `idx_spec_id` (`spec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格值表';

-- 4. SKU表
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE `t_sku`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `spu_id`      bigint(20) NOT NULL,
    `price`       decimal(10, 2) NOT NULL DEFAULT '0.00',
    `stock`       int(11) NOT NULL DEFAULT '0',
    `create_time` datetime                DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX         `idx_spu_id` (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU表';

-- 5. SKU与规格值关联表 (关联 ID)
DROP TABLE IF EXISTS `t_sku_spec_rel`;
CREATE TABLE `t_sku_spec_rel`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `sku_id`        bigint(20) NOT NULL,
    `spec_id`       bigint(20) NOT NULL COMMENT '关联 t_spec_key.id',
    `spec_value_id` bigint(20) NOT NULL COMMENT '关联 t_spec_value.id',
    `create_time`    datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sku_spec` (`sku_id`, `spec_id`),
    INDEX           `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU规格值关联表';

SET
FOREIGN_KEY_CHECKS = 1;
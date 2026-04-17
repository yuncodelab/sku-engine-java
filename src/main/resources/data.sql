-- 1. SPU
INSERT INTO `t_spu` (`id`, `name`, `default_sku_id`)
VALUES (1, '极客风格多功能运动衫', 1);

-- 2. Spec Key
INSERT INTO `t_spec_key` (`id`, `spec_code`, `spec_name`)
VALUES (1, 'style', '款式'),
       (2, 'color', '颜色'),
       (3, 'size', '尺码');

-- 3. Spec Value (关联 spec_id)
INSERT INTO `t_spec_value` (`id`, `spec_id`, `value_code`, `value_name`)
VALUES (1, 1, 'sport', '运动款'),
       (2, 1, 'casual', '休闲款'),
       (3, 2, 'black', '黑色'),
       (4, 2, 'white', '白色'),
       (5, 2, 'blue', '蓝色'),
       (6, 3, 'm', 'M'),
       (7, 3, 'l', 'L'),
       (8, 3, 'xl', 'XL');

-- 4. SKU
INSERT INTO `t_sku` (`id`, `spu_id`, `price`, `stock`)
VALUES (1, 1, 199.0, 12),
       (2, 1, 199.0, 0),
       (3, 1, 209.0, 5),
       (4, 1, 209.0, 8),
       (5, 1, 219.0, 6);

-- 5. SKU Spec Relation (全部使用自增 ID 关联)
INSERT INTO `t_sku_spec_rel` (`sku_id`, `spec_id`, `spec_value_id`)
VALUES
-- SKU 1: 1(style):1(sport), 2(color):3(black), 3(size):6(m)
(1, 1, 1),
(1, 2, 3),
(1, 3, 6),
-- SKU 2: 1(style):1(sport), 2(color):3(black), 3(size):7(l)
(2, 1, 1),
(2, 2, 3),
(2, 3, 7),
-- SKU 3: 1(style):1(sport), 2(color):4(white), 3(size):6(m)
(3, 1, 1),
(3, 2, 4),
(3, 3, 6),
-- SKU 4: 1(style):2(casual), 2(color):5(blue), 3(size):7(l)
(4, 1, 2),
(4, 2, 5),
(4, 3, 7),
-- SKU 5: 1(style):2(casual), 2(color):3(black), 3(size):8(xl)
(5, 1, 2),
(5, 2, 3),
(5, 3, 8);
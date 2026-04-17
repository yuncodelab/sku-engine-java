package com.yuncodelab.sku.api.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SpuDetailResponse {
    /**
     * 规格选择器列表（如：颜色、尺寸及其对应的选项值）
     */
    private List<SpecGroupResponse> specList;
    /**
     * SKU 详细数据列表（包含价格、库存及该 SKU 对应的规格路径）
     */
    private List<SkuItemResponse> skuList;
    /**
     * 默认选中的 SKU ID
     */
    private String defaultSkuId;

    @Data
    public static class SpecGroupResponse {
        private String specId;   // 对应数据库的 spec_code: style, color
        private String specName; // 对应数据库的 spec_name: 款式, 颜色
        private List<SpecValueResponse> values;
    }

    @Data
    public static class SpecValueResponse {
        private String id;   // 对应数据库的 value_code: sport, black
        private String name; // 对应数据库的 value_name: 运动款, 黑色
    }

    @Data
    public static class SkuItemResponse {
        private String skuId;
        private Double price;
        private Integer stock;
        /**
         * 动态规格键值对
         * key: spec_code (如 style)
         * value: value_code (如 sport)
         */
        private Map<String, String> specs;
    }
}

package com.yuncodelab.sku.service.bo;

import com.yuncodelab.sku.api.response.SpuDetailResponse;
import com.yuncodelab.sku.infrastructure.entity.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Slf4j
public class SpuDetailBO {
    private SpuEntity spu;
    private List<SkuEntity> skus;
    private List<SpecKeyEntity> specKeys;
    private List<SpecValueEntity> specValues;
    private List<SkuSpecRelEntity> rels;

    /**
     * 将领域对象聚合转换为前端需要的 Response 结构
     */
    public SpuDetailResponse transform() {
        SpuDetailResponse response = new SpuDetailResponse();
        response.setDefaultSkuId(spu.getDefaultSkuId() != null ? String.valueOf(spu.getDefaultSkuId()) : null);

        // 步骤一：组装左侧/上方规格选择器（规格项 -> 规格值列表）
        response.setSpecList(buildSpecList());

        // 步骤二：组装具体的 SKU 矩阵（SKU ID -> 价格、库存、规格路径）
        response.setSkuList(buildSkuList());

        return response;
    }

    /**
     * 构建规格项列表：将规格值按 Key 归类
     */
    private List<SpuDetailResponse.SpecGroupResponse> buildSpecList() {
        // 按 specId 分组，避免在循环中重复过滤 list
        Map<Long, List<SpecValueEntity>> valueMap = specValues.stream()
            .collect(Collectors.groupingBy(SpecValueEntity::getSpecId));

        return specKeys.stream().map(key -> {
            SpuDetailResponse.SpecGroupResponse group = new SpuDetailResponse.SpecGroupResponse();
            group.setSpecId(key.getSpecCode());   // 如: "color"
            group.setSpecName(key.getSpecName()); // 如: "颜色"

            List<SpuDetailResponse.SpecValueResponse> values = valueMap.getOrDefault(key.getId(), Collections.emptyList())
                .stream().map(v -> {
                    SpuDetailResponse.SpecValueResponse val = new SpuDetailResponse.SpecValueResponse();
                    val.setId(v.getValueCode());   // 如: "black"
                    val.setName(v.getValueName()); // 如: "黑色"
                    return val;
                }).collect(Collectors.toList());

            group.setValues(values);
            return group;
        }).collect(Collectors.toList());
    }

    /**
     * 构建 SKU 列表：匹配每个 SKU 的动态规格路径
     */
    private List<SpuDetailResponse.SkuItemResponse> buildSkuList() {
        // 预处理映射表，提高查找效率
        Map<Long, String> keyCodeMap = specKeys.stream()
            .collect(Collectors.toMap(SpecKeyEntity::getId, SpecKeyEntity::getSpecCode, (k1, k2) -> k1));
        Map<Long, String> valueCodeMap = specValues.stream()
            .collect(Collectors.toMap(SpecValueEntity::getId, SpecValueEntity::getValueCode, (v1, v2) -> v1));

        // 按 skuId 快速定位该 SKU 关联的所有规格键值
        Map<Long, List<SkuSpecRelEntity>> skuRelMap = rels.stream()
            .collect(Collectors.groupingBy(SkuSpecRelEntity::getSkuId));

        return skus.stream().map(sku -> {
            SpuDetailResponse.SkuItemResponse item = new SpuDetailResponse.SkuItemResponse();
            item.setSkuId(String.valueOf(sku.getId()));
            item.setPrice(sku.getPrice() != null ? sku.getPrice().doubleValue() : 0.0);
            item.setStock(sku.getStock());

            // 核心逻辑：组装 specs map，例如 {"color": "black", "size": "64G"}
            // 前端将根据这个 map 实现“点击不同规格切换 SKU 详情”
            Map<String, String> specMap = new HashMap<>();
            List<SkuSpecRelEntity> myRels = skuRelMap.getOrDefault(sku.getId(), Collections.emptyList());

            for (SkuSpecRelEntity rel : myRels) {
                String k = keyCodeMap.get(rel.getSpecId());
                String v = valueCodeMap.get(rel.getSpecValueId());
                if (k != null && v != null) {
                    specMap.put(k, v);
                } else {
                    log.warn("SKU规格关联丢失定义. skuId: {}, specId: {}, valueId: {}", sku.getId(), rel.getSpecId(), rel.getSpecValueId());
                }
            }
            item.setSpecs(specMap);
            return item;
        }).collect(Collectors.toList());
    }
}

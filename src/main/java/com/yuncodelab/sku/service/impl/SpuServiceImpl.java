package com.yuncodelab.sku.service.impl;

import com.yuncodelab.sku.api.response.SpuDetailResponse;
import com.yuncodelab.sku.common.BusinessException;
import com.yuncodelab.sku.infrastructure.entity.*;
import com.yuncodelab.sku.infrastructure.repository.SkuRepository;
import com.yuncodelab.sku.infrastructure.repository.SpecRepository;
import com.yuncodelab.sku.infrastructure.repository.SpuRepository;
import com.yuncodelab.sku.service.SpuService;
import com.yuncodelab.sku.service.bo.SpuDetailBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpuServiceImpl implements SpuService {

    private final SpuRepository spuRepository;
    private final SkuRepository skuRepository;
    private final SpecRepository specRepository;

    @Override
    public SpuDetailResponse getSpuDetail(Long spuId) {
        log.info("开始查询商品详情. SPU_ID: {}", spuId);

        // 1. 查询 SPU 基础信息
        SpuEntity spu = spuRepository.getById(spuId);
        if (spu == null) {
            log.warn("商品详情查询失败：SPU 不存在. SPU_ID: {}", spuId);
            throw new BusinessException("商品不存在"); // 使用之前定义的业务异常
        }

        // 2. 批量拉取所有相关数据（减少 DB 交互次数）
        List<SkuEntity> skus = skuRepository.findBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)) {
            log.warn("商品配置异常：SPU 下无有效 SKU. SPU_ID: {}", spuId);
        }

        List<Long> skuIds = skus.stream().map(SkuEntity::getId).toList();

        // 获取 SKU 关联的规格映射关系
        List<SkuSpecRelEntity> rels = skuRepository.findRelsBySkuIds(skuIds);

        // 提取所有涉及到的规格键 ID
        List<Long> specKeyIds = rels.stream()
            .map(SkuSpecRelEntity::getSpecId)
            .distinct()
            .toList();

        // 批量查询规格定义和规格值
        List<SpecKeyEntity> keys = specRepository.findKeysByIds(specKeyIds);
        List<SpecValueEntity> values = specRepository.findValuesBySpecIds(specKeyIds);

        log.info("数据拉取完成. SKU数量: {}, 规格项数量: {}", skus.size(), keys.size());

        // 3. 构建 BO 对象执行内存级转换逻辑
        SpuDetailBO bo = new SpuDetailBO();
        bo.setSpu(spu);
        bo.setSkus(skus);
        bo.setSpecKeys(keys);
        bo.setSpecValues(values);
        bo.setRels(rels);

        try {
            SpuDetailResponse response = bo.transform();
            log.info("商品详情组装成功. SPU_ID: {}, 默认SKU: {}", spuId, response.getDefaultSkuId());
            return response;
        } catch (Exception e) {
            log.error("商品详情转换异常. SPU_ID: {}", spuId, e);
            throw new BusinessException("商品数据解析失败");
        }
    }
}
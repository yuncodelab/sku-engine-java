package com.yuncodelab.sku.service;

import com.yuncodelab.sku.api.response.SpuDetailResponse;

public interface SpuService {

    /**
     * 根据 SPU ID 聚合查询规格列表和 SKU 矩阵
     */
    SpuDetailResponse getSpuDetail(Long spuId);
}

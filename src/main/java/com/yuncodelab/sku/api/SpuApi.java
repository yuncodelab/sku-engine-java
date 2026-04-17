package com.yuncodelab.sku.api;

import com.yuncodelab.sku.api.response.SpuDetailResponse;
import com.yuncodelab.sku.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SpuApi {
    /**
     * 获取商品详情（包含规格列表与SKU列表）
     *
     * @param spuId SPU唯一标识
     */
    @GetMapping("/v1/spu/{spuId}/detail")
    Result<SpuDetailResponse> getSpuDetail(@PathVariable("spuId") Long spuId);
}

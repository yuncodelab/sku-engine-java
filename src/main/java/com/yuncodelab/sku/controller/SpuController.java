package com.yuncodelab.sku.controller;

import com.yuncodelab.sku.api.SpuApi;
import com.yuncodelab.sku.api.response.SpuDetailResponse;
import com.yuncodelab.sku.common.Result;
import com.yuncodelab.sku.service.SpuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpuController implements SpuApi {

    private final SpuService spuService;

    @Override
    public Result<SpuDetailResponse> getSpuDetail(@PathVariable("spuId") Long spuId) {
        log.info("REST request to get SPU detail. spuId: {}", spuId);

        SpuDetailResponse response = spuService.getSpuDetail(spuId);

        log.info("Successfully retrieved SPU detail for spuId: {}", spuId);
        return Result.success(response);
    }
}

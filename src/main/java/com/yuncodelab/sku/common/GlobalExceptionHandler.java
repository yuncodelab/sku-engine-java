package com.yuncodelab.sku.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     * 业务异常通常是预见内的（如库存不足、商品不存在），使用 WARN 级别，无需堆栈轨迹
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.warn("业务逻辑异常: {}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 捕获系统级未知异常
     * 必须使用 ERROR 级别并打印堆栈，这是系统稳定性监控的核心
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        // 关键日志：记录堆栈信息，方便定位代码行数
        log.error("系统运行不可预知异常: ", e);
        return Result.error("服务器内部错误，请稍后再试");
    }
}

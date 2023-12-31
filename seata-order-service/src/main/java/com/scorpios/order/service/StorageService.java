package com.scorpios.order.service;


import com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration;
import com.scorpios.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service",configuration = SeataFeignClientAutoConfiguration.class)
public interface StorageService{

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
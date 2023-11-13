package com.scorpios.order.service;

import com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration;
import com.scorpios.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service",configuration = SeataFeignClientAutoConfiguration.class)
public interface AccountService{

    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}


package com.scorpios.storage.service.impl;

import com.scorpios.storage.dao.StorageDao;
import com.scorpios.storage.service.StorageService;
import io.seata.spring.annotation.GlobalLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class StorageServiceImpl implements StorageService {


    @Resource
    private StorageDao storageDao;

    /**
     * 全局锁 + select for update
     * 1.加了 select for update语句会在执行 SQL 前检查全局锁是否存在，只有当全局锁完成之后，才能继续执行 SQL，这样就防止了脏读。
     * 2.加了 select for update语句，则会在 update 前检查全局锁是否存在，只有当全局锁释放之后，其他事务才能开始执行 update 操作。防止了脏写。
     *   select for update 也有个好处，就是可以重试。
     */
    // 扣减库存
    @Override
    @GlobalLock
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}

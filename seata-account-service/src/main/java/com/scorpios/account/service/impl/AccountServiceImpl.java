package com.scorpios.account.service.impl;

import com.scorpios.account.dao.AccountDao;
import com.scorpios.account.service.AccountService;
import io.seata.spring.annotation.GlobalLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户业务实现类
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {



    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     * 全局锁 + select for update
     * 1.加了 select for update语句会在执行 SQL 前检查全局锁是否存在，只有当全局锁完成之后，才能继续执行 SQL，这样就防止了脏读。
     * 2.加了 select for update语句，则会在 update 前检查全局锁是否存在，只有当全局锁释放之后，其他事务才能开始执行 update 操作。防止了脏写。
     *       select for update 也有个好处，就是可以重试。
     */
    @Override
    @GlobalLock
    public void decrease(Long userId, BigDecimal money) {

        log.info("------->account-service中扣减账户余额开始");
       /*try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        accountDao.decrease(userId,money);
        log.info("------->account-service中扣减账户余额结束");
    }
}

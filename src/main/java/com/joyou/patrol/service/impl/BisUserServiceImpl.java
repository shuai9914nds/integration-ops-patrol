package com.joyou.patrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joyou.patrol.entity.BisUser;
import com.joyou.patrol.mapper.BisUserMapper;
import com.joyou.patrol.service.IBisUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2022-12-23
 */
@Service
public class BisUserServiceImpl extends ServiceImpl<BisUserMapper, BisUser> implements IBisUserService {

}

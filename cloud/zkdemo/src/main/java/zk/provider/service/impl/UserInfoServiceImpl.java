package zk.provider.service.impl;/**
 * Created by husky on 2018/11/1.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zk.provider.domain.UserInfo;
import zk.provider.mapper.UserInfoMapper;
import zk.provider.service.UserInfoService;

/**
 * @author zhangkai
 * @create 2018-11-01 16:47
 **/
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo getUserById(Long id) {
        return userInfoMapper.getUserById(id);
    }
}

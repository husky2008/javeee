package zk.provider.service;/**
 * Created by husky on 2018/11/1.
 */

import zk.provider.domain.UserInfo;

/**
 * @author zhangkai
 * @create 2018-11-01 16:40
 **/
public interface UserInfoService {

    UserInfo getUserById(Long id);
}

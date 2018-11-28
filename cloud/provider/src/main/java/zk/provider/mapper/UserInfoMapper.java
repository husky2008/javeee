package zk.provider.mapper;/**
 * Created by husky on 2018/11/1.
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import zk.provider.domain.UserInfo;

/**
 * @author zhangkai
 * @create 2018-11-01 16:40
 **/

@Mapper
@Repository("userInfoMapper")
public interface UserInfoMapper {
    @Select("select * from tbl_user_info where id = #{id}")
    UserInfo getUserById(@Param("id") Long id);
}

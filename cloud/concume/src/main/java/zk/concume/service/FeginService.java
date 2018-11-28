package zk.concume.service;/**
 * Created by husky on 2018/11/11.
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangkai
 * @create 2018-11-11 18:58
 **/


@FeignClient("Provider")
public interface FeginService {

    @GetMapping("user/info/{id}")
    String getInfo(@PathVariable("id") Long id);
}

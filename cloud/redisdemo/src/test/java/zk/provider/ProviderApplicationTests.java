package zk.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  App.class)
public class ProviderApplicationTests {


	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() {
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		System.out.println(stringStringValueOperations.get("name"));

	}

}

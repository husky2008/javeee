package zk.concume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//开启feign 功能
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class DemoFeginApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoFeginApplication.class, args);
    }
}

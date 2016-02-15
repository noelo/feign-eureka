package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableCircuitBreaker
public class HelloClientApplication {
    @Autowired
    HelloClient client;

    @RequestMapping("/")
    public String hello() {
        return "--->"+client.hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloClientApplication.class, args);
//        new SpringApplicationBuilder(HelloClientApplication.class).web(true).run(args);
    }

    @FeignClient("HelloServer")
    @Component
    interface HelloClient {
        @RequestMapping(value = "/", method = GET)
        String hello();
    }

}


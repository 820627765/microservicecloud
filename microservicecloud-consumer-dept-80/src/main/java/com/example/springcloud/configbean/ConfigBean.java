package com.example.springcloud.configbean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 如果显示在容器中指定了Ribbon 的负载均衡策略，则就使用指定的，否则使用默认的
     * @return
     */
    @Bean
    public IRule iRule(){
        return new RoundRobinRule();
    }
}

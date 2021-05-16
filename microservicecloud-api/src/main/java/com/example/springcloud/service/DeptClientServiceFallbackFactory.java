package com.example.springcloud.service;

import com.example.springcloud.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  //这个注解必须要添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        //这里将原来熔断 的Fallback 处理逻辑全部 拿到这里来处理，下面的每一个方法都是对应服务方法发生异常后的 处理逻辑
        return new DeptClientService() {
            @Override
            public Dept get(long id) {
                return new Dept().setDeptno(id).setDname("没有对应的信息，此刻服务已经关闭");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}

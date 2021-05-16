package com.example.springcloud.controller;

import com.example.springcloud.entity.Dept;
import com.example.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
	@Autowired
	private DeptService service;
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept)
	{
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id)
	{
		return service.get(id);
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}

	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery(){
		//通过 discoveryClient 去 eureka 查看有哪些微服务，返回微服务列表
		List<String> list = discoveryClient.getServices();
		System.out.println("**********" + list);

		// 通过 discoveryClient 的getInstances 方法 通过服务名称查询 是这个服务名称的 微服务实例列表（一个服务的集群）。
		List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			//通过微服务实例访问微服务的Id，服务的主机，服务的端口号，服务的uri 等信息
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.discoveryClient;
	}
}

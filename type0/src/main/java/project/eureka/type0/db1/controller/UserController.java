package project.eureka.type0.db1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import project.eureka.type0.bean.User;
import project.eureka.type0.db1.service.UserService;

/**
 * controller层会调用service层，完成对请求的处理，并且做出响应。类似于servlet
 * 另外，如果是返回对象，在响应的时候默认会以json格式输出
 */
@RestController
@RequestMapping("/user")	//controller处理.../user的请求，...代表域名
@Api(value = "用户Controller")	//简单介绍controller的功能
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/query")	//这个方法会处理.../user/query的请求
	public User testQuery() {
		return userService.selectUserByName("Alice");
	}
	
	@RequestMapping("/insert")
	public List<User> testInsert() {
		userService.insertService();
		return userService.selectAllUser();	//可以反馈用户插入是否成功
	}
	
	@RequestMapping("/changemoney")
	public List<User> testchangemoney() {
		userService.changemoney();
		return userService.selectAllUser();
	}
	
	@RequestMapping("/delete")
	public String testDelete() {
		userService.deleteService(5);
		return "OK";
	}
	
	/**
	 * 下面是swagger测试的api。其实swagger更多地只是一个方便描述API的一个工具（类似于druid，可以直观地在网页显示和测试api），核心的HTTP请求与响应写法还是用的spring自己的注解
	 */
	
	/**
	 * GetMapping表示请求的方式是get，/{id}是路径参数，注意和查询参数区分。像上面RequestMapping映射的就是所有的请求方式，包括get,post,put,delete...
	 * PathVariable获取路径参数并绑定到方法的参数，和GetMapping里的参数，ApiImplicitParam里的参数三者一致。
	 * ResponseEntity是一个完整的HTTP response，而不是仅仅返回Json或者text
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "获取用户信息", notes = "根据用户名获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "path")
    @GetMapping("/{name}")
    public ResponseEntity getUserById(@PathVariable(value = "name") String name) {
        User user = new User();
        user.setName(name);
        return ResponseEntity.ok(user);	//成功返回，用ok(200)状态码封装
    }
	
	/**
	 * RequestParam表示url的查询参数值，即?a=xx&b=xx这种形式。传入的名字要和url中的参数名一致。
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "获取用户信息", notes = "根据用户名获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "query")
	@GetMapping("/search")
    public ResponseEntity getBookByName(@RequestParam("name") String name) {
		User user = new User();
        user.setName(name);
        return ResponseEntity.ok(user);
    }
	
	/**
	 * RequestBody将HttpRequest body中的JSON类型数据反序列化为合适的Java类型
	 * 这里ApiImplicitParam里的参数的name要和方法里的参数名一致，否则会抛出一个奇怪的nullpointer异常，swagger页面无法正常显示。不过，直观上这些参数名字也要保持一致。
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "data", value = "用户实体", required = true, dataType = "User", paramType = "body")
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody User data) {
		if (data != null) {
			User user = new User();
			user.setName(data.getName());
	        return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.ok("no input data");
		}
		
    }
}

package project.eureka.type0.db1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.eureka.type0.bean.User;
import project.eureka.type0.db1.dao.UserDao;

/**
 * service层最基本的就是调用Dao层，然后对得到的数据进行一些封装和处理，最后返回
 */
@Service
/**
 * 一般将cache设置在service层。cacheName相当于一个前缀，和key组成真正的redis的key。
 * 可以粗略地将cacheName理解成“表名”
 */
@CacheConfig(cacheNames = "user")
public class UserService {
	
	@Autowired
	private UserDao userDao;	//这里注入后相当于将一个实现了UserDao接口的类实例化了，可以直接用
	
	/**
	 * @Cacheable 缓存有数据时，从缓存获取；没有数据时，执行方法，并将返回值保存到缓存中
	 * 注意这里返回的是User，也就是SpringBoot要往redis里面添加的值是User对象。如果是对象，则要求它能够可序列化。所以User实体类要implements Serializable
	 * 这里简单测试了一下，无论同样的查询进行多少次，druid监控台sql都只执行一次，说明成功用上了缓存。且在redis中也能查到对应的k-v对。
	 * 类似于get方法
	 */
	@Cacheable(cacheNames = "user", key = "#name")
	public User selectUserByName(String name) {
		return userDao.findUserByName(name);
	}
	
	public List<User> selectAllUser() {
		return userDao.findAllUser();
	}
	
	/**
	 * @CachePut 一定会执行方法，并将返回值保存到缓存中
	 * 类似于put方法
	 */
	//@CachePut(key = "#name")
	public void insertService() {
		userDao.insertUser("Alice", 21, 3000.0);	//因为用了Double，所以必须显式double
		userDao.insertUser("Bob", 24, 3000.0);
	}
	
	/**
     * 根据key删除缓存区中的数据
     */
    //@CacheEvict(key = "#id")
	public void deleteService(int id) {
		userDao.deleteUser(id);
	}
	
	/**
	 * 模拟事务。保证数据的一致性。
	 */
	@Transactional
	public void changemoney() {
		userDao.updateUser("Alice", 21, 6000.0, 5);
		int tmp = 1 / 0;	//模拟转账过程中可能遇到的意外状况
		userDao.updateUser("Bob", 24, 0.0, 6);
	}
	
}

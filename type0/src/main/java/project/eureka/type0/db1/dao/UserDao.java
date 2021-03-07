package project.eureka.type0.db1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

import project.eureka.type0.bean.User;



/**
 * Dao层都不需要具体的实现（交给mybatis完成）
 * 所以用interface就好了，不用class
 * 注解实现相比xml更简洁一点，不需要额外配置xml文件
 */
@Mapper
@Qualifier("db1SqlSessionTemplate")	//标记数据源
public interface UserDao {
	
	/**
	 * 应该是把函数的参数name用另一个名字name声明，然后才能直接在注解中使用
	 */
	@Select("select * from user where name = #{name}")
	User findUserByName(@Param("name") String name);
	
	@Select("select * from user")
	List<User> findAllUser();
	
	@Insert("insert into user(name, age, money) values(#{name}, #{age}, #{money})")
	void insertUser(@Param("name") String name, @Param("age") Integer age, @Param("money") Double money);
	
	@Update("update user set name = #{name}, age = #{age}, money = #{money} where id = #{id}")
	void updateUser(@Param("name") String name, @Param("age") Integer age, @Param("money") Double money, @Param("id") int id);
	
	@Delete("delete from user where id = #{id}")
	void deleteUser(@Param("id") int id);
}

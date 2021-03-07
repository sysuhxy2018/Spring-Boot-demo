package project.eureka.type0.bean;

import java.io.Serializable;

/**
 * mybatis是如何将返回的数据库查询结果对应到实体类的呢？
 * 默认的映射方式要求遵循一定的命名规则：
 * 实体类中属性userName对应数据库列名user_name;
 * 当然也可以指定其他的映射方式，参考https://www.cnblogs.com/yuqingya/p/12016314.html
 */
public class User implements Serializable{
	/**
	 * 这里age, money可以为null，所以用包装类而不是基本类型
	 * id是主键，不能为null，所以用int即可
	 */
	private int id;
	private String name;
	private Integer age;
	private Double money;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", money=" + money + "]";
	}
	
}

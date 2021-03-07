package project.eureka.type0.db1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.beans.factory.annotation.Qualifier;

import project.eureka.type0.bean.Player;

/**
 * 逻辑：一个玩家（player）可以有很多不同英雄（champion），属于一对多的关系。
 * 我们在player的实体类里加入一个champion的列表，当然在数据库里player表中是没有这个list的。
 */
@Mapper
@Qualifier("db1SqlSessionTemplate")
public interface PlayerDao {
	
	/**
	 * 可以用Results/Result自定义映射规则，会覆盖默认的映射方式。因为默认的也有映射，所以我们不必每一个变量都写一个Result规则。
	 * property表示实体类的属性名，column表示数据库表的列名。
	 * many和@Many表示是多个的结果，如果只有一个返回结果，用one和@One。
	 * 这里的意思是，将id这列的值，先作为参数传给一个方法（要精确到哪个包下的哪个类中的哪个方法），然后将得到的结果（列表）装填到实体类的ls属性
	 */
	@Select("select * from player where name = #{name}")	//如果只有Select这行，显然ls无法被装填，所以就按默认的值null来设置。mybatis会自动装填那些映射成功的变量。
	@Results(id = "resultMap", value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"),
			@Result(property = "level", column = "level"),
			@Result(property = "ls", column = "id", 
			many = @Many(select = "project.eureka.type0.db1.dao.ChampionDao.findChampionsByOwnerId"))	//这里设置fetchType延迟加载会报错，就直接删除了（其实也有解决方法）
	})
	List<Player> findPlayersByName(@Param("name") String name);
	
	@Select("select * from player")
	@Result(property = "id", column = "id")	//如果没有这行，则id装配失败。尽量还是一个个变量写清楚，特别是重复映射的column。
	@Result(property = "ls", column = "id", 
			many = @Many(select = "project.eureka.type0.db1.dao.ChampionDao.findChampionsByOwnerId"))
	List<Player> findAllPlayers();
}

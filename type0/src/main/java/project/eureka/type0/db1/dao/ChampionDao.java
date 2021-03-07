package project.eureka.type0.db1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import project.eureka.type0.bean.Champion;


@Mapper
@Qualifier("db1SqlSessionTemplate")
public interface ChampionDao {
	
	@Select("select * from champion where owner_id = #{oid}")
	@Result(property = "ownerId", column = "owner_id")	//奇怪，这里ownerId没有默认映射owner_id（好像要手动配置）。那我们手动补充映射。
	List<Champion> findChampionsByOwnerId(@Param("oid") int oid);
}

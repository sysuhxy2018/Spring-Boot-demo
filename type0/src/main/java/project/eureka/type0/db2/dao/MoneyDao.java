package project.eureka.type0.db2.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import project.eureka.type0.bean.Money;

@Qualifier("db2SqlSessionTemplate")
public interface MoneyDao {

    @Select("SELECT * FROM money WHERE id = #{id}")
    Money findMoneyById(@Param("id") int id);
}

package project.eureka.type0.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * MapperScan简单来说就是建立Dao层和mybatis数据源间的映射，两者互相关联。
 * basePackages指定Dao包名，sqlSessionTemplateRef指定生成的名为db1SqlSessionTemplate的Bean。
 */
@Configuration
@MapperScan(basePackages = "project.eureka.type0.db1.dao", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSource1Config {

    /**
     * 生成数据源。
     * Bean标记返回的对象（Bean）一个别名
     * ConfigurationProperties表示自动导入application.properties中属性名前缀为prefix的属性
     * Primary可以暂时理解为用于标记默认数据源相关的Bean
     */
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    @Primary
    public DataSource testDataSource() {
        return DruidDataSourceBuilder.create().build();	//注意是DruidDataSourceBuilder而不是DataSourceBuilder。如果是后者，将直接用默认的数据库连接池Hikari
    }

    /**
     * 创建 SqlSessionFactory。
     * Qualifier类似于Autowired，会自动注入。如这里将上面标记为db1DataSource的Bean注入到局部变量dataSource
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理。
     */
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建SqlSessionTemplate。这个SqlSessionTemplate本质基于db1DataSource
     */
    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
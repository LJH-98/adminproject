//package com.ljh.admin.mybatis;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import javax.sql.DataSource;
//
//
//@Configuration
//@MapperScan(basePackages = {"com.ljh.admin.dao.oracle"},
//            sqlSessionFactoryRef = "SqlSessionFactoryOracle",
//            sqlSessionTemplateRef = "SqlSessionTemplateOracle")
//public class MybatisOracle {
//
//    //@Value("mapper.oracle.name")
//    private String mapperXml="classpath:mapper/oracle/*.xml";
//
//    /**
//     * 配置oracle链接信息
//     * Bean 数据源Bean的id名
//     * ConfigurationProperties  使用配置文件下的spring.datasource.oracle的链接信息
//     * @return
//     */
//    @Bean(value = "DataSourceOracle")
//    @ConfigurationProperties(prefix = "spring.datasource.oracle")
//    public DataSource dataSourceOracle(){
//        return DataSourceBuilder.create().build();
//    }
//
//
//    /**
//     * 将链接信息注入SqlSessionFactory,扫描对应的mapper.xml文件
//     * @param dataSourceOracle 链接信息
//     * @return SqlSessionFactory
//     * @throws Exception
//     */
//    @Bean(value = "SqlSessionFactoryOracle")
//    public SqlSessionFactory sqlSessionFactoryOracle(@Qualifier("DataSourceOracle") DataSource dataSourceOracle) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSourceOracle);
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(mapperXml));
//        return factoryBean.getObject();
//    }
//
//
//    /**
//     * @param sqlSessionFactoryOracle 使用spring-mybatis的sqlSessionTemplate(线程安全)  代替DefaultSqlSession
//     * SqlSessionTemplate 将会保证使用的 SqlSession 是和当前 Spring 的事务相关的,实现数据库关闭,提交或回滚操作
//     * @return sqlSessionFactoryOracle
//     */
//    @Bean(value = "SqlSessionTemplateOracle")
//    public SqlSessionTemplate sqlSessionTemplateOracle(@Qualifier("SqlSessionFactoryOracle")SqlSessionFactory sqlSessionFactoryOracle){
//        return new SqlSessionTemplate(sqlSessionFactoryOracle);
//    }
//
//
//    /**
//     * 事务管理
//     * @param dataSourceOracle
//     * @return
//     */
//    public DataSourceTransactionManager dataSourceTransactionManagerOracle(@Qualifier("DataSourceOracle") DataSource dataSourceOracle){
//        return new DataSourceTransactionManager(dataSourceOracle);
//    }
//}

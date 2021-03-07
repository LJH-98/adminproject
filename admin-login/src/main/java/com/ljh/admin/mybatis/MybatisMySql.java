//package com.ljh.admin.mybatis;
//
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import javax.sql.DataSource;
//
///**
// * mysql数据链接配置
// */
//@Configuration
//@MapperScan(basePackages = {"com.ljh.admin.dao.mysql"}
//            ,sqlSessionFactoryRef = "SqlSessionFactoryMysql"
//            ,sqlSessionTemplateRef = "SqlSessionTemplateMysql")
//public class MybatisMySql {
//
//    //@Value("mapper.mysql.name")
//    private String mapperXml="classpath:mapper/mysql/*.xml";
//
//
//
//
//    /**
//     * 配置mysql链接信息
//     * Bean 数据源Bean的id名
//     * ConfigurationProperties  使用配置文件下的spring.datasource.mysql的链接信息
//     * @return
//     */
//    @Bean(value = "DataSourceMysql")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource dataSourceMysql(){
////        DruidDataSource dataSource = new DruidDataSource();
////        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
////        dataSource.setUrl("jdbc:mysql://localhost:3306/myuser?useUnicode=true&characterEncoding=utf-8&useSSL=false");
////        dataSource.setUsername("root");
////        dataSource.setPassword("369000");
////        return dataSource;
//        //return new DruidDataSource();
//        return DataSourceBuilder.create().build();
//    }
//
//
//    /**
//     * 将链接信息注入SqlSessionFactory,扫描对应的mapper.xml文件
//     * @param dataSourceMysql 链接信息
//     * @return SqlSessionFactory
//     * @throws Exception
//     */
//    @Bean(value = "SqlSessionFactoryMysql")
//    public SqlSessionFactory sqlSessionFactoryMysql(@Qualifier("DataSourceMysql") DataSource dataSourceMysql) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSourceMysql);
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                                        .getResources(mapperXml));
//        return factoryBean.getObject();
//    }
//
//
//    /**
//     * @param sqlSessionFactoryMysql 使用spring-mybatis的sqlSessionTemplate(线程安全)  代替DefaultSqlSession
//     * SqlSessionTemplate 将会保证使用的 SqlSession 是和当前 Spring 的事务相关的,实现数据库关闭,提交或回滚操作
//     * @return sqlSessionFactoryMysql
//     */
//    @Bean(value = "SqlSessionTemplateMysql")
//    public SqlSessionTemplate sqlSessionTemplateMysql(@Qualifier("SqlSessionFactoryMysql")SqlSessionFactory sqlSessionFactoryMysql){
//        return new SqlSessionTemplate(sqlSessionFactoryMysql);
//    }
//
//
//    /**
//     * 事务管理
//     * @param dataSourceMysql
//     * @return
//     */
//    @Bean(value = "DataSourceTransactionManagerMysql")
//    public DataSourceTransactionManager dataSourceTransactionManagerMysql(@Qualifier("DataSourceMysql") DataSource dataSourceMysql){
//        return new DataSourceTransactionManager(dataSourceMysql);
//    }
//
//}

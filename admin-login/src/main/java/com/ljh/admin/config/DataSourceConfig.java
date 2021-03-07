//package com.ljh.admin.config;
//
//import org.apache.ibatis.mapping.DatabaseIdProvider;
//import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//public class DataSourceConfig {
//    @Value("${mybatis.mapper-locations}")
//    private String mapperLocations;
//
//    @Primary
//    @Bean(name = "dataSource")
//    @ConfigurationProperties("spring.datasource.hikari")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//
//    @Bean
//    public DatabaseIdProvider databaseIdProvider() {
//        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
//        Properties p = new Properties();
//        p.setProperty("Oracle", "oracle");
//        p.setProperty("MySQL", "mysql");
//        p.setProperty("PostgreSQL", "postgresql");
//        p.setProperty("DB2", "db2");
//        p.setProperty("SQL Server", "sqlserver");
//        databaseIdProvider.setProperties(p);
//        return databaseIdProvider;
//    }
//
//
//
//
//    @Primary
//    @Bean(value = "sqlSessionFactoryBean")
//    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setDatabaseIdProvider(databaseIdProvider());
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
//
//        return factoryBean;
//    }
//
//
//    /**
//     * @param sqlSessionFactoryBean 使用spring-mybatis的sqlSessionTemplate(线程安全)  代替DefaultSqlSession
//     * SqlSessionTemplate 将会保证使用的 SqlSession 是和当前 Spring 的事务相关的,实现数据库关闭,提交或回滚操作
//     * @return sqlSessionFactoryMysql
//     */
//    @Bean(value = "SqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplateMysql(@Qualifier("sqlSessionFactoryBean")SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
//    }
//
//
//    /**
//     * 事务管理
//     * @param dataSource
//     * @return
//     */
//    @Bean(value = "DataSourceTransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManagerMysql(@Qualifier("dataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//
//
//
//}

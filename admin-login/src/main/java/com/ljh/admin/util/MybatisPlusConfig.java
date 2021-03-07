package com.ljh.admin.util;







import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusConfig {


    public static void main(String[] args) {


        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOpen(false)
          .setAuthor("Sailing")     //作者
          .setOutputDir(projectPath + "/admin-login/src/main/java")
          .setIdType(IdType.AUTO)   //主键生成策略  这里是自增模式
          .setActiveRecord(true)  //是否支持AR模式
          .setFileOverride(true)   //是否支持覆盖
          .setServiceName("%sService")   //去除Service层名字开头的I
          .setBaseResultMap(true);   //生成Mapper映射

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/myuser?useUnicode=true&characterEncoding=utf-8&useSSL=false")
           .setDriverName("com.mysql.jdbc.Driver")
           .setUsername("root")
           .setPassword("369000");



        // 4、策略配置
        StrategyConfig sc = new StrategyConfig();
          sc.setCapitalMode(true)
            .setEntityColumnConstant(true)
            .setRestControllerStyle(true)
            .setNaming(NamingStrategy.underline_to_camel)
            .setColumnNaming(NamingStrategy.underline_to_camel)
            .setTablePrefix("tbl_")   //表前缀
            .setInclude("user");  //表名


        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.ljh.admin.mp")
          .setMapper("dao")
          .setService("service")
          .setController("controller")
          .setXml("dao.mapper");

        //整合配置
        AutoGenerator ag=new AutoGenerator();
        ag.setPackageInfo(pc)
          .setStrategy(sc)
          .setDataSource(dsc)
          .setGlobalConfig(gc);

        //启动
        ag.execute();
    }
}

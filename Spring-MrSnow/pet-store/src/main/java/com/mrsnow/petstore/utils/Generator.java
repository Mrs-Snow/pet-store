package com.mrsnow.petstore.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2022-07-08  23:54
 * mybatis-plus 代码生成器
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 * 项目写好后千万不要运行此处！！！！！项目写好后千万不要运行此处！！！！！
 **/
public class Generator {
    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /**
         * 全局配置
         */
        GlobalConfig globalConfig = new GlobalConfig();
        //是否支持AR模式
        globalConfig.setActiveRecord(true);
        //作者
        System.out.println("请输入作者姓名");
        String author = scanner.nextLine();
        globalConfig.setAuthor(author);
        //生成路径
        globalConfig.setOutputDir("src/main/java");
        //文件覆盖
        globalConfig.setFileOverride(true);
        //主键策略
        globalConfig.setIdType(IdType.AUTO);
        //service接口名字前是否有I,我选择没有
        globalConfig.setServiceName("%sService");
        //基本的映射
        globalConfig.setBaseResultMap(true);
        //基本的sql片段
        globalConfig.setBaseColumnList(true);

        /**
         * 数据库配置
         */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        //驱动
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        //url
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/pet");
        //username
        dataSourceConfig.setUsername("root");
        //password
        dataSourceConfig.setPassword("12345678");

        /**
         * 策略配置
         */
        StrategyConfig strategyConfig = new StrategyConfig();
        //全局大写命名
        strategyConfig.setCapitalMode(true);
        //映射下划线到驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //表名前缀
        strategyConfig.setTablePrefix("pet_");
        //生产的表
        String[] tables = new String[]{"pet_address","pet_cart","pet_goods","pet_users","pet_shipAddress","pet_order","pet_favorite","pet_preferential","pet_store"};
        strategyConfig.setInclude("pet_user");

        /**
         * 包名策略配置
         */
        PackageConfig packageConfig = new PackageConfig();
        //父包
        packageConfig.setParent("com.mrsnow.petstore");
        //mvc包名
        packageConfig.setController("controller")
                .setService("service")
                .setMapper("mapper")
                .setEntity("dao")
                .setXml("mapper");



        /**
         * 整合配置
         */
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        /**
         * 执行
         */
        autoGenerator.execute();
    }

}


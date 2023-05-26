//package com.utopian.tech.demo.es.test;
//
//import com.utopian.tech.demo.es.entity.Product;
//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringDataESTest extends TestCase {
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchRestTemplate;
//
//
//    @Test
//    public void createIndex() {
//        //创建索引，系统初始化会自动创建索引
//        System.out.println("创建索引");
//    }
//
//    @Test
//    public void deleteIndex() {
//        //创建索引，系统初始化会自动创建索引
//        boolean flg = elasticsearchRestTemplate.deleteIndex(Product.class);
//        System.out.println("删除索引" + flg);
//    }
//
//}

//package com.utopian.tech.demo.es.entity;
//
//import lombok.Data;
//import lombok.experimental.Accessors;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//@Data
//@Accessors(chain = true)
//@Document(indexName = "product", shards = 3, replicas = 1)
//public class Product {
//    @Id
//    private Long id;//商品唯一标识
//
//    //    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    @Field(type = FieldType.Text)
//    private String title;//商品名称
//
//    @Field(type = FieldType.Keyword)
//    private String category;//分类名称
//
//    @Field(type = FieldType .Double)
//    private Double price;//商品价格
//
//    @Field(type = FieldType.Keyword,index = false)
//    private String images;//图片地址
//}

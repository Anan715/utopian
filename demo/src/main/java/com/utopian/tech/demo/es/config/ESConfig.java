//package com.utopian.tech.demo.es.config;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.utopian.tech.demo.es.dao")
//public class ESConfig {
//
//    @Value("${elasticsearch.host}")
//    private String host;
//
//    @Value("${elasticsearch.port}")
//    private int port;
//
//    @Bean
//    public Client elasticsearchClient() {
//        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port))
//                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
//                        .setConnectTimeout(5000) // 连接超时时间（毫秒）
//                        .setSocketTimeout(60000));// Socket 超时时间（毫秒）
//        return (Client) new RestHighLevelClient(restClientBuilder);
//    }
//
//    @Bean
//    public ElasticsearchTemplate elasticsearchRestTemplate() {
//        return new ElasticsearchTemplate(elasticsearchClient());
//    }
//
//}

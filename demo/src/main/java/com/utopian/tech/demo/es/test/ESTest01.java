//package com.utopian.tech.demo.es.test;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//
//import java.io.IOException;
//
//public class ESTest01 {
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//
//        // 创建索引
//        CreateIndexRequest request = new CreateIndexRequest("user");
//        CreateIndexResponse createIndexResponse =
//                client.indices().create(request, RequestOptions.DEFAULT);
//        // 响应状态
//        boolean acknowledged = createIndexResponse.isAcknowledged();
//        System.out.println("索引操作 :" + acknowledged);
//
//        client.close();
//    }
//}

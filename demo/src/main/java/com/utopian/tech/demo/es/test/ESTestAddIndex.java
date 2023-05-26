//package com.utopian.tech.demo.es.test;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.utopian.tech.demo.es.entity.User;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.DocWriteResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.xcontent.XContentType;
//
//import java.io.IOException;
//
//public class ESTestAddIndex {
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//        IndexRequest request = new IndexRequest();
//        request.index("user").id("20230523001");
//        User user = new User();
//        user.setAddress("北京市朝阳区安贞门田园翔太大厦").setAge(18).setName("李六旬");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String userStr = mapper.writeValueAsString(user);
//        request.source(userStr, XContentType.JSON);
//        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//        DocWriteResponse.Result result = response.getResult();
//
//
//        System.out.println("索引操作 :" + result);
//
//        client.close();
//    }
//}

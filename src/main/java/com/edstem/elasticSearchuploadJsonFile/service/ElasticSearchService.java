package com.edstem.elasticSearchuploadJsonFile.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ElasticSearchService {

    @Autowired
    private RestHighLevelClient client;

    public void uploadJsonDocument(String index, MultipartFile multipartFile) throws IOException {
        String jsonString = new String(multipartFile.getBytes());
        IndexRequest request = new IndexRequest(index);
        request.source(jsonString, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        String id = response.getId();
        System.out.println("Document indexed with id: " + id);
    }
}


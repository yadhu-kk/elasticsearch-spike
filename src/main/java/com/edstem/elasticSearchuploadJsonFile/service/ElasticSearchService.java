package com.edstem.elasticSearchuploadJsonFile.service;

import com.edstem.elasticSearchuploadJsonFile.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
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

    public SearchResponse search(SearchRequest request, String index) throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<String> programmingLanguages = List.of(request.getProgrammingLanguages());
        if (!programmingLanguages.isEmpty()) {
            for (String lang : programmingLanguages) {
                queryBuilder.must(QueryBuilders.matchQuery("skillsAndTechnologies.programmingLanguages", lang));
            }
        }

        String role = request.getRole();
        if (role != null && !role.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("skillsAndTechnologies.role", role));
        }

        Integer totalYearsOfExperience = request.getTotalYearsOfExperience();
        if (totalYearsOfExperience != null) {
            queryBuilder.must(QueryBuilders.matchQuery("skillsAndTechnologies.totalYearsOfExperience", totalYearsOfExperience));
        }
        int from = request.getPage() * request.getSize();
        SortOrder sortOrder = SortOrder.valueOf(request.getSortOrder().toUpperCase());

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder)
                .from(from)
                .size(request.getSize());
        String orderBy = request.getOrderBy();
        String orderByField;
        switch (orderBy) {
            case "totalYearsOfExperience":
                orderByField = "skillsAndTechnologies.totalYearsOfExperience";
                break;
            case "programmingLanguages":
                orderByField = "skillsAndTechnologies.programmingLanguages";
                break;
            case "role":
                orderByField = "skillsAndTechnologies.role";
                break;
            default:
                orderByField = null;
        }
        if (orderByField != null) {
            searchSourceBuilder.sort(orderByField, sortOrder);
        }

        SearchRequest searchRequest = new SearchRequest("sample-resume");
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        return response;
    }

}

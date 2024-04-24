package com.edstem.elasticSearchuploadJsonFile.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchRequest extends org.elasticsearch.action.search.SearchRequest {
    private String[] programmingLanguages;
    private String role;
    private int totalYearsOfExperience;
    private int page;
    private int size;
    private String orderBy;
    private String sortOrder;
    private SearchSourceBuilder source;

    public SearchRequest(String index) {
    }

    public SearchRequest(String language, String experienceLevel, String role) {
    }

    public SearchRequest(String experienceLevel, String role) {
    }

    public SearchRequest(String[] languagesArray, String experienceLevel, String role) {
    }
}

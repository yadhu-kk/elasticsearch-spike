package com.edstem.elasticSearchuploadJsonFile.controller;

import com.edstem.elasticSearchuploadJsonFile.request.SearchRequest;
import com.edstem.elasticSearchuploadJsonFile.service.ElasticSearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/json")
    public String uploadJsonFile(@RequestParam("file") MultipartFile file) throws Exception {

        elasticSearchService.uploadJsonDocument("sample-resume", file);
        return "File uploaded successfully to Elasticsearch!";
    }

    @GetMapping("")
    public ResponseEntity<SearchResponse> getResumes(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String experienceLevel,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "totalYearsOfExperience") String orderBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) throws IOException {
        SearchRequest request = new SearchRequest();
        request.setProgrammingLanguages(language != null ? new String[]{language} : null);
        request.setTotalYearsOfExperience(experienceLevel != null ? Integer.parseInt(experienceLevel) : null);
        request.setRole(role);
        request.setPage(page);
        request.setSize(size);
        request.setOrderBy(orderBy);
        request.setSortOrder(sortOrder);

        org.elasticsearch.action.search.SearchResponse response = elasticSearchService.search(request, "sample-resume");
        return ResponseEntity.ok(response);
    }

}


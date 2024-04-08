package com.edstem.elasticSearchuploadJsonFile.controller;

import com.edstem.elasticSearchuploadJsonFile.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
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
}


package com.edstem.elasticSearchuploadJsonFile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "sample-resume")
public class SearchModel {
    private Long id;
}

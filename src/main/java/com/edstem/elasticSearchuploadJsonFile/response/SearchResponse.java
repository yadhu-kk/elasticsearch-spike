package com.edstem.elasticSearchuploadJsonFile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.search.SearchHit;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private List<SearchHit> hits;
}

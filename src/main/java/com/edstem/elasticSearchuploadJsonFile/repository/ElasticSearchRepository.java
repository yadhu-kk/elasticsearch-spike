package com.edstem.elasticSearchuploadJsonFile.repository;

import com.edstem.elasticSearchuploadJsonFile.model.SearchModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<SearchModel,Long> {

}

package ru.giylmi.dictionary.repository.search;

import ru.giylmi.dictionary.domain.Word;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Word entity.
 */
public interface WordSearchRepository extends ElasticsearchRepository<Word, Long> {
}

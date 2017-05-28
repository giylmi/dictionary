package ru.giylmi.dictionary.web.rest;

import com.codahale.metrics.annotation.Timed;
import ru.giylmi.dictionary.domain.Word;

import ru.giylmi.dictionary.repository.WordRepository;
import ru.giylmi.dictionary.repository.search.WordSearchRepository;
import ru.giylmi.dictionary.web.rest.util.HeaderUtil;
import ru.giylmi.dictionary.web.rest.util.PaginationUtil;
import ru.giylmi.dictionary.service.dto.WordDTO;
import ru.giylmi.dictionary.service.mapper.WordMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Word.
 */
@RestController
@RequestMapping("/api")
public class WordResource {

    private final Logger log = LoggerFactory.getLogger(WordResource.class);

    private static final String ENTITY_NAME = "word";
        
    private final WordRepository wordRepository;

    private final WordMapper wordMapper;

    private final WordSearchRepository wordSearchRepository;

    public WordResource(WordRepository wordRepository, WordMapper wordMapper, WordSearchRepository wordSearchRepository) {
        this.wordRepository = wordRepository;
        this.wordMapper = wordMapper;
        this.wordSearchRepository = wordSearchRepository;
    }

    /**
     * POST  /words : Create a new word.
     *
     * @param wordDTO the wordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wordDTO, or with status 400 (Bad Request) if the word has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/words")
    @Timed
    public ResponseEntity<WordDTO> createWord(@RequestBody WordDTO wordDTO) throws URISyntaxException {
        log.debug("REST request to save Word : {}", wordDTO);
        if (wordDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new word cannot already have an ID")).body(null);
        }
        Word word = wordMapper.toEntity(wordDTO);
        word = wordRepository.save(word);
        WordDTO result = wordMapper.toDto(word);
        wordSearchRepository.save(word);
        return ResponseEntity.created(new URI("/api/words/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /words : Updates an existing word.
     *
     * @param wordDTO the wordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wordDTO,
     * or with status 400 (Bad Request) if the wordDTO is not valid,
     * or with status 500 (Internal Server Error) if the wordDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/words")
    @Timed
    public ResponseEntity<WordDTO> updateWord(@RequestBody WordDTO wordDTO) throws URISyntaxException {
        log.debug("REST request to update Word : {}", wordDTO);
        if (wordDTO.getId() == null) {
            return createWord(wordDTO);
        }
        Word word = wordMapper.toEntity(wordDTO);
        word = wordRepository.save(word);
        WordDTO result = wordMapper.toDto(word);
        wordSearchRepository.save(word);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /words : get all the words.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of words in body
     */
    @GetMapping("/words")
    @Timed
    public ResponseEntity<List<WordDTO>> getAllWords(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Words");
        Page<Word> page = wordRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/words");
        return new ResponseEntity<>(wordMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /words/:id : get the "id" word.
     *
     * @param id the id of the wordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/words/{id}")
    @Timed
    public ResponseEntity<WordDTO> getWord(@PathVariable Long id) {
        log.debug("REST request to get Word : {}", id);
        Word word = wordRepository.findOne(id);
        WordDTO wordDTO = wordMapper.toDto(word);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wordDTO));
    }

    /**
     * DELETE  /words/:id : delete the "id" word.
     *
     * @param id the id of the wordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/words/{id}")
    @Timed
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        log.debug("REST request to delete Word : {}", id);
        wordRepository.delete(id);
        wordSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/words?query=:query : search for the word corresponding
     * to the query.
     *
     * @param query the query of the word search 
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/words")
    @Timed
    public ResponseEntity<List<WordDTO>> searchWords(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Words for query {}", query);
        Page<Word> page = wordSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/words");
        return new ResponseEntity<>(wordMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }


}

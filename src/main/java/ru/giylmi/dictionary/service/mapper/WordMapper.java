package ru.giylmi.dictionary.service.mapper;

import ru.giylmi.dictionary.domain.*;
import ru.giylmi.dictionary.service.dto.WordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Word and its DTO WordDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, })
public interface WordMapper extends EntityMapper <WordDTO, Word> {
    @Mapping(source = "category.id", target = "categoryId")
    WordDTO toDto(Word word); 
    @Mapping(source = "categoryId", target = "category")
    Word toEntity(WordDTO wordDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Word fromId(Long id) {
        if (id == null) {
            return null;
        }
        Word word = new Word();
        word.setId(id);
        return word;
    }
}

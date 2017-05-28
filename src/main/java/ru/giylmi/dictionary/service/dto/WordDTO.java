package ru.giylmi.dictionary.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Word entity.
 */
public class WordDTO implements Serializable {

    private Long id;

    private String rus;

    private String en;

    private String definition;

    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WordDTO wordDTO = (WordDTO) o;
        if(wordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WordDTO{" +
            "id=" + getId() +
            ", rus='" + getRus() + "'" +
            ", en='" + getEn() + "'" +
            ", definition='" + getDefinition() + "'" +
            "}";
    }
}

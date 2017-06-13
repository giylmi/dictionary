package ru.giylmi.dictionary.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Word.
 */
@Entity
@Table(name = "word")
@Document(indexName = "word")
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "rus")
    private String rus;

    @Column(name = "en")
    private String en;

    @Column(name = "definition")
    private String definition;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRus() {
        return rus;
    }

    public Word rus(String rus) {
        this.rus = rus;
        return this;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getEn() {
        return en;
    }

    public Word en(String en) {
        this.en = en;
        return this;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getDefinition() {
        return definition;
    }

    public Word definition(String definition) {
        this.definition = definition;
        return this;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Category getCategory() {
        return category;
    }

    public Word category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word = (Word) o;
        if (word.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), word.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Word{" +
            "id=" + getId() +
            ", rus='" + getRus() + "'" +
            ", en='" + getEn() + "'" +
            ", definition='" + getDefinition() + "'" +
            "}";
    }
}

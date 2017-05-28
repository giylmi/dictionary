package ru.giylmi.dictionary.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Document(indexName = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "rus")
    private String rus;

    @Column(name = "en")
    private String en;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRus() {
        return rus;
    }

    public Category rus(String rus) {
        this.rus = rus;
        return this;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getEn() {
        return en;
    }

    public Category en(String en) {
        this.en = en;
        return this;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", rus='" + getRus() + "'" +
            ", en='" + getEn() + "'" +
            "}";
    }
}

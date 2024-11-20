package com.github.model;


import com.github.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book extends BaseModel<Integer> implements Serializable {
    private String title;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    private Double price;

    private Double weight;

    @Column(name = "author_name")
    private String authorName;

    private String publisher;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Subject subject;
}

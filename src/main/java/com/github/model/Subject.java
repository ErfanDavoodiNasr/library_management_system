package com.github.model;

import com.github.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class Subject extends BaseModel<Integer>  implements Serializable {
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Book> books;

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                '}';
    }
}

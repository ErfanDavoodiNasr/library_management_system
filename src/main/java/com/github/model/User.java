package com.github.model;

import com.github.base.model.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseModel<Integer> implements Serializable {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Builder.Default
    private LocalDateTime joined = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(
            name = "j_user_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    List<Book> borrowBooks;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;
}
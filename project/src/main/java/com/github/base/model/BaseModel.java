package com.github.base.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;
}
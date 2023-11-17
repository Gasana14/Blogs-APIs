package com.codesmachine.springbootrestapi.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "category_id",unique = true)
    private String id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts;

}

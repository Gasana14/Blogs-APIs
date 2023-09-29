package com.codesmachine.springbootrestapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "post_id",unique = true)
    private String id;

    @Column(name = "title",nullable = false,unique = true, length = 100)
    private String title;

    @Column(name = "description",nullable = false,length = 1000)
    private String description;

    @Column(name = "content",nullable = false,length = 2000)
    private String content;

}

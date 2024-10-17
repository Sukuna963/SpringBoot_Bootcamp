package com.codingnomads.springdata.jpa.onetomany.bidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    // this annotation references the configuration
    // on the post field in the Comment class
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
}


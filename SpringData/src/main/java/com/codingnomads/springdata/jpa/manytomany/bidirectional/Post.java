package com.codingnomads.springdata.jpa.manytomany.bidirectional;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /*
    * In practical use, it's common to refer to an id column, not a username.
    * This example is mainly for demonstration purposes.
    * */
    @JoinTable(
            name = "post_location_join_table",
            joinColumns = @JoinColumn(
                    name = "post_username",
                    referencedColumnName = "username"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "location_latitude",
                    referencedColumnName = "latitude"
            )
    )
    private Set<Location> locations;
}

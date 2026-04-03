package com.workintech.Sprint19_Challenge.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweet", schema = "public")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Likes> likes;
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Comments> comments;
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Retweets> retweets;
}

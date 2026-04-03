package com.workintech.Sprint19_Challenge.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String userName;
    @Column(name = "password")
    @NotNull
    @NotBlank
    @Size(min = 5, max = 10)
    private String password;
    @Column(name = "email")
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweet;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Likes> likes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comments> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Retweets> retweets;
}

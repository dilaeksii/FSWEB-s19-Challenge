package com.workintech.Sprint19_Challenge.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "public")
public class User implements  UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String fullName;

    @Column(name = "password")
    @NotNull
    @NotBlank
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }





}

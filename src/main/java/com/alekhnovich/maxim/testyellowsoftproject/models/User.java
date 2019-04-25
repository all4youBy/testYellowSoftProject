package com.alekhnovich.maxim.testyellowsoftproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user",schema = "testyellowsoftproject")
@NoArgsConstructor
@RequiredArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Setter
    @Getter
    @NonNull
    private String login;

    @Setter
    @Getter
    @JsonIgnore
    @Column(name = "passhash")
    @NonNull
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Setter
    @Getter
    @JsonIgnore
    private Set<Run> userRuns;
}

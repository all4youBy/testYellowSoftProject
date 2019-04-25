package com.alekhnovich.maxim.testyellowsoftproject.models;

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
    @Column(name = "passhash")
    @NonNull
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Setter
    @Getter
    private Set<Run> userRuns;
}

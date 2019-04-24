package com.alekhnovich.maxim.testyellowsoftproject.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
public class User{

    @Id
    @Getter
    private Long id;

    @Setter
    @Getter
    @Column(name = "name")
    @NonNull
    private String login;

    @Setter
    @Getter
    @Column(name = "passHash")
    @NonNull
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Setter
    @Getter
    private Set<Run> userRuns;
}

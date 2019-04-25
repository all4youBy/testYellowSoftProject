package com.alekhnovich.maxim.testyellowsoftproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "run",schema = "testyellowsoftproject")
@NoArgsConstructor
@RequiredArgsConstructor
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Setter
    @Getter
    @NonNull
    private Double distance;

    @Column(name = "run_time")
    @Setter
    @Getter
    private LocalDateTime runTime;

    @Column(name = "run_date")
    @Setter
    @Getter
    private LocalDate runDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

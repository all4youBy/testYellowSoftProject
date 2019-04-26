package com.alekhnovich.maxim.testyellowsoftproject.repositories.queries;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import com.alekhnovich.maxim.testyellowsoftproject.models.Run_;
import com.alekhnovich.maxim.testyellowsoftproject.models.User_;
import org.springframework.data.jpa.domain.Specification;


public class RunSpecification {
    public static Specification<Run> userRuns(String userLogin){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Run_.user).get(User_.login),userLogin);
    }
}

package com.example.blaze_persistence_demo.views;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.example.blaze_persistence_demo.domain.Team;

import java.util.List;

@EntityView(Team.class)
public interface TeamView {
    @IdMapping
    Long getId();

    String getName();

    List<MemberView> getMembers();
}

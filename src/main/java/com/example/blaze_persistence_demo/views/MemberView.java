package com.example.blaze_persistence_demo.views;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.example.blaze_persistence_demo.domain.Member;

@EntityView(Member.class)
public interface MemberView {

    @IdMapping
    Long getId();

    String getName();

    String getSurname();

    String getEmail();

    Integer getPoints();

    String getLocation();
}

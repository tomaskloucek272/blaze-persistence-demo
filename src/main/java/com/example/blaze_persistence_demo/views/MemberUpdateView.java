package com.example.blaze_persistence_demo.views;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.example.blaze_persistence_demo.domain.Member;

@UpdatableEntityView
@EntityView(Member.class)
public interface MemberUpdateView {

    @IdMapping
    Long getId();

    String getName();
    void setName(String name);

    String getSurname();
    void setSurname(String surname);

    String getEmail();
    void setEmail(String email);

    Integer getPoints();
    void setPoints(Integer points);

    String getLocation();
    void setLocation(String location);
}

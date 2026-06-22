package com.example.blaze_persistence_demo.repository;

import com.blazebit.persistence.spring.data.repository.EntityViewRepository;
import com.example.blaze_persistence_demo.views.MemberView;

import java.util.Optional;

public interface MemberRepository extends EntityViewRepository<MemberView, Long> {

    Optional<MemberView> findByEmail(String email);
}

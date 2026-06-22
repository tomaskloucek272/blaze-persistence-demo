package com.example.blaze_persistence_demo.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.example.blaze_persistence_demo.domain.Member;
import com.example.blaze_persistence_demo.domain.Team;
import com.example.blaze_persistence_demo.repository.MemberRepository;
import com.example.blaze_persistence_demo.views.MemberView;
import com.example.blaze_persistence_demo.views.TeamView;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl {

    private final CriteriaBuilderFactory criteriaBuilderFactory;
    private final EntityViewManager entityViewManager;
    private final EntityManager entityManager;
    private final MemberRepository memberRepository;

    public TeamView getTeamByTeamName(String teamName) {
        CriteriaBuilder<Member> memberCriterialBuilder = criteriaBuilderFactory.create(entityManager, Member.class)
                .from(Team.class, "t")
                .where("t.name").eq(teamName);

        EntityViewSetting<TeamView, CriteriaBuilder<TeamView>> setting =
                EntityViewSetting.create(TeamView.class);

        return entityViewManager.applySetting(setting, memberCriterialBuilder).getSingleResult();
    }

    public List<MemberView> getMembersWithPointsAbove(int minPoints) {
        CriteriaBuilder<Member> cb = criteriaBuilderFactory.create(entityManager, Member.class)
                .where("points").gt(minPoints);

        return entityViewManager.applySetting(EntityViewSetting.create(MemberView.class), cb).getResultList();
    }

    public Optional<MemberView> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}

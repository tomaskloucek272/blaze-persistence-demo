package com.example.blaze_persistence_demo.configuration;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import com.example.blaze_persistence_demo.views.MemberView;
import com.example.blaze_persistence_demo.views.TeamView;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlazePersistenceConfig {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public CriteriaBuilderFactory createCriteriaBuilderFactory() {
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        return config.createCriteriaBuilderFactory(entityManagerFactory);
    }

    @Bean
    public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf) {
        EntityViewConfiguration config = EntityViews.createDefaultConfiguration();
        config.addEntityView(MemberView.class);
        config.addEntityView(TeamView.class);
        return config.createEntityViewManager(cbf);
    }
}

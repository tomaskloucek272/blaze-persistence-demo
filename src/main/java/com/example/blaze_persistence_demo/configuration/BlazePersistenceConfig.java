package com.example.blaze_persistence_demo.configuration;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.integration.jackson.EntityViewAwareObjectMapper;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import com.example.blaze_persistence_demo.views.MemberUpdateView;
import com.example.blaze_persistence_demo.views.MemberView;
import com.example.blaze_persistence_demo.views.TeamView;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;


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
        config.addEntityView(MemberUpdateView.class);
        return config.createEntityViewManager(cbf);
    }

    @Bean
    public ObjectMapper objectMapper(EntityViewManager evm) {
        ObjectMapper baseMapper = new ObjectMapper();

        baseMapper.registerModule(new JavaTimeModule());
        baseMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return new EntityViewAwareObjectMapper(evm, baseMapper).getObjectMapper();
    }
}

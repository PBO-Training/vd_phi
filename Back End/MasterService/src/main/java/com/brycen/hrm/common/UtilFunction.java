package com.brycen.hrm.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.brycen.hrm.entity.CompanyEntity;
import com.brycen.hrm.repository.CompanyIRepository;

@Component
public class UtilFunction {
    
    @Autowired
    private Environment env;

    @Autowired
    private CompanyIRepository companyIRepository;

    public String generateCompanyCode(int companyID) {
        String companyCode = "";
        Optional<CompanyEntity> companyData = companyIRepository.findById(companyID);
        if (companyData.isPresent()) {
            companyCode = companyData.get().getCompanyCode().toUpperCase() + "-";
        }
        return companyCode;
    }

    public SessionFactory getCurrentSession() {
        // Hibernate 5.4 SessionFactory example without XML
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", env.getProperty("spring.datasource.driver-class-name"));
        settings.put("dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        settings.put("hibernate.connection.url", env.getProperty("spring.datasource.url"));
        settings.put("hibernate.connection.username", env.getProperty("spring.datasource.username"));
        settings.put("hibernate.connection.password", env.getProperty("spring.datasource.password"));
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        // metadataSources.addAnnotatedClass(Player.class);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        // Session session = sessionFactory.getCurrentSession();
        return sessionFactory;
    }
}

package com.test.test;

import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("test")
public class TruncateDatabaseService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void truncate(){
        Session session=entityManager.unwrap(Session.class);
        Metamodel metamodel=session.getSessionFactory().getMetamodel();

        List<String> tableNames=metamodel.getEntities().stream()
                .map(entityType -> entityType.getName().toLowerCase())
                .collect(Collectors.toList());

        entityManager.flush();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = FALSE").executeUpdate();
        tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = TRUE").executeUpdate();

    }
}

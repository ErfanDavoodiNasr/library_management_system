package com.github.util;

import lombok.experimental.UtilityClass;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@UtilityClass
public class EntityManagerProvider {
    private static EntityManagerFactory emf;

    public static synchronized EntityManager getEntityManager(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("jdbc-postgres");
        }
        return emf.createEntityManager();
    }
}

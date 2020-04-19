package org.example.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmUtil {

    private static final String NAME = "stasInduk";
    private final EntityManagerFactory emFatory;
    private final EntityManager em;

    public EmUtil(){
        emFatory = Persistence.createEntityManagerFactory(NAME);
        em = emFatory.createEntityManager();
    }

    public void close(){
        if(em != null)  em.close();
        if(emFatory != null) emFatory.close();
    }


    public EntityManager getEm() {
        return em;
    }
}

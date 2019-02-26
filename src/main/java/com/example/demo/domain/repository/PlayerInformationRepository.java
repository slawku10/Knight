package com.example.demo.domain.repository;

import com.example.demo.domain.PlayerInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class PlayerInformationRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createPlayerInformation(PlayerInformation playerInformation) {
        em.persist(playerInformation);
    }

    public PlayerInformation getPlayerInformation() {
        return em.createQuery("from PlayerInformation", PlayerInformation.class).getResultList().get(0);
    }

}

package com.example.demo.domain.repository;

import com.example.demo.domain.Knight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


public class DBKnightRepository implements KnightRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void createKnight(String name, int age) {
        Knight knight = new Knight(name, age);
        em.persist(knight);
    }

    @Override
    public Collection<Knight> getAllKnights() {
        return em.createQuery("from Knight", Knight.class).getResultList();
    }

    @Override
    public Optional<Knight> getKnight(String name) {
        Knight knight = em.createQuery("from Knight k where k.name=:name", Knight.class).setParameter("name", name).getSingleResult();
        return Optional.ofNullable(knight);
    }

    @Override
    @Transactional
    public void deleteKnight(int id) {
     em.remove(id);
    }

    @Override
    public void build() {
        System.out.println("Używam bazy danych");
    }

    @Override
    @Transactional
    public void createKnight(Knight knight) {
        em.persist(knight);
    }

    @Override
    public Knight getKnightById(Integer id) {
         return em.find(Knight.class , id);
    }

    @Override
    @Transactional
    public void updateKnight(int id, Knight knight) {
        em.merge(knight);
    }
}

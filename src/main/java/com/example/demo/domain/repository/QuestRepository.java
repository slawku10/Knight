package com.example.demo.domain.repository;

import com.example.demo.domain.Quest;
import com.example.demo.utils.Ids;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class QuestRepository {
    Random random = new Random();

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createQuest(String description) {

        Quest newQuest = new Quest(description);
        em.persist(newQuest);
        System.out.println(newQuest);

    }

    public List<Quest> getAll() {
        return em.createQuery("from Quest", Quest.class).getResultList();
    }

    @Transactional
    public void deleteQuest(Quest quest) {
        em.remove(quest);
    }

    @Scheduled(fixedDelay = 50000)
    @Transactional
    public void createRandomQuest() {
        List<String> descriptions = new ArrayList<>();
        descriptions.add("Uratuj księżniczke");
        descriptions.add("Zabij smoka");
        descriptions.add("Weź udział w turnieju");
        descriptions.add("Zabij bande goblinów");

        String description = descriptions.get(random.nextInt(descriptions.size()));
        createQuest(description);
    }

    @Transactional
    public void update(Quest quest) {
        em.merge(quest);
    }

    public Quest getQuest(Integer id) {
        return (Quest) em.find(Quest.class, id);
    }
}

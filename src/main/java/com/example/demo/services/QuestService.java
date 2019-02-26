package com.example.demo.services;


import com.example.demo.domain.Knight;
import com.example.demo.domain.Quest;
import com.example.demo.domain.repository.KnightRepository;
import com.example.demo.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestService {

    @Autowired
    KnightRepository knightRepository;


    QuestRepository questRepository;

    final static Random RANDOM = new Random();

    public void assignRandomQuest(String knightName) {
        List<Quest> allQuest = questRepository.getAll();
        Quest randomQuest = allQuest.get(RANDOM.nextInt(allQuest.size()));
        knightRepository.getKnight(knightName).ifPresent(knight -> knight.setQuest(randomQuest));
//        questRepository.deleteQuest(randomQuest);

    }

    public List<Quest> getAllNotStartedQuests() {
        return questRepository.getAll().stream().filter(quest -> !quest.isStarted()).collect(Collectors.toList());

    }
    @Autowired
    public void setQuestRepository(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public boolean isQuestCompleted(Quest quest){
        return quest.isCompleted();
    }
}

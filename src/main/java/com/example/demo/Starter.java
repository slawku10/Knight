package com.example.demo;

import com.example.demo.domain.PlayerInformation;
import com.example.demo.domain.repository.KnightRepository;
import com.example.demo.domain.repository.PlayerInformationRepository;
import com.example.demo.domain.repository.QuestRepository;
import com.example.demo.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    KnightRepository knightRepository;
    @Autowired
    QuestRepository questRepository;

    @Autowired
    QuestService questService;

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Override
    @Transactional
    public void run(String...  args) throws Exception {


        questRepository.createRandomQuest();
        questRepository.createRandomQuest();
        knightRepository.createKnight("Percival",30);

        playerInformationRepository.createPlayerInformation(new PlayerInformation());

        questService.assignRandomQuest("Percival");
    }
}

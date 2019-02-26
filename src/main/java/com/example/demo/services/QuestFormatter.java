package com.example.demo.services;

import com.example.demo.domain.Quest;
import com.example.demo.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;


@Service
public class QuestFormatter implements Formatter<Quest> {
    @Autowired
    QuestRepository questRepository;

    @Override
    public Quest parse(String idAssStr, Locale locale) throws ParseException {
        Integer id = Integer.parseInt(idAssStr);
        return questRepository.getQuest(id);
    }

    @Override
    public String print(Quest quest, Locale locale) {
        return quest.toString();
    }
}

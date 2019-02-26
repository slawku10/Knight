package com.example.demo.controllers;

import com.example.demo.domain.Knight;
import com.example.demo.domain.PlayerInformation;
import com.example.demo.domain.Quest;
import com.example.demo.services.KnightService;
import com.example.demo.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestController {
    @Autowired
    private KnightService knightService;

    @Autowired
    QuestService questService;


    @RequestMapping("/assignQuest")
    public String assignQuest(@RequestParam("knightId") Integer knightId, Model model) {
        Knight knight = knightService.getKnight(knightId);
        List<Quest> notStartedQuest = questService.getAllNotStartedQuests();
        model.addAttribute("knight", knight);
        model.addAttribute("notStartedQuest", notStartedQuest);
        return "assignQuest";
    }

    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String assignQuest(Knight knight) {
        knightService.updateKnight(knight);
        Quest quest = knight.getQuest();
        questService.update(quest);
        return "redirect:/knights";
    }

    @RequestMapping(value = "/checkQuests", method = RequestMethod.GET)
    public String checkQuests() {
        knightService.getMyGold();
        return "redirect:/knights";
    }
}

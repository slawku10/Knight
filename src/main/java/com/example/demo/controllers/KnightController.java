package com.example.demo.controllers;

import com.example.demo.components.TimeComponent;
import com.example.demo.domain.Knight;
import com.example.demo.domain.PlayerInformation;
import com.example.demo.domain.repository.KnightRepository;
import com.example.demo.domain.repository.PlayerInformationRepository;
import com.example.demo.services.KnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller

public class KnightController {

    @Autowired
    TimeComponent timeComponent;

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Autowired
    KnightService service;

    @RequestMapping("/knights")
    public String getKnights(Model model) {
        List<Knight> allKnights = service.getAllKnights();
        model.addAttribute("knights", allKnights);
        model.addAttribute("hello", "Witaj Świecie");
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformationRepository.getPlayerInformation());
        return "knights";
    }

    @RequestMapping("/newknight")
    public String createKnight(Model model) {
        model.addAttribute("knight", new Knight());
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformationRepository.getPlayerInformation());
        return "knightform";
    }

    @RequestMapping(value = "/knights", method = RequestMethod.POST)
    public String saveKnights(@Valid Knight knight, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach
                    (error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    });
            return "knightform";
        } else {
            service.saveKnight(knight);
            return "redirect:/knights";
        }
    }

    @RequestMapping("/knight")
    public String getKnight(@RequestParam("id") Integer id, Model model) {
        Knight knight = service.getKnight(id);
        model.addAttribute("knight", knight);
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformationRepository.getPlayerInformation());
        return "knightdetails";
    }

    @RequestMapping("/knight/delete/{id}")
    public String deleteKnight(@PathVariable("id") Integer id, Model model) {
        service.deleteKnight(id);
        model.addAttribute("timecomponent", timeComponent);
        return "redirect:/knights";
    }
}

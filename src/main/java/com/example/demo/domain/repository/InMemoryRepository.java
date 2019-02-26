package com.example.demo.domain.repository;

import com.example.demo.domain.Knight;
import com.example.demo.utils.Ids;

import javax.annotation.PostConstruct;
import java.util.*;


public class InMemoryRepository implements KnightRepository {

    private Map<Integer, Knight> knights = new HashMap<>();

    public InMemoryRepository() {
    }

    @Override
    public void createKnight(String name, int age) {
        Knight newKnight = new Knight(name, age);
        newKnight.setId(Ids.getNewId(knights.keySet()));
        knights.put(newKnight.getId(), newKnight);
    }

    @Override
    public Collection<Knight> getAllKnights() {
        return knights.values();
    }

    @Override
    public Optional<Knight> getKnight(String name) {
        Optional<Knight> knightByName = knights.values().stream().filter(knight -> knight.getName().equals(name)).findAny();
        return knightByName;
    }

    @Override
    public void deleteKnight(int id) {
        knights.remove(id);
    }

    @Override
    @PostConstruct
    public void build() {
        createKnight("Lancelot", 29);
        createKnight("Percival", 25);
    }

    @Override
    public void createKnight(Knight knight) {
        createKnight(knight.getName(), knight.getAge());
    }

    @Override
    public Knight getKnightById(Integer id) {
        return knights.get(id) ;
    }

    @Override
    public void updateKnight(int id, Knight knight) {
        knights.put(id, knight);
    }

    @Override
    public String toString() {
        return "KnightRepository{" +
                "knights=" + knights +
                '}';
    }
}

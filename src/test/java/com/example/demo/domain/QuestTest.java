package com.example.demo.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QuestTest {
    @Test
    public void settingStartedFlagToFalseShouldSetStartDate(){
        Quest quest = new Quest(1, "Testowe zadanie");
        quest.setStarted(true);

        assertNotNull(quest.startDate);
    }

    @Test
    public void questShouldBeCompleted(){
        Quest quest = new Quest(2, "Testowe zadanie");
        quest.setStarted(true);
        quest.lengthInSeconds = -60;
        assertTrue(quest.isCompleted());
    }

    @Test
    public void questShouldNotBeCompleted(){
        Quest quest = new Quest(3, "Testowe zadanie");
        quest.setStarted(true);
        quest.lengthInSeconds = 60;
        assertFalse(quest.isCompleted());
    }
}

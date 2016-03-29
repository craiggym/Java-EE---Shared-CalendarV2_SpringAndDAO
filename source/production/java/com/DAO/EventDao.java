package com.DAO;

import com.Calendar.Event;
import com.Calendar.User;

import java.util.List;

public interface EventDao {
    void insertEvent(Event event);
    void createEventTable();
    void dropEventTable();
    boolean eventsExists(String username);
    boolean eventsExists();
    List<Event> selectAllEvent(String username);
    List<Event> selectAllEvent();
}

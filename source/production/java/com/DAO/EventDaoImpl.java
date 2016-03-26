package com.DAO;

import com.Calendar.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

public class EventDaoImpl implements EventDao{
    // class variables //
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    // methods //
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /***************************************************************
     * Title: insertEvent
     * Description: Add an event to the database
     * @param event An Event object
     ***************************************************************/
    @Override
    public void insertEvent(Event event) {
        String query = "insert into Event (id, eventName, eventDate, eventDescription, username) values (?,?,?,?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] inputs = new Object[] {event.getId(), event.getEventName(), event.getEventDate(), event.getDescription(), event.getUsername()};
        jdbcTemplate.update(query,inputs); // 'update' allows for non-static queries whereas execute wouldn't (e.g. '?')
    }
}
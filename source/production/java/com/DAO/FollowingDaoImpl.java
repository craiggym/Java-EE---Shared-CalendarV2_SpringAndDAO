package com.DAO;

import com.Calendar.Following;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

public class FollowingDaoImpl implements FollowingDao{
    // class variables //
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    // methods //
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /***************************************************************
     * Title: insertFollowing
     * Description: Add an event to the database
     * @param follow A Following object
     ***************************************************************/
    @Override
    public void insertFollowing(Following follow) {
        String query = "insert into Following (userID,eventID) values (?,?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] inputs = new Object[] {follow.getUserID(), follow.getEventID()};
        jdbcTemplate.update(query,inputs); // 'update' allows for non-static queries whereas execute wouldn't (e.g. '?')
    }
}

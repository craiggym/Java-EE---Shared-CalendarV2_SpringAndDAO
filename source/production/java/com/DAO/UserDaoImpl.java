package com.DAO;

import com.Calendar.User;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

public class UserDaoImpl implements UserDao{
    // class variables //
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    // methods //
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /***************************************************************
     * Title: insertUser
     * Description: Add a user to the database
     * @param user User object
     ***************************************************************/
    @Override
    public void insertUser(User user) {
        String query = "insert into User (username, e_mail, last_name, userID) values (?,?,?,?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] inputs = new Object[] {user.getUsername(),user.getE_mail(),user.getLast_name(),user.getUserID()};
        jdbcTemplate.update(query,inputs); // 'update' allows for non-static queries whereas execute wouldn't (e.g. '?')
    }
}
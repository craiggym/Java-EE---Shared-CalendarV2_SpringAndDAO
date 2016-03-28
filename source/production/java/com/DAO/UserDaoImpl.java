package com.DAO;

import com.Calendar.User;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

public class UserDaoImpl implements UserDao{
    // class variables //
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    boolean debug = true;

    // methods //
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createUserTable() {
        String query = "CREATE TABLE User(userID int, username VARCHAR(255) , e_mail VARCHAR(255), password VARCHAR(255), " +
                "first_name VARCHAR(255), last_name VARCHAR(255));";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(query);
    }

    @Override
    public void dropUserTable() {
        String query = "DROP TABLE User;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(query);
    }

    /***************************************************************
     * Title: insertUser
     * Description: Add a user to the database
     * @param user User object
     ***************************************************************/
    @Override
    public void insertUser(User user) {
        String query = "insert into User (userID, username, e_mail, password, first_name, last_name) values (?,?,?,?,?,?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] inputs = new Object[] {user.getUserID(), user.getUsername(),user.getE_mail(), user.getPassword(), user.getFirst_name(), user.getLast_name()};
        jdbcTemplate.update(query,inputs); // 'update' allows for non-static queries whereas execute wouldn't (e.g. '?')
    }


    @Override
    public String selectUser(int id) {
        String query = "SELECT username FROM User WHERE userID=?";
        Object[] input = new Object[] {id};
        jdbcTemplate = new JdbcTemplate(dataSource);
        String username = (String)jdbcTemplate.queryForObject(query, input, String.class);
        return username;
    }

    @Override
    public boolean userExists(String username) {
        try {
            String query = "SELECT username FROM User WHERE username=?";
            Object[] input = new Object[]{username};
            jdbcTemplate = new JdbcTemplate(dataSource);
            String uname = (String) jdbcTemplate.queryForObject(query, input, String.class);

            if(debug) {
                System.out.println("result of query: " + uname);
                System.out.println("User exists");
            }
            return true;
        }
        catch(Exception e){
            if (debug) System.out.println("User does not exist");
            return false;
        }
    }

    @Override
    public boolean isAuthCorrect(String username, String password) {
        try {
            String query = "SELECT username FROM User WHERE username=?" + " AND password=?";
            Object[] input = new Object[]{username,password};
            jdbcTemplate = new JdbcTemplate(dataSource);
            String q_result = (String) jdbcTemplate.queryForObject(query, input, String.class);

            if(debug)System.out.println("Authentication for " + q_result + " correct!");
            return true;
        }
        catch(Exception e){
            if (debug) System.out.println("Authentication incorrect");
            return false;
        }
    }
}
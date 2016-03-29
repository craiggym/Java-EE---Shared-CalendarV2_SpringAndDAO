package com.Calendar;

import com.DAO.EventDao;
import com.DAO.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by craig on 2/25/16.
 */
@WebServlet(
        name = "RegisterServlet",
        urlPatterns = {"/register"}
)
public class RegisterServlet extends HttpServlet {
    // Variables //
    int idCount=0;
    boolean debug=true;
    private static String appContextFile = "AppContext.xml"; // Use the settings from this xml file
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addUser(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        userRegister(request,response);
    }

    /************************************************************************
     * Title: goHome
     * Description: The index home page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***********************************************************************/
    private void goHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/view/browse.jsp")
                .forward(request, response);
    }

    /************************************************************************
     * Title: addUser
     * Description: The index home page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***********************************************************************/
    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(appContextFile); // New AppContext pointing to xml config
        UserDao userDao = (UserDao) context.getBean("userDao");
        HttpSession session = request.getSession(true);

        try { // catch occurs when HSQLDB is not established
            // First check if username is unique otherwise throw fail
            String username = request.getParameter("username");
            if (!userDao.userExists(username)) { //User doesn't exist. Proceed with user add:
                // Take rest of parameters from form and set them in local variables //
                String e_mail = request.getParameter("e_mail");
                String pass = request.getParameter("pass");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");


                // Create new user and set the attributes using local variables. //
                User user = new User();
                user.setUserID(idCount++);
                user.setUsername(username);
                user.setE_mail(e_mail);
                user.setPassword(pass);
                user.setFirst_name(fname);
                user.setLast_name(lname);
                userDao.insertUser(user); // Inserts the user into HSQLDB table

                session.setAttribute("duplicate", "false");
                request.getRequestDispatcher("/WEB-INF/jsp/view/registerSuccess.jsp")
                        .forward(request, response);
            } else { // Username exists in the database
                session.setAttribute("duplicate", "true");

                request.getRequestDispatcher("/WEB-INF/jsp/view/register.jsp")
                        .forward(request, response);
            }
        }
        catch (CannotGetJdbcConnectionException e){
            e.printStackTrace();
            System.out.println("Database connection could not be established");
            request.getRequestDispatcher("/WEB-INF/jsp/view/databaseError.jsp")
                    .forward(request, response);
        }
    }

    /************************************************************************
     * Title: userRegister
     * Description: Brings user to the register page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***********************************************************************/
    private void userRegister(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException
    {
        // Fresh instance of web application. Clear out the data and re-establish tables //
        if (idCount == 0) {
            UserDao userDao = (UserDao) context.getBean("userDao");
            EventDao eventDao = (EventDao) context.getBean("eventDao");
            try {
                try {
                    userDao.dropUserTable();
                } catch (Exception e) {
                    System.out.println("There is no user table to drop");
                }
                userDao.createUserTable();
                if (debug) System.out.println("User table cleared");
            }
            catch(CannotGetJdbcConnectionException e){
                e.printStackTrace();
                System.out.println("Database connection could not be established");
                request.getRequestDispatcher("/WEB-INF/jsp/view/databaseError.jsp")
                        .forward(request, response);
            }
        }
        // End procedure of clearing data //

        request.getRequestDispatcher("/WEB-INF/jsp/view/register.jsp")
                .forward(request, response);
    }

}

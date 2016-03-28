package com.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
//Added for proj 2//
import com.DAO.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


@WebServlet(
        name = "HomeServlet",
        urlPatterns = {"/home","/loginservlet"}
)
public class HomeServlet extends HttpServlet
{
    // Variables //
    private static int idCount = 0;
    private static final Map<String, String> userDatabase = new Hashtable<>();
    boolean debug = true;

    static {
        userDatabase.put("test", "t3st");
        userDatabase.put("a", "aa");
    }

    /*****************************************************
     * doPost Method
     * Goes directly to doGet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ****************************************************/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    /****************************************************
     * doGet Method
     * Handles user requests relating to logging in, logging out, adding, and going home
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***************************************************/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action == null)
            action = "goHome";

        switch(action)
        {
            case "userAdd":
                this.userAdd(request, response);
                break;
            case "login":
                this.login(request, response);
                break;
            case "logout":
                this.logout(request, response);
                break;
            case "goHome":
                this.goHome(request, response);
                break;

            default:
                this.goHome(request, response);
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("user");
        String password=request.getParameter("pass");
        if(username == null || password == null ||
                !HomeServlet.userDatabase.containsKey(username) ||
                !password.equals(HomeServlet.userDatabase.get(username)))
        {
            if(!HomeServlet.userDatabase.containsKey(username) ||
                    !password.equals(HomeServlet.userDatabase.get(username)))
                request.getRequestDispatcher("/WEB-INF/jsp/view/browseIncorrect.jsp")
                        .forward(request, response);

            request.getRequestDispatcher("/WEB-INF/jsp/view/browse.jsp")
                    .forward(request, response);
        }
        else
        {
            HttpSession session = request.getSession(true);
            String id=session.getId();
            session.setAttribute("username",username);
            response.sendRedirect("event");
        }
    }
    /*********************************************************
     *logout
     * Logs the user out
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ********************************************************/
    private void logout(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
    {
        //======================= SORTING CODE ===================== //
        EventServlet.allEvents = new ArrayList<>();
        if(EventServlet.eventDatabase != null) {
            for (String name : EventServlet.eventDatabase.keySet()) {
                List<Event> e = EventServlet.eventDatabase.get(name);// grab all values for key
                for (int i = 0; i < e.size(); i++) // Iterate through the list for each key user
                    EventServlet.allEvents.add(e.get(i)); // Gobble gobble
            }
        }
        if(EventServlet.eventArrayList != null || EventServlet.eventArrayList.size() > 1) {
            Collections.sort(EventServlet.eventArrayList, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    if(o1.getMonthWeight() == o2.getMonthWeight()){
                        return o1.getDateWeight()-o2.getDateWeight();
                    }
                    return o1.getMonthWeight()-o2.getMonthWeight();
                }
            });
        }
        //======================================================================


        HttpSession session = request.getSession(false);
        session.invalidate();//to invalidate the session
        request.getRequestDispatcher("/WEB-INF/jsp/view/logout.jsp")
                .forward(request, response);
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
        //request.setAttribute("Created",EventServlet.Created);
        request.setAttribute("allEvents",EventServlet.eventDatabase);
        request.getRequestDispatcher("/WEB-INF/jsp/view/browse.jsp")
                .forward(request, response);
    }

    /*********************************************************************
     * Title: userAdd
     * Description: Adds user to "database"
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***********************************************************************/
    private void userAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // Take parameters from form and set them in local variables //
        String username = request.getParameter("username");
        String e_mail = request.getParameter("e_mail");
        String pass = request.getParameter("pass");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");

        String acFile = "AppContext.xml"; // Use the settings from this xml file
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(acFile); // New AppContext pointing to xml config
        UserDao userDao = (UserDao) context.getBean("userDao");


        if(idCount == 0) { // Fresh instance of web application
            userDao.dropUserTable(); // Drop the user table
            if(debug) System.out.println("User table Dropped");
            userDao.createUserTable(); // Create new instance of the user table
            if(debug) System.out.println("User table created");
        }

        // Create new user and set the attributes using local variables. //
        User user = new User();
        user.setUserID(idCount++);
        user.setUsername(username);
        user.setE_mail(e_mail);
        user.setPassword(pass);
        user.setFirst_name(fname);
        user.setLast_name(lname);
        userDao.insertUser(user); // Inserts the user into HSQLDB table
        if(debug) System.out.println("\nAdded user: " + userDao.selectUser(idCount-1)); // Check database

        request.getRequestDispatcher("/WEB-INF/jsp/view/registerSuccess.jsp")
                .forward(request, response);
    }
}

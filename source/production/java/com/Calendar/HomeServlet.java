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
import com.DAO.EventDao;
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
    private static final Map<String, String> userDatabase = new Hashtable<>();
    boolean debug = true;
    private static String appContextFile = "AppContext.xml"; // Use the settings from this xml file
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");

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

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        HttpSession session = request.getSession(true);
        UserDao user = (UserDao) context.getBean("userDao");
        session.setAttribute("auth", "true");

        if(username == null) { // Accessing the page via link without logging in
            request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
                    .forward(request, response);
        }

        // AUTHENTICATION //
        if(user.isAuthCorrect(username, password)) // authenticate through database
        {
            session.setAttribute("username", username);
            session.setAttribute("first_name", user.selectFirstName(username));
            request.getRequestDispatcher("/welcome")
                    .forward(request, response);
        }
        else
        {
            session.setAttribute("auth","false");
            request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
                    .forward(request, response);
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
        HttpSession session = request.getSession(true);
        session.setAttribute("auth", "null"); // Incorrect auth message doesn't need to be shown


        request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
                .forward(request, response);
    }
}

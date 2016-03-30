package com.Calendar;

import com.DAO.EventDao;
import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "EventServlet",
        urlPatterns = {"/welcome", "/event"})
public class EventServlet extends HttpServlet {
    public static Calendar date = Calendar.getInstance();
   // public static Map<Integer, Event> eventDatabase = new LinkedHashMap<>();
    //public static Map<Integer, Event> personalDatabase = new LinkedHashMap<>();

    //public static Map<String, LinkedList<Event>> eventDatabase = new HashMap<>();
    //public static LinkedList<Event> eventLinkedList = new LinkedList<>();
    public static Map<String, List<Event>> eventDatabase = new HashMap<>();
    public static List<Event> eventArrayList = new ArrayList<>();
    public static List<Event> allEvents;
    int id=0;
    private static String appContextFile = "AppContext.xml"; // Use the settings from this xml file
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
    private static boolean debug = true;
    /************************************
     * doPost
     * Create and view events
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "viewAll";

        switch (action) {
            case "create": // Go to form
                this.createEventPage(request, response);
                break;
            case "add_event": // Coming from form
                this.addEvent(request, response);
                break;
            case "likedEvent": // Coming from homepage 'like'
                this.likedEvent(request,response);
                break;
            default: // View the user's personal page
                this.userHome(request, response);
                break;
        }
    }

    /************************************
     * doGet
     * View events
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***************************************/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("home");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";
        switch (action) {
            default:
                this.userHome(request, response);
                break;
        }
    }

    /*********************************************************
     * CreateEventPage
     * Allow the user to create events
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ********************************************************/
    private void createEventPage(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException
        {
            request.getRequestDispatcher("/WEB-INF/jsp/view/createEvent.jsp")//to create an event
                    .forward(request, response);
        }
    /*********************************************************
     * addEvent
     * Allow the user to create events
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ********************************************************/
    private void addEvent(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        // Taken from HTML form
        HttpSession session = request.getSession(false);
        String eventName = request.getParameter("eventName");
        String eventDescription = request.getParameter("Description");
        String username = (String) session.getAttribute("username");
        String author = username;

        // Parsing the date passed from the HTML form //
        String string = request.getParameter("month"); // Passed from HTML
        String[] parser = string.split("_"); // Parse using the indicator
        String parsedMonth = parser[1]; // Take what we want
        string = request.getParameter("date"); // Repeat for date...
        parser = string.split("_");
        String parsedDate = parser[1];
        string = request.getParameter("year"); // Repeat for year...
        parser = string.split("_");
        String parsedYear = parser[1];

        // Result:
        String eventDate =  parsedMonth + "-" + parsedDate + "-" + parsedYear;
        Date eventDateFormatted = new Date();
        try {
            eventDateFormatted = new SimpleDateFormat("MM-dd-yyyy").parse(eventDate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Event createdNewEvent = new Event(id++, eventName, eventDateFormatted, eventDescription, username, author); // Create event object

        // Access database to add event
        EventDao eventDao = (EventDao) context.getBean("eventDao");
        eventDao.insertEvent(createdNewEvent);

        request.getRequestDispatcher("/WEB-INF/jsp/view/userPersonal.jsp")//User's Home page
                .forward(request, response);
    }

    /*********************************************************
     * likedEvent
     * Add the liked event to the users events
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ********************************************************/
    private void likedEvent(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        // Taken from HTML form
        HttpSession session = request.getSession(false);
        int it = Integer.parseInt(request.getParameter("it")); // Parsed from HTML form
        List<Event> event = (List<Event>) session.getAttribute("eventsList");

        int eventID = event.get(it).getId();
        String eventName = event.get(it).getEventName();
        Date eventDate = event.get(it).getEventDate();
        String eventDescription = event.get(it).getDescription();
        String username = (String) session.getAttribute("username");
        String author = event.get(it).getEventAuthor();

        Event createdNewEvent = new Event(eventID, eventName, eventDate, eventDescription, username, author); // Create event object

        EventDao eventDao = (EventDao) context.getBean("eventDao");
        eventDao.insertEvent(createdNewEvent);

        if(debug){
            System.out.printf("Following:\nID:%d\nTitle:%s\nDate:%s\nDesc:%s\nusername:%s\nauthor:%s\n", eventID,eventName,eventDate,eventDescription,username,author);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/view/userPersonal.jsp")//User's Home page
                .forward(request, response);
    }

    /*********************************************************
     *userHome
     * userHome page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ********************************************************/
    private void userHome(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
         {
             HttpSession session = request.getSession(false);
             EventDao eventDao = (EventDao) context.getBean("eventDao");
             // Fresh instance of web application. Clear out the data and re-establish tables //
             if(id == 0) {
                 try {
                     eventDao.dropEventTable();
                 } catch (Exception e) {
                     System.out.println("There is no Event table to drop");
                 }
                 eventDao.createEventTable();
                 if (debug) System.out.println("Event table cleared");
             }

        request.getRequestDispatcher("/WEB-INF/jsp/view/userPersonal.jsp")//User's Home page
                .forward(request, response);
        }
}

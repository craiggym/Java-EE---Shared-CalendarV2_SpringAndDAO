<%@ page import="com.Calendar.Event" %>
<%@ page import="java.util.*" %>
<%@ page import="com.Calendar.EventServlet" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Incorrect Authentication</title>
        <%    @SuppressWarnings("unchecked")
        List<Event> allEvents = EventServlet.allEvents;%>
    </head>
    <body>
        <h1>Event page</h1>
        <p style="color:darkred">Incorrect username/password</p>
        <h2><a href="register">Link to Register</a></h2>
    <form action="home?action=login" method="POST">
       Username: <input type="text" name="user"/><br>
       Password: <input type="password" name="pass"/><br>
        <input type="submit" value="login"/><br>

        <br>
        <hr><hr>
    </form>
        <% // Display message if the user has no events
            if(allEvents == null)
        %><h3>There are no events created</h3> <br/><p><em>Create one or follow one from the All Events page!</em><p></p>
        <%
            if(allEvents != null){
                for(int i = 0; i < allEvents.size(); i++){
        %>
        Event: <%= allEvents.get(i).getEventName() %> <br/>
        Date: <%= allEvents.get(i).getEventDate() %> <br/>
        Description: <%= allEvents.get(i).getDescription() %> <br/>
        User: <%= allEvents.get(i).getUsername() %> <br/>
        EventID: <%= allEvents.get(i).getId() %> <br/>

        <% if(session.getAttribute("username") !=null){%>
        <form action="event?action=add_event" method="POST">
            <input type="submit" value="Like">
        </form>
        <%
            }%><br/><%
                }
            }
        %>

    </body>
</html>

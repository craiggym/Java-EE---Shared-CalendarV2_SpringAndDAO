<%@ page import="java.util.*" %>
<%@ page import="com.Calendar.Event" %>
<%@ page import="com.Calendar.EventServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
            <%    @SuppressWarnings("unchecked")
    List<Event> allEvents = EventServlet.allEvents;%>
    </head>
    <body>
        <h1>Event page</h1>

        <% if(session.getAttribute("username")==null){%>
            <h2><a href="register">Link to Register</a></h2>
            <form action="home?action=login" method="POST">
             Username: <input type="text" name="user"/><br>
             Password: <input type="password" name="pass"/><br>
             <input type="submit" value="login"/><br>
        <%}else{%>
             <form action="event?create_event" method="POST">
             <input type="submit" Value="User Page">
             </form>
            <form action="home?action=logout" method="POST">
                <input type="submit" value="Log out"><br/>
            </form>
        <%}%>

        <br>
        <hr><hr>

        <br/>
        <hr/>
                    <% // Display message if the user has no events
   if(allEvents == null)
%><h3>There are no events created</h3> <br/><p><em>Create one or follow one from the All Events page!</em><p></p>
                    <%
                    if(allEvents != null){
                    for(int i = 0; i < allEvents.size(); i++){
                    %>
                Events: <%= allEvents.get(i).getEventName() %> <br/>
                Date: <%= allEvents.get(i).getEventDate() %> <br/>
                Description: <%= allEvents.get(i).getDescription() %> <br/>
                User: <%= allEvents.get(i).getUsername() %> <br/>
                EventID: <%= allEvents.get(i).getId() %> <br/>

                <% if(session.getAttribute("username") !=null){%>
                <form action="event?action=likedEvent" method="POST">
                    <input type="hidden" name="it" value="<%= i %>"/>
                    <input type="submit" value="Like">
                </form>
                    <%
                    }%><br/><%
}
    }
%>


    </body>
</html>

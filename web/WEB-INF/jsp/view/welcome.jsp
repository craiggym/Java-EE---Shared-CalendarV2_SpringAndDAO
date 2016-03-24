<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.Calendar.Event" %>
<%@ page import="java.util.List" %>

<%
    @SuppressWarnings("unchecked")
    Map<String, List<Event>> eventDatabase =
            (Map<String, List<Event>>)request.getAttribute("eventDatabase");

%>
<html>
<head>
    <title>User Homepage</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css"/>
</head>
<body>


<h1>Welcome <%=session.getAttribute("username")%></h1>
<br/>
<br/>

<% // Display message if the user has no events
    List<Event> checkForNull = eventDatabase.get(session.getAttribute("username"));
    if(checkForNull == null || checkForNull.isEmpty()){
%><h3>Not subscribed to any events!</h3> <br/><p><em>Create one or follow one from the All Events page!</em><p></p><%
}
else
{
    List<Event> e = eventDatabase.get(session.getAttribute("username"));// grab all values for key
    for(int i = 0; i < e.size(); i++)
    {

        {
            String eventName = e.get(i).getEventName();
            String eventDate = e.get(i).getEventDate();
            String eventDesc = e.get(i).getDescription();
            String eventUser = e.get(i).getUsername();
            int eventID = e.get(i).getId();
%>
Event: <%= eventName %> <br/>
Date: <%= eventDate %> <br/>
Description: <%= eventDesc %> <br/>
User: <%= eventUser %> <br/>
EventID: <%= eventID %> <br/>
<br />
<hr/>
<%
            }
        }
    }
%>

<br/>

<form action="event?action=create" method="POST">
    <input type="submit" value="Create Event"><br/>
</form>
<form action="home?action=logout" method="POST">
    <input type="submit" value="Log out"><br/>
</form>

<form action="event?action=viewAll" method="POST">
    <input type="submit" value="Event Page">
</form>
<br/>


</body>
</html>
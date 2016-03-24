package com.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by craig on 2/25/16.
 */
@WebServlet(
        name = "RegisterServlet",
        urlPatterns = {"/register"}
)
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/home?action=userAdd")
                .forward(request, response);
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
     * Title: goSuccess
     * Description: The index home page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     ***********************************************************************/
    private void goSuccess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/?action=userAdd")
                .forward(request, response);
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
        request.getRequestDispatcher("/WEB-INF/jsp/view/register.jsp")
                .forward(request, response);
    }

}

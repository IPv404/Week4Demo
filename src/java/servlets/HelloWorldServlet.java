package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Person;

/**
 *
 * @author Zeina Mint
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //The welcome page requested 
        
        getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //variables
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        
        
        
        
        //If first or last name doesnt exist, resend form and end
        if (firstname == null || firstname.equals("") || lastname == null || lastname.equals(""))
        {
                //setting variables
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            
            //message
            
            //for classic
            //request.setAttribute("message", "Invalid Entry!");
            
            //for jstl 
            request.setAttribute("invalid", true);
            
            //reset form
            getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp")
                .forward(request, response);
            
            //end so next line does not run
            return;
        }  
        
        
        Person person = new Person(firstname, lastname); 
        request.setAttribute("person", person);
        
        
        
        //Valid names sent to sayHello
        getServletContext().getRequestDispatcher("/WEB-INF/sayHello.jsp")
                .forward(request, response);
        
    }
}

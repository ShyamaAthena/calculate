

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html"); // informing the client that which format of data/response will be send
		PrintWriter out= response.getWriter(); 
		
		//All data entered into a "TEXT" field of form, is stored as "STRING"
		//Hence if we enter "1" in a "TEXT" field of form, it will be stored as "ONE"
		//To convert it to "INT", we use a predefined function called= "parseInt()"
		int a1= Integer.parseInt(request.getParameter("n1")); 
		// using "getParameter()" to retrieve data entered by user in "n1" field
		int a2= Integer.parseInt(request.getParameter("n2")); 
		
		if (request.getParameter("r1")!=null) // checking if 1st radio button checked or not?
		{
			out.println("<h3> Addition= </h3>"+(a1+a2));
		}
		else if(request.getParameter("r2")!=null) // checking if 2nd radio button checked or not?
		{
			out.println("<h3> Subtraction= </h3>"+(a1-a2));
		}
		else if(request.getParameter("r3")!=null) //checking if 3rd radio button checked or not?
		{
			out.println("<h3> Multiply= </h3>"+(a1*a2));
		}
		else
		{
			out.println("<h3> Divide= </h3>"+(a1/a2));
		}		
	}
}

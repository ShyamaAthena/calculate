
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.logging.*;

import com.sforce.ws.*;
import com.sforce.soap.partner.*;
import com.sforce.soap.partner.sobject.SObject;


@SuppressWarnings("serial")

public class HelloWorldServlet extends HttpServlet {
 	//private static final Logger log = Logger.getLogger(helloworldServlet.class.getName());

	private String username = "your username";
	private String password = "your password";
	private PartnerConnection connection;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("Hello, world. this is a test2");
		username=req.getParameter("n1");
		password=req.getParameter("n2");
		PrintWriter t = resp.getWriter();
		getConnection( t, req);
		if ( connection == null ) { return; }

		QueryResult result = null;
		
		try {
		
			result = connection.query(  "select id, name, phone from Account order by LastModifiedDate desc limit 10 ");
		
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (SObject account: result.getRecords()) {
		     t.println("<li>"+ (String)account.getField("Name") + "</li>");
		}
	}
	
	void getConnection(PrintWriter out, HttpServletRequest req)  { 
		
		try { 
			// build up a ConnectorConfig from a sid
			String sessionid = req.getParameter("sid");
			String serverurl = req.getParameter("srv");
			
			if ( connection == null ) { 
				
			   out.println("<p>new connection needed</p>");
			   // login to the Force.com Platform
			   ConnectorConfig config = new ConnectorConfig();
			   if ( sessionid!= null && serverurl!= null) {
				   config.setServiceEndpoint(serverurl);
				   config.setSessionId(sessionid);
				   config.setManualLogin(false);
				   out.println("using session from query string");
			   }   else { 
				   config.setUsername(username);
				   config.setPassword(password);
			   }
			   connection = Connector.newConnection(config);
			   out.println( connection.getConfig().getSessionId() );
			   out.println( connection.getConfig().getServiceEndpoint() );
		   } else { 
			   out.println("<p> reuse existing connection"); 
			   out.println( connection.getConfig().getSessionId() );
		   }
		 //  log.warning("Connection SID " +connection.getConfig().getSessionId());

		} catch ( ConnectionException ce) {
			//log.warning("ConnectionException " +ce.getMessage());
			
			out.println( ce.getMessage() + " s " + ce.getClass() );

		}
			
	}
}

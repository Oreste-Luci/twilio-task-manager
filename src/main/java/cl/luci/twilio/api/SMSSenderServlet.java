package cl.luci.twilio.api;

import cl.luci.twilio.dom.SMSMessage;
import cl.luci.twilio.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet is the Endpoint for creating a new task and sending the SMS.
 * @author Oreste Luci
 */
public class SMSSenderServlet extends HttpServlet {

    @Autowired
    private SmsService smsService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String nextJSP = "/WEB-INF/jsp/confirmation.jsp";
        String outMessage = "Message Sent Successfully";

        try {

            // Validating input parameters
            String name = request.getParameter("name");
            if (name==null || name.trim().length()==0) {
                throw new Exception("Error: Must specify name!");
            }

            String to = request.getParameter("phone");
            if (to==null || to.trim().length()==0) {
                throw new Exception("Error: Must specify phone number!");
            }

            String message = request.getParameter("message");
            if (message==null || message.trim().length()==0) {
                throw new Exception("Error: Must specify message content!");
            }

            // Creating new message/task
            SMSMessage smsMessage = new SMSMessage();
            smsMessage.setName(name);
            smsMessage.setPhone(to.trim());
            smsMessage.setBody(message.trim());

            smsService.createNewTask(smsMessage);

        } catch (Exception e) {
            // Generating error message for view
            outMessage = e.getMessage();
            request.setAttribute("error","true");
        } finally {
            // Sending response to view
            request.setAttribute("outMessage",outMessage);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
        }
    }
}

package cl.luci.twilio.api;

import cl.luci.twilio.dom.SMSMessage;
import cl.luci.twilio.service.SmsService;
import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Endpoint for receiving SMS
 * @author Oreste Luci
 */
public class SMSReceiverServlet extends HttpServlet {

    @Autowired
    private SmsService smsService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Getting SMS parameters
        String fromNumber = request.getParameter("From");
        String body = request.getParameter("Body");

        // Creating message/task
        SMSMessage smsMessage = new SMSMessage();
        smsMessage.setPhone(fromNumber);
        smsMessage.setBody(body);

        // Processing message
        String responseMessage = smsService.processResponse(smsMessage);

        // Creating message response
        TwiMLResponse twiml = new TwiMLResponse();
        Message sms = new Message(responseMessage);
        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}

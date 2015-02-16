package cl.luci.twilio.api;

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
 * This servlet obtains the task list and forwards to the corresponding view.
 * @author Oreste Luci
 */
public class DisplayTasksServlet extends HttpServlet {

    @Autowired
    private SmsService smsService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Placing list in request
        request.setAttribute("taskList",smsService.getTaskList());

        // Forwarding to view
        String nextJSP = "/WEB-INF/jsp/taskList.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
}

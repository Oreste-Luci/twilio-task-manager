package cl.luci.twilio.service;

import cl.luci.twilio.dao.SMSMessageDAO;
import cl.luci.twilio.dom.SMSMessage;
import cl.luci.twilio.dom.TaskStatusEnum;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Main service for providing SMS/Task functionalities.
 * @author Oreste Luci
 */
@Service("smsService")
public class SmsService {

    @Autowired
    private SMSMessageDAO smsMessageDAO;

    @Autowired
    private Properties configurationProperties;

    /**
     * Creates a new task and sends the corresponding SMS
     * @param outMessage
     * @throws Exception
     */
    public void createNewTask(SMSMessage outMessage) throws Exception {

        // Removing spaces from phone number.
        outMessage.setPhone(outMessage.getPhone().replaceAll(" ", ""));

        TwilioRestClient client = new TwilioRestClient(configurationProperties.getProperty("twilio.account.sid"), configurationProperties.getProperty("twilio.auth.token"));

        Account account = client.getAccount();

        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", outMessage.getPhone()));
        params.add(new BasicNameValuePair("From", configurationProperties.getProperty("twilio.from.number")));
        params.add(new BasicNameValuePair("Body", outMessage.getBody()));

        Message sentMessage = messageFactory.create(params);

        outMessage.setCreated(new Date());
        outMessage.setStatus(TaskStatusEnum.NEW.value());

        smsMessageDAO.create(outMessage);
    }

    /**
     * Processes SMS answer.
     * @param inMessage
     * @return
     */
    public String processResponse(SMSMessage inMessage) {

        String responseMessage = null;

        try {

            // Getting existing Task/Message from storage
            String phoneNumber = inMessage.getPhone().replaceAll(" ", "");
            SMSMessage taskMessage = smsMessageDAO.findOldestNew(phoneNumber);

            // If message not found then no task is pending.
            if (taskMessage==null) {
                return "No task pending";
            }

            // Updating message/task with response
            String status = determineStatus(inMessage.getBody());
            taskMessage.setStatus(status);
            taskMessage.setUpdated(new Date());
            smsMessageDAO.update(taskMessage);

            // Determining message to be sent back by SMS
            responseMessage = taskMessage.getName();
            if (status.equalsIgnoreCase(TaskStatusEnum.ACCEPTED.toString())) {
                responseMessage += ", thank for accepting!!!";
            } else if (status.equalsIgnoreCase(TaskStatusEnum.REFUSED.toString())) {
                responseMessage += ", sorry you can't make it. Maybe next time.";
            } else {
                responseMessage += ", I don't understand your answer. Please type 'Yes' or 'No'.";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseMessage;
    }

    /**
     * Gets the list of all Task/Messages
     * @return
     */
    public List<SMSMessage> getTaskList() {
        return smsMessageDAO.findAll();
    }

    /**
     * Helper method for determining status from message body.
     * @param message
     * @return
     */
    private String determineStatus(String message) {

        if (message.equalsIgnoreCase("OK")
                || message.equalsIgnoreCase("KO")
                || message.equalsIgnoreCase("SI")
                || message.equalsIgnoreCase("YEAH")
                || message.equalsIgnoreCase("YES")) {

            return TaskStatusEnum.ACCEPTED.value();

        } else if (message.equalsIgnoreCase("NO")
                || message.equalsIgnoreCase("NOP")
                || message.equalsIgnoreCase("NEVER")
                || message.equalsIgnoreCase("N0")) {

            return TaskStatusEnum.REFUSED.value();

        } else {
            return TaskStatusEnum.UNDETERMINED.value();
        }
    }
}
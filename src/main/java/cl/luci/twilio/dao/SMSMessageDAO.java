package cl.luci.twilio.dao;

import cl.luci.twilio.dom.SMSMessage;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO for CRUD operations
 * @author Oreste Luci
 */
@Repository("smsMessageDAO")
public class SMSMessageDAO extends AbstractJpaDAO<SMSMessage> {

    public SMSMessageDAO(){
        setClazz(SMSMessage.class);
    }

    /**
     * Find the oldest unanswered or undetermined message.
     * @param phone phone number for message
     * @return oldest unanswered message or null in none available.
     */
    public SMSMessage findOldestNew(String phone) {

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from SMSMessage S WHERE (S.status = 'New' OR S.status = 'Undetermined') AND S.phone = :phoneNumber ORDER BY S.created ASC";
        Query query = session.createQuery(hql);
        query.setParameter("phoneNumber",phone);
        List<SMSMessage> l = query.list();
        tx.commit();
        session.close();

        if (l.size()==0) {
            return null;
        } else {
            return l.get(0);
        }
    }

}

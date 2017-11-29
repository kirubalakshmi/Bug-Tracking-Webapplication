package bugtracking.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author Kiruba
 */
//@Service
public class MailService {

    public static String SENDER_EMAIL = "kirubalakshmi@gmail.com";

    //@Autowired
    private MailSender mailSender;

    //@Autowired
    private TaskExecutor taskExecutor;

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(final String from, final String to, final String subject, final String msg) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(msg);
                mailSender.send(message);
            }
        });

    }
}

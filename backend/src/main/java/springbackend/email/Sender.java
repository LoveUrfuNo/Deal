package springbackend.email;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Пример отправки писем с помощью JavaMailSenderImpl через почтовые
 * сервис mail.ru и gmail.com. Убедитесь, что вы заменили mail.username, mail.password
 * и mail.from в файле конфигурации (src/main/resources/mail.ru.properties или
 * в src/main/resources/gmail.com.properties, смотря что включено
 * в applicationContext.xml)
 * на настройки своей учётной записи почты.
 */

public class Sender {

    public static void sendLetterRegistration() {

        try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {

            context.load("classpath:mail-messenger.xml");
            context.refresh();
            JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
            SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);

            // Создаём потокобезопасную копию шаблона.
            SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);

            mailMessage.setTo("andreykostyuchenko87@gmail.com");
            mailMessage.setText("Привет, товарищи. Присылаю вам письмо...");

            try {
                mailSender.send(mailMessage);
                System.out.println("Mail sended");
            } catch (MailException mailException) {
                System.out.println("Mail send failed.");
                mailException.printStackTrace();
            }
        }
    }
}

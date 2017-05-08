package springbackend.service;

import java.util.Map;

/**
 * Service for send email message
 */

public interface EmailService {
    boolean sendEmail(final String templateName, final Map<String, Object> model);

    String getNameFromEmailAddress(String email);

    String generateString(int length);
}

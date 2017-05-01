package springbackend.service;

import java.util.Map;

/**
 * Service for send email message
 */

public interface EmailService {
    public boolean sendEmail(final String templateName, final Map<String, Object> model);

    public String getNameFromEmailAddress(String email);

    public String generateString(int length);
}

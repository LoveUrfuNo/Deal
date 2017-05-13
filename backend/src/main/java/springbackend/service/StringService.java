package springbackend.service;

import java.io.UnsupportedEncodingException;

/**
 * Service for decoding string from jsp.
 */
public interface StringService {
    String decoding(String sourceString) throws UnsupportedEncodingException;

    String makePathForFile(String sourceString);
}

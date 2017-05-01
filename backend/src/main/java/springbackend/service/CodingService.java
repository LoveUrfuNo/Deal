package springbackend.service;

import java.io.UnsupportedEncodingException;

/**
 * Service for decoding string from jsp.
 */
public interface CodingService {
    String decoding(String sourceString) throws UnsupportedEncodingException;
}

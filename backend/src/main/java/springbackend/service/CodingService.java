package springbackend.service;

import java.io.UnsupportedEncodingException;

/**
 * Created by kosty on 01.05.2017.
 */
public interface CodingService {
    String decoding(String sourceString) throws UnsupportedEncodingException;
}

package springbackend.service.implementation;

import org.springframework.stereotype.Service;
import springbackend.service.CodingService;

import java.io.UnsupportedEncodingException;

/**
 * Implementation of {@link CodingService} interface.
 */

@Service
public class CodingServiceImpl implements CodingService {
    @Override
    public String decoding(String sourceString) throws UnsupportedEncodingException {
        return new String(sourceString.getBytes("ISO8859-1"), "UTF-8");
    }
}

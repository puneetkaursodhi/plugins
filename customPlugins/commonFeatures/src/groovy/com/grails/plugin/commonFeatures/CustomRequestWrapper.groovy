package com.grails.plugin.commonFeatures
import groovy.util.logging.Log4j
import org.apache.commons.io.IOUtils

import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
/**
 * Class which is used to wrap a request in order that the wrapped request's input stream can be
 * read once and later be read again in a pseudo fashion by virtue of keeping the original payload
 * as a string which is actually what is returned by subsequent calls to getInputStream().
 */
@Log4j
public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private final String xmlPayload;

    public CustomRequestWrapper(HttpServletRequest request) {

        super(request);

        // read the original payload into the xmlPayload variable
        String string = ""
        BufferedReader bufferedReader = null;
        try {
            // read the payload into the StringBuilder
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                string = IOUtils.toString(inputStream, "UTF-8")
            }
        } catch (Exception ex) {
            log.error("Error reading the request payload", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException iox) {
                    // ignore
                }
            }
        }
        xmlPayload = string;
    }

    /**
     * Override of the getInputStream() method which returns an InputStream that reads from the
     * stored XML payload string instead of from the request's actual InputStream.
     */
    @Override
    public ServletInputStream getInputStream()
            throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlPayload.getBytes());
        ServletInputStream inputStream = new ServletInputStream() {
            public int read()
                    throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return inputStream;
    }

}

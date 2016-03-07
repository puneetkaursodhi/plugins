package com.grails.plugin.validAPI

import groovy.util.logging.Log4j

@Log4j
class ValidRestCallHelper {
    public static def getJSONFromUrl(String urlString) {
        HttpURLConnection conn = null
        def response = null
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            response = processResponse(conn)
        } catch (MalformedURLException e) {
            log.error(e.cause + e.message)
        } catch (IOException e) {
            log.error(e.cause + e.message)
        } finally {
            conn.disconnect();
        }
        return response
    }

    private static def processResponse(HttpURLConnection conn) {
        StringBuilder output = null
        BufferedReader br = null
        if (conn.responseCode >= 400) {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))
        } else {
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        }
        String line = null;
        output = new StringBuilder()
        while ((line = br.readLine()) != null) {
            output.append(line);
        }
        return output.toString()
    }
}

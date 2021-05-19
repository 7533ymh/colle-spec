package com.example.backend.config;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Component
public class EncodeFileName {

    public String getBrowser(HttpServletRequest request) {

        String header = request.getHeader("User-Agent");

        if (header.contains("MSIE")) {

            return "MSIE";

        } else if (header.contains("Trident")) {   // IE11 문자열 깨짐 방지

            return "Trident";

        } else if (header.contains("Chrome")) {

            return "Chrome";

        } else if (header.contains("Opera")) {

            return "Opera";

        } else if (header.contains("Safari")) {

            return "Safari";

        }

        return "Firefox";

    }


    public String getEncodeFileName(HttpServletRequest request ,String filename) throws UnsupportedEncodingException {
        String browser = getBrowser(request);

        String encodedFilename = null;

        if (browser.equals("MSIE")) {

            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

        } else if (browser.equals("Trident")) {       // IE11 문자열 깨짐 방지

            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

        } else if (browser.equals("Firefox")) {

            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

            encodedFilename = URLDecoder.decode(encodedFilename);

        } else if (browser.equals("Opera")) {

            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

        } else if (browser.equals("Chrome")) {

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < filename.length(); i++) {

                char c = filename.charAt(i);

                if (c > '~') {

                    sb.append(URLEncoder.encode("" + c, "UTF-8"));

                } else {

                    sb.append(c);

                }

            }

            encodedFilename = sb.toString();

        } else if (browser.equals("Safari")){

            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";

            encodedFilename = URLDecoder.decode(encodedFilename);

        }

        else {

            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";

        }

        return encodedFilename;

    }

}

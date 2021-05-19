package com.example.backend.global.config;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Service
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

        String encodedFilename;

        switch (browser) {
            case "MSIE":

                encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

                break;
            case "Trident":        // IE11 문자열 깨짐 방지

                encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

                break;
            case "Firefox":

                encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

                encodedFilename = URLDecoder.decode(encodedFilename);

                break;
            case "Opera":

                encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

                break;
            case "Chrome":

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

                break;
            case "Safari":

                encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

                encodedFilename = URLDecoder.decode(encodedFilename);

                break;
            default:

                encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

                break;
        }

        return encodedFilename;

    }

}

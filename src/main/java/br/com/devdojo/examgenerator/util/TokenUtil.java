package br.com.devdojo.examgenerator.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Arrays.asList;

public class TokenUtil {
    //Método para validação do token

    public String getTokemFromCookies(HttpServletRequest request){
        if(request.getCookies() == null) return "";
        List<Cookie> cookieList = asList(request.getCookies());
        return getCookieByKey(cookieList, "token");
    }

    //Método para verificar se a data do cookie é válida

    public boolean isExpirationTimeFromCookieValid(HttpServletRequest request){
        if(request.getCookies() == null) return false;
        List<Cookie> cookieList = asList(request.getCookies());
        String expirationTime = getCookieByKey(cookieList, "expirationTime");
        return validateIfTimeNowIsBeforeTokenExpires(expirationTime);
    }

    private String getCookieByKey(List<Cookie> cookieList, String key) {
        return cookieList.stream()
                .filter(cookie -> cookie.getName().equals(key))
                .map(Cookie::getValue)
                .findFirst()
                .orElse("");
    }

//    public boolean isExpirationTimeFromCookieValid(HttpServletRequest request){
//        if(request.getCookies() == null) return "false";
//        List<Cookie> cookieList = asList(request.getCookies();
//        String expirationTime = cookieList.stream()
//                .filter(cookie -> cookie.getName().equals("expirationTime"))
//                .map(Cookie::getValue)
//                .findFirst()
//                .orElse("");
//        return validateIfTimeNowIsBeforeTokenExpires(expirationTime);
//    }

    private boolean validateIfTimeNowIsBeforeTokenExpires(String expirationTime){
         if(expirationTime.isEmpty()) return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .withZone(ZoneId.of("UTC"));
        LocalDateTime tokenExpirationTime = LocalDateTime.parse(expirationTime, formatter);
        return LocalDateTime.now(ZoneId.of("UTC")).isBefore(tokenExpirationTime);
    }
}

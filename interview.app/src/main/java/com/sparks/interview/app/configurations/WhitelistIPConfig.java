//package com.sparks.interview.app.configurations;
//
//import java.util.Set;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WhitelistIPConfig implements AuthenticationProvider {
//
//
//    Set<String> whitelist = Set.of("11.11.11.11", "12.12.12.12");
//
//    @Override
//    public Authentication authenticate(Authentication auth) throws AuthenticationException {
//        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
//        String userIp = details.getRemoteAddress();
//        if (!whitelist.contains(userIp)) {
//            throw new BadCredentialsException("Invalid IP Address");
//        }
//        return auth;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//
//}

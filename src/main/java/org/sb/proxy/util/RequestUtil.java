package org.sb.proxy.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

public class RequestUtil {
    public static String getUsernameFromRequest() {
        // Check user in the authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> attributes = null;
        String username = null;
        if (authentication instanceof OAuth2AuthenticationToken) {
            attributes = ((OAuth2AuthenticationToken) authentication).getPrincipal().getAttributes();
        } else if (authentication instanceof JwtAuthenticationToken) {
            attributes = ((JwtAuthenticationToken) authentication).getTokenAttributes();
        }
        assert attributes != null;
        username = (String) attributes.get("preferred_username");
        System.out.println(attributes);
        return username;
    }
}

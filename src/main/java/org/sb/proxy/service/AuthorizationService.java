package org.sb.proxy.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthorizationService {

    String userName;
    try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> details = ((JwtAuthenticationToken) authentication).getTokenAttributes();
        String username = (String) details.get("preferred_username");

    }
}

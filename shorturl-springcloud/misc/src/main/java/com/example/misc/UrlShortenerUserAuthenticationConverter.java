package com.example.misc;

import com.example.sharedentity.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UrlShortenerUserAuthenticationConverter implements UserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        @SuppressWarnings("unchecked")
        Map<String, Object> userMap = (Map<String, Object>) map.get("user");
        User user = new User();
        user.setId(Integer.parseInt((String) userMap.get("id")));
        user.setUsername((String) userMap.get("username"));
        user.setAdmin(Boolean.valueOf((String) userMap.get("admin")));
        user.setEmail((String) userMap.get("email"));
        List<GrantedAuthority> authorities = user.getAdmin() ?
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN")) : Collections.emptyList();
        return new UsernamePasswordAuthenticationToken(new UrlShortenerUserDetails(user), "N/A", authorities);
    }
}

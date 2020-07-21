package com.example.adminservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.security.oauth2")
public class ResourceServerConfigurationProperties {
    private Client client;
    private Authorization authorization;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public static class Client {
        private String clientId;
        private String clientSecret;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    public static class Authorization {
        private String checkTokenAccess;

        public String getCheckTokenAccess() {
            return checkTokenAccess;
        }

        public void setCheckTokenAccess(String checkTokenAccess) {
            this.checkTokenAccess = checkTokenAccess;
        }
    }
}

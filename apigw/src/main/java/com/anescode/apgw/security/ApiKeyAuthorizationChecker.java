package com.anescode.apgw.security;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String key, String application);
}

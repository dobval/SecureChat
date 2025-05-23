package com.dobval.SecureChat.security;

import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(
        org.springframework.http.server.ServerHttpRequest request,
        org.springframework.web.socket.WebSocketHandler wsHandler,
        Map<String, Object> attributes
    ) {
        String username = (String) attributes.get("user");
        return () -> username; // simple Principal implementation
    }
}

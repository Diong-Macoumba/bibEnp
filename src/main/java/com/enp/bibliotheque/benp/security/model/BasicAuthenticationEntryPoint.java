package com.enp.bibliotheque.benp.security.model;

import com.enp.bibliotheque.benp.constants.StringConstant;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class BasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Gson gson;

    public BasicAuthenticationEntryPoint(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Content-Type", "application/json");

        response.addHeader("Access-Control-Allow-Headers", "session");
        response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,PATCH");
        response.addHeader("Access-Control-Allow-Origin", "*");

        response.getWriter().println(gson.toJson(new HashMap<String, String>(){{
            put(StringConstant.RESPONSE_MSG, "Invalid Credentials");
        }}));
    }
}

package com.enp.bibliotheque.benp.security.services;

import com.enp.bibliotheque.benp.security.entity.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;

import static com.enp.bibliotheque.benp.constants.JwtProperties.*;
import static java.util.Arrays.stream;

@Component
public class JwtTokenProvider {

    public String generateToken(UserPrincipal user) {

        String[] claims = getClaimsFromUser(user);

        return JWT.create().withIssuer(GET_ARRAY_LCC).withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public List<GrantedAuthority> getAuthorities(String token) {

        String[] claims = getClaimsFromToken(token);

        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    private String[] getClaimsFromToken(String token) {

        JWTVerifier jwtVerifier =  getJwtVerifier();

        return jwtVerifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJwtVerifier() {

        JWTVerifier verifier;

        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRET);

            verifier = JWT.require(algorithm).withIssuer(GET_ARRAY_LCC).build();
        }
        catch (JWTVerificationException e) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        List<String> authorities = new ArrayList();

        for (GrantedAuthority authority : userPrincipal.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authenticationToken;
    }


    public Boolean isTokenValid( String username, String token) {
        JWTVerifier jwtVerifier = getJwtVerifier();
        return StringUtils.isNotEmpty(username) && istokenExpired(jwtVerifier, token);
    }

    private boolean istokenExpired(JWTVerifier verifier, String token) {

        Date expiration = verifier.verify(token).getExpiresAt();

        return expiration.before(new Date());
    }

    public String getSubject( String token) {

        JWTVerifier verifier = getJwtVerifier();

        return verifier.verify(token).getSubject();
    }

}

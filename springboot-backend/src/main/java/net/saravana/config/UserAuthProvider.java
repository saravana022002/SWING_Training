package net.saravana.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.saravana.dto.UserDto;
import net.saravana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
@PropertySource("classpath:application.yml")
public class UserAuthProvider {

    @Autowired
    private Environment environment;
    private String secretKey;
    private UserService userService;


    @PostConstruct
    protected void init(){
        secretKey = environment.getProperty("security.jwt.token.secret-key", "secret-value");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(String login){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);
        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }
    public Authentication validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .build();
        DecodedJWT decoded = verifier.verify(token);
        UserDto user = userService.findByLogin(decoded.getIssuer());
        return  new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}

package dcc.tp2.gatewayservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity

public class SecConfig {

    private RsaConfig rsaConfig;

    public SecConfig(RsaConfig rsaConfig) {
        this.rsaConfig = rsaConfig;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(auth -> auth
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/ENSEIGNANT-SERVICE/Enseignants/email/{id}").permitAll()
                        .pathMatchers("/ENSEIGNANT-SERVICE/Enseignants/**").hasAuthority("SCOPE_Enseignant")
                        .pathMatchers("/CHERCHEUR-SERVICE/Chercheurs/email/{id}").permitAll()
                        .pathMatchers("/CHERCHEUR-SERVICE/Chercheurs/**").hasAuthority("SCOPE_Chercheur")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }


    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        // Ensure that rsaConfig.publicKey() returns a valid PublicKey
        return NimbusReactiveJwtDecoder.withPublicKey(rsaConfig.publicKey()).build();
    }


}

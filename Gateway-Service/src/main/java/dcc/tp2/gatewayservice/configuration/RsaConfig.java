package dcc.tp2.gatewayservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties(prefix = "rsa")

public record RsaConfig(RSAPublicKey publicKey ) {
}

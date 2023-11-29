package com.openclassrooms.chatop.configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "rsa")
@Data
public class RsaKeyProperties {

	private RSAPublicKey publicKey;

	private RSAPrivateKey privateKey;

}

package com.smhj.UserService;

import com.smhj.UserService.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	JpaRegisteredClientRepository jpaRegisteredClientRepository;

	@Test
	void contextLoads() {
	}

	//@Test
//	void addClient(){
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("oidc-client")
//				.clientSecret("$2a$12$jsncoFDNuUquy4uNWpOwIe4khw/pmRGyiNYDrs594ZUvO3w7GSTPq")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.redirectUri("https://oauth.pstmn.io/v1/callback")
//				.postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//				.scope(OidcScopes.OPENID)
//				.scope(OidcScopes.PROFILE)
//				.scope("ADMIN")
//				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//				.build();
//
//		jpaRegisteredClientRepository.save(oidcClient);
//	}

}

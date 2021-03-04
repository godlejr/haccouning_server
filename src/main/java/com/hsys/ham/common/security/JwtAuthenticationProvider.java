package com.hsys.ham.common.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.hsys.ham.common.UserFlag;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * json web token내 메시지(유저 레벨) 검증 및 권한 부여 클래스
 * 
 * @author 김동주 사원
 * @since 2019.03.27
 * 
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {
	@Value("#{jwt['jwt.principal']}")
	private String PRINCIPAL;

	@Value("#{jwt['jwt.credentials']}")
	private String CREDENTIALS;

	@Value("#{jwt['jwt.secretKey']}")
	private String SECRET_KEY;

	@Value("#{jwt['jwt.message']}")
	private String message;

	@Value("#{jwt['jwt.tokenKey']}")
	private String tokenKey;

	@Value("#{jwt['jwt.expirationTime']}")
	private long EXPIRATION_TIME;

	private String secretValue;

	/**
	 * 유저 시크릿 키 별 인증 후 권한 부여 함수
	 * 
	 * claims로 부터 유저 시크릿 키값 추출 및 권한 부여(ham 사용자)
	 * 
	 * @author 김동주 사원
	 * @since 2019.03.27
	 * 
	 * @param authentication
	 * 
	 * @throws AuthenticationException
	 * 
	 * @return Authentication
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		try {
			secretValue = getValueWithToken(token);

			//expandExpiration(token);
				
		} catch (Exception e) {
			throw new AuthenticationServiceException(message);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		// secretValue를 통해 추루 권한 및 분기 처리

		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		authentication = new UsernamePasswordAuthenticationToken(PRINCIPAL, CREDENTIALS, grantedAuthorities);

		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	/**
	 * 엑세스 토큰(claims)에서 유저 시크릿키값 검출 함수
	 * 
	 * @author 김동주 사원
	 * @since 2019.03.27
	 * 
	 * @param token
	 * @return String
	 */
	public String getValueWithToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return (String) claims.get(tokenKey);
	}

	public String expandExpiration(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

		System.out.println("갱신 전 시간 확인:" + claims.getExpiration());
		Date exDate = new Date();

		exDate.setTime(exDate.getTime() + EXPIRATION_TIME);

		claims.setExpiration(exDate);

		System.out.println("갱신 후 시간 확인:" + claims.getExpiration());

		return (String) claims.get(tokenKey);
	}

}
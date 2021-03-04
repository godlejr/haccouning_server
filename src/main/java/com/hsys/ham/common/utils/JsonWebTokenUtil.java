package com.hsys.ham.common.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * json web token 관련 서비스 클래스
 * 
 * JWT Encryption & Decription
 * 
 * @author 김동주 사원
 * 
 * @since 2019.03.27
 */

@Component
public class JsonWebTokenUtil {

	@Value("#{jwt['jwt.secretKey']}")
	private String SECRET_KEY;

	@Value("#{jwt['jwt.expirationTime']}")
	private long EXPIRATION_TIME;

	/**
	 * 엑세스 토큰과 키를 통해 hidden 벨류 추출 함수
	 * 
	 * @author 김동주 사원
	 * @since 2019.03.27
	 * 
	 * @param token
	 * @param key
	 * 
	 * @return String
	 */
	public String getValueWithTokenAndKey(String token, String key) {
		Claims body = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return (String) body.get(key);
	}

	/**
	 * 키벨류 조합을 담은 Claim과 시크릿 키로 엑세스 토큰 생성 함수
	 * 
	 * SignatureAlgorithm.HS512 암호화 알고리즘 통해 sign 적용
	 * 
	 * @author 김동주 사원
	 * @since 2019.03.27
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return String
	 */
	public String setTokenWithKeyAndValue(String key, String value) {
		Claims claims = Jwts.claims().setSubject("ham");
		claims.put(key, value);
		Date exDate = new Date();

		exDate.setTime(exDate.getTime() + EXPIRATION_TIME);

		return Jwts.builder().setClaims(claims).setExpiration(exDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}

}

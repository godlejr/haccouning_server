package com.hsys.ham.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.hsys.ham.common.utils.sms.HamSms;

/**
 * Luis Web service call 클래스
 * 
 * SMS를 luis 서버 처리 후 반환
 * 
 * @author 김동주 사원
 * 
 * @since 2019.03.27
 */

@Component
public class LuisWebServiceUtil {

	@Value("#{luis['luis.scheme']}")
	private String LUIS_SERVER_SCHEME;

	@Value("#{luis['luis.url']}")
	private String LUIS_SERVER_URL;

	@Value("#{luis['luis.url.path']}")
	private String LUIS_SERVER_URL_PATH;

	@Value("#{luis['luis.verbose']}")
	private String LUIS_VERBOSE;

	@Value("#{luis['luis.timezoneOffset']}")
	private String LUIS_TIMEZONE_OFFSET;

	@Value("#{luis['luis.subscription-key']}")
	private String LUIS_SUBSCRIPTION_KEY;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Luis 서버 Get 호출 및 Respose 반환
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.11
	 * 
	 * @param message
	 * 
	 * @return HamSms
	 * @throws UnsupportedEncodingException 
	 */
	public HamSms getHamSmsByMachineLearing(String message) throws UnsupportedEncodingException {

		UriComponents  uriComponents = UriComponentsBuilder.newInstance().scheme(LUIS_SERVER_SCHEME).host(LUIS_SERVER_URL)
				.path(LUIS_SERVER_URL_PATH).queryParam("verbose", LUIS_VERBOSE)
				.queryParam("timezoneOffset", LUIS_TIMEZONE_OFFSET)
				.queryParam("subscription-key", LUIS_SUBSCRIPTION_KEY).queryParam("q", message).build().encode("UTF-8");
		
		//restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		HamSms hamSms = restTemplate.getForObject(uriComponents.toUri(), HamSms.class);
		
		return hamSms;
	}

}

package com.ll.codicaster.base.initData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import com.ll.codicaster.boundedContext.member.entity.Member;
import com.ll.codicaster.boundedContext.member.service.MemberService;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
	@Value("${custom.security.oauth2.client.registration.kakao.devUserOauthId}")
	private String kakaoDevUserOAuthId;

	@Value("${custom.security.oauth2.client.registration.naver.devUserOauthId}")
	private String naverDevUserOAuthId;

	@Bean
	CommandLineRunner initData(
		MemberService memberService
	) {
		return new CommandLineRunner() {
			@Override
			@Transactional
			public void run(String... args) throws Exception {
				Member memberAdmin = memberService.join("admin", "1234").getData();
				Member memberUser1 = memberService.join("user1", "1234").getData();
				Member memberUser2 = memberService.join("user2", "1234").getData();
				Member memberUser3 = memberService.join("user3", "1234").getData();
				Member memberUser4 = memberService.join("user4", "1234").getData();
				Member memberUser5 = memberService.join("user5", "1234").getData();

				Member memberUser6ByKakao = memberService.whenSocialLogin("KAKAO",
					"KAKAO__%s".formatted(kakaoDevUserOAuthId)).getData();
				Member memberUser8ByNaver = memberService.whenSocialLogin("NAVER",
					"NAVER__%s".formatted(naverDevUserOAuthId)).getData();
			}
		};
	}
}

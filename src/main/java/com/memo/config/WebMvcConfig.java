package com.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.common.FileManagerService;
import com.memo.interceptor.PermissionInterceptor;

@Configuration	// 설정을 위한 spring bean 
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private PermissionInterceptor interceptor;
	
	// 서버에 업로드 된 이미지와 웹 이미지 주소와의 매핑 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // 웹 이미지의 주소:http://localhost/images/....png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 위치 (윈도우는 /3개 맥은 /2개)
	}
	
	// interceptor 설정 추가
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(interceptor)
		.addPathPatterns("/**")	// "/**" <- 아래 디렉토리까지 모두 확인(모든 주소를 인터셉터로 적용한다)
		.excludePathPatterns("/favicon.ico", "/error", "/static/**", "/user/sign_out")
		
		;	
		
	}
	

}

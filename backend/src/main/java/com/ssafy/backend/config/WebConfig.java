package com.ssafy.backend.config;

import com.ssafy.backend.common.auth.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private JWTInterceptor jwtInterceptor;

	//[CORS 허용 관련]
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.maxAge(6000);
	}

	//[swagger ui 관련]
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	
	//[jwt 관련] 로그인 안해도 접근 가능한 url(excludePathPatterns)
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				// 모든 path 검증
				.addPathPatterns("/api/**")
				// swagger 관련 path 제외
				.excludePathPatterns("/api/swagger-ui/**")
				.excludePathPatterns("/api/v2/api-docs")
				.excludePathPatterns("/api/swagger-resources/**")
				.excludePathPatterns("/api/webjars/**")
				// 회원가입 및 로그인 관련 path 제외
				.excludePathPatterns("/api/register/**")
				.excludePathPatterns("/api/member/login");
	}
}

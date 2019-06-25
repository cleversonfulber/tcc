package br.edu.utfpr.tcc.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private LocalDateSerializer localDateSerializer;
	@Autowired
	private LocalDateDeserializer localDateDeserializer;

	private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		SimpleModule module = new SimpleModule("app", Version.unknownVersion());
		module.addSerializer(LocalDate.class, localDateSerializer);
		module.addDeserializer(LocalDate.class, localDateDeserializer);
		objectMapper.registerModule(module);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

	}

	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {

	}

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

	}

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {

	}

	@Override
	public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter());
		converters.add(customJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

	}

	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		return null;
	}

}

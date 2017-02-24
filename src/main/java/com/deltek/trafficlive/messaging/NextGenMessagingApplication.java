package com.deltek.trafficlive.messaging;

import org.granite.config.GraniteConfigListener;
import org.granite.gravity.servlet3.GravityAsyncServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deltek.trafficlive.messaging.security.CustomDestinationSecurizer;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@ImportResource("classpath:graniteds-config.xml")
public class NextGenMessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextGenMessagingApplication.class, args);
	}
	
	@Bean
	public GravityAsyncServlet asyncServlet() {
		return new GravityAsyncServlet();
	}
	
	@Bean
	public GraniteConfigListener graniteConfigListener() {
		return new GraniteConfigListener();
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(GravityAsyncServlet asyncServlet) {
	    ServletRegistrationBean registration = new ServletRegistrationBean(
	    		asyncServlet);
	    registration.setLoadOnStartup(1);
	    registration.setAsyncSupported(true);
	    registration.addUrlMappings("/feed/gravityamf/*");
	    return registration;
	}
	
	@Bean
	public CustomDestinationSecurizer customDestinationSecurizer() {
		return new CustomDestinationSecurizer();
	}
	
//	@Bean
//	public DispatcherServlet dispatcherServlet() {
//		return new DispatcherServlet();
//	}
//	
//	@Bean
//	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(
//	            dispatcherServlet);
//	    registration.addUrlMappings("/graniteamf/*");
//	    return registration;
//	}
//	
//	@Bean
//	public FilterRegistrationBean graniteFilterRegistration() {
//	    FilterRegistrationBean registration = new FilterRegistrationBean();
//	    registration.setFilter(amfMessageFilter());
//	    registration.addUrlPatterns("/graniteamf/*");
//	    registration.setName("AMFMessageFilter");
//	    return registration;
//	}
//	
//	@Bean
//	public AMFMessageFilter amfMessageFilter() {
//		return new AMFMessageFilter();
//	}
	
	@Controller
	public class GreetingController {
	    @RequestMapping("/greeting")
	    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
	        model.addAttribute("name", name);
	        return "greeting";
	    }		
	}
	
}

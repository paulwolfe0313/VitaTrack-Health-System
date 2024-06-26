package vitatrack.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("registration");
        registry.addViewController("/account").setViewName("account");
		registry.addViewController("/schedule-appointment").setViewName("bookappointment");
		registry.addViewController("/view-bills").setViewName("selectbill");
		registry.addViewController("/help").setViewName("help-page");
		registry.addViewController("/about").setViewName("about");
	}

}
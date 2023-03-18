package lesson37.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author spasko
 */
@EnableWebMvc
@Configuration
@ComponentScan("lesson37")
public class WebConfig extends AnnotationConfigWebApplicationContext {

}

package lesson37.config;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author spasko
 */
@EnableWebMvc
@Configuration
@ComponentScan("lesson37")
public class ConversionConfig extends AnnotationConfigWebApplicationContext {

    @Autowired
    private GenericConversionService conversionService;

    @Autowired
    private void converters(Set<Converter<?, ?>> converters) {
        converters.forEach(conversionService::addConverter);
    }
}

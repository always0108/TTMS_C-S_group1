package TTMS_Server.config.spring;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//配置加载非Web组件的Bean的ContextLoaderListener应用上下文的JavaConfig
@Configuration
@ComponentScan(basePackages = {"TTMS_Server"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}

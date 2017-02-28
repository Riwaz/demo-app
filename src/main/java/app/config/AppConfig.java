package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableMBeanExport
@Import({WebAppConfig.class, ServiceConfig.class})
public class AppConfig {
}

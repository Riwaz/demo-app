package app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import javax.servlet.*;
import java.util.EnumSet;



public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/rest/*"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("****** Application Context Initialization ******");
        
        servletContext.addListener(new RequestContextListener());
        super.onStartup(servletContext);

        /*initSecurityFilter(servletContext);
        servletContext.addListener(new RequestContextListener());
        servletContext.getSessionCookieConfig().setName("INBAS_JSESSIONID");
        super.onStartup(servletContext);*/
    }
    
    /*private void initSecurityFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain",
                DelegatingFilterProxy.class);
        final EnumSet<DispatcherType> es = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        securityFilter.addMappingForUrlPatterns(es, true, "/*");
    }*/

}

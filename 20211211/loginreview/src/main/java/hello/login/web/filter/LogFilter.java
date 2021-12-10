package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info(" >>> Filter Execute !! - init() method");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();
    String uuid = UUID.randomUUID().toString();

    try {
      log.info(" >>> Filter doFilter START!! REQUEST requestURI = {} , uuid = {}", requestURI, uuid);
      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e;
    } finally {
      log.info(" >>> Filter doFilter END!! RESPONSE requestURI = {}, uuid = {}", requestURI, uuid);
    }
  }

  @Override
  public void destroy() {
    log.info(" >>> Filter destroy Execute !!");
  }
}

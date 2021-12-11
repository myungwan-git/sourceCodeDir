package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

  public static final String LOG_ID = "logId";
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String requestURI = request.getRequestURI();
    String uuid = UUID.randomUUID().toString();
    request.setAttribute(LOG_ID, uuid);

    if (handler instanceof HandlerMethod) {
      //호출할 컨트롤러 메서드의 모든 정보를 담는다.
      HandlerMethod handlerMethod = (HandlerMethod) handler;
    }
    log.info(" >>> preHandle() uuid = {} ,  requestURI = {} , handler Info = {} ", uuid, requestURI, handler);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    log.info(" >>> postHandle() URI = {} , ModelAndView = {} ", request.getRequestURI(), modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    String requestURI = request.getRequestURI();
    String logId = (String) request.getAttribute(LOG_ID);

    log.info(" >>> afterCompletion() uuid = {} , requestURI = {} ", logId, requestURI);

    if (ex != null) {
      log.error(" >>> Interceptor afterCompletion ERROR !! = ", ex);
    }
  }


}
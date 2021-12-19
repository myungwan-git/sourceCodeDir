package hello.exceptionreview.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {

  @GetMapping("/error-ex")
  public void errorEx() {
    throw new RuntimeException("servlet RuntimeException 발생 !!");
  }

  @GetMapping("/error-404")
  public void error404(HttpServletResponse response) throws Exception{
    log.info("error-404 GetMapping @@@");
    response.sendError(404, "404 ERROR !!! ");
  }

  @GetMapping("/error-500")
  public void error500(HttpServletResponse response) throws Exception{
    response.sendError(500, "500 ERROR !!! ");
  }

  @GetMapping("/error/tf/{pathId}")
  public String errorPath(@PathVariable String pathId, HttpServletResponse response) throws IOException {
    System.out.println("pathId = " + pathId);
    if(pathId.equals("abc")) {
      log.info(" pathId is abc @@ Execute sendError");
      response.sendError(404, "404 ##########");
    }
    return "hello";
  }


}

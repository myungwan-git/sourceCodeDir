package hello.exceptionreview.api;

import hello.exceptionreview.exception.BadRequestException;
import hello.exceptionreview.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionController {

  @GetMapping("/api/members/{id}")
  public MemberDto getMember(@PathVariable("id") String id) {
    if (id.equals("ex")) {
      throw new RuntimeException("ex의 요청이 들어옴 -> 잘못된 사용자");
    }
    if (id.equals("bad")) {
      throw new IllegalArgumentException("bad의 요청이 들어옴 -> 나쁜경우");
    }
    if (id.equals("user-ex")) {
      throw new UserException("사용자 오류 UserException");
    }
    return new MemberDto(id, "hello " + id);
  }

  @GetMapping("/api/response-status-ex1")
  public String responseStatusEx1() {
    throw new BadRequestException();
  }

  @GetMapping("/api/response-status-ex2")
  public String responseStatusEx2() {
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "responseStatusException !! Not Annotation", new IllegalArgumentException());
  }

  @GetMapping("/api/default-handler-ex")
  public String defaultException(@RequestParam Integer data) {
    return "OK";
  }


  @Data
  @AllArgsConstructor
  static class MemberDto {
    private String memberId;
    private String name;
  }
}

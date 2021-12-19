package hello.exceptionreview.exhandler;

import hello.exceptionreview.api.ApiExceptionController;
import hello.exceptionreview.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {



  @GetMapping("/api2/members/{id}")
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


  @Data
  @AllArgsConstructor
  static class MemberDto {
    private String memberId;
    private String name;
  }
}

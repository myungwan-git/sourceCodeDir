package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @GetMapping("login")
  public String login(@ModelAttribute LoginForm form) {
    return "login/loginForm";
  }

  @PostMapping("login")
  public String loginForm(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request,
                          @RequestParam(defaultValue = "/") String redirectURL) {
    if (bindingResult.hasErrors()) {
      log.error(" >>> login try binding ERROR = {} ", bindingResult);
      return "login/loginForm";
    }
    Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
    log.info(" >>> login Information = {}", loginMember);

    if (loginMember == null) {
      bindingResult.reject("loginFail", "ID 또는 PASSWORD가 잘못되었습니다.");
      return "login/loginForm";
    }

    HttpSession session = request.getSession();
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    log.info(" >>> login SUCCESS = {} / session = {}", loginMember, session);
    return "redirect:" + redirectURL;
  }

  @PostMapping("/logout")
  public String logout(HttpServletResponse response, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    log.info(" >>> logout session expire !! ");
    return "redirect:/";
  }

  private void expireCookie(HttpServletResponse response, String cookieName) {
    Cookie cookie = new Cookie(cookieName, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }


}

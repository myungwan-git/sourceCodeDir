package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

  private final Map<Long, Member> store = new HashMap<>();
  private long sequence = 0L;

  public Member save(Member member) {
    log.info(" >>> save member = {}", member);
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  public Member findById(Long id) {
    Member member = store.get(id);
    return member;
  }

  public Optional<Member> findByLoginId(String loginId) {
    return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
  }

  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }
}

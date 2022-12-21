package core.aop.proxyvs;

import core.aop.member.MemberService;
import core.aop.member.MemberServiceImpl;
import core.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;
    //JDK 프록시는 MemberService 인터페이스를 기반으로 만들어지기 때문에 캐스팅이 가능하다.

    @Autowired
    MemberServiceImpl memberServiceImpl;
    //JDK 프록시는 MemberServie 인터페이스를 기반으로 만들어지기 때문에 MemberServiceImpl이 뭔지 모른다.

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }
}

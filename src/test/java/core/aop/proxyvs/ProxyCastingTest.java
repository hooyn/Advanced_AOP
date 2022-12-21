package core.aop.proxyvs;

import core.aop.member.MemberService;
import core.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.net.Proxy;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시

        //프록시를 인터페이스로 캐스팅 성공
        //인터페이스 기반으로 생성하기 때문에 성공한다.
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //JDK 동적 프록시를 구현 클래스로 캐스팅 실패, ClassCastException 예외 발생
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); //JDK 동적 프록시

        //프록시를 인터페이스로 캐스팅 성공
        //인터페이스 기반으로 생성하기 때문에 성공한다.
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //CGLIB 동적 프록시를 구현 클래스로 캐스팅 성공
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

        // CGLIB -> MemberServiceImpl -> MemberService의 구조이기 때문에
        // 상위 부모 객체로 타입 변환이 가능하다.
    }
}

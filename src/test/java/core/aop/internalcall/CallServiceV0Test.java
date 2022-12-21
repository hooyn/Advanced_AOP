package core.aop.internalcall;

import core.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        callServiceV0.external();
    }
    // 실행 결과를 보면 callServiceV0.external()을 실행할 때는 프록시를 호출한다.
    // 따라서 CallLogAspect 어드바이스가 호출된 것을 확인할 수 있다.
    // target.external()을 호출할 때 callServiceV0.external() 내부에 internal()을 호출하는데,
    // 이때는 CallLogAspect 어드바이스가 호출되지 않고 target의 인스턴스인 this.internal()이 실행되게 된다.
    // 결과적으로 이러한 내부 호출은 프록시를 거치지 않게 된다. 따라서 어드바이스도 적용할 수 없다.

    @Test
    void internal() {
        callServiceV0.internal();
    }
}
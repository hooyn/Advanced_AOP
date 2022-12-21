package core.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    //ApplicationContext 는 너무 큰 기능이기 때문에 특화된 기능인 ObjectProvider를 사용하는 것이 좋다.
    //private final ApplicationContext applicationContext;
    //public CallServiceV2(ApplicationContext applicationContext) {
    //    this.applicationContext = applicationContext;
    //}
    private final ObjectProvider<CallServiceV2> callServiceV2ObjectProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceV2ObjectProvider) {
        this.callServiceV2ObjectProvider = callServiceV2ObjectProvider;
    }

    public void external() {
        log.info("call external");

        //CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceV2ObjectProvider.getObject(); // 지연 조회

        callServiceV2.internal(); //외부 메서드 호출 (callServiceV1.internal());
    }

    public void internal() {
        log.info("call internal");
    }
}

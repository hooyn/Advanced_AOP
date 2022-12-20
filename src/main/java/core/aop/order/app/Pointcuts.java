package core.aop.order.app;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    //core.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* core.aop.order..*(..))")
    public void allOrder(){} //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}

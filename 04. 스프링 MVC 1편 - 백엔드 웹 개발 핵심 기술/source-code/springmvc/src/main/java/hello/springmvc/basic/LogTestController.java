package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    //@Slf4j와 동일한 역할
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        //log찍을 때 이렇게 사용하면 안된다(+연산을 쓰면 연산자가 먼저 실행이되서 메모리까지 할당하기때문에(출력을 안하더라도 할당함))
        log.info("info log= " + name);

        //log를 찍을 때 레벨을 정할 수 있다.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //디버그 할때 보는것
        log.info("info log={}", name); //정보를 보는것
        log.warn("warn log={}", name); // 경고 할때 보는것
        log.error("error log={}", name); // 에러날때 보는것

        return "ok";
    }
}

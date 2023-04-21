package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(

        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
//ComponentScan에 configuration을 뺀 이유는 앞서 appconfig.java파일을 수동으로 설정하였기때문에 중복되지 않으려고 Filter를 걸었다..
//ComponentScan의 default : ComponentScan을 붙인 클래스가 해당되는 패키지부터 하위 패키지까지 다 조회
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

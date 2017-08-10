package cn.niceabc.springbootadminclient;

import cn.niceabc.springbootadminclient.entity.CustomTraceManageer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.TraceProperties;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 自定义统计项
    /*@Bean
    public PublicMetrics customMetrics() {
        return new CustomMetrics();
    }*/

    // 自定义TraceFilter
    @Bean
    public IncludeBodyTraceFilter getTraceFilter(@Autowired CustomTraceManageer repository,
                                                 @Autowired TraceProperties properties,
                                                 @Autowired ObjectProvider<ErrorAttributes> errorAttributes) {
        return new IncludeBodyTraceFilter(repository, properties, errorAttributes);
    }

}

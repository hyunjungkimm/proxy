package hello.advanced;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace log() {
        return new ThreadLogTrace();
    }
}

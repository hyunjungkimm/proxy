package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {

        TraceStatus statue = null;

        try{
            statue = trace.begin("OrderRepository.save()");
            //저장 로직
            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            trace.end(statue);

        }catch (Exception e){
            trace.exception(statue, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package hello.proxy.postprocessor;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BeanPostProcessorTest {

    @Test
    void postProcessor() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanPostprocessorConfig.class);

        //beanA 이름으로 B 객체가 빈으로 등록된다.
        B b = ac.getBean("beanA", B.class);
        b.helloB();

        //A는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(A.class));
    }

    @Configuration
    static class BeanPostprocessorConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public BeanPostProcessor beanPostProcessor() {
            return new AToBProcessor();
        }
    }

    static class A {
        public void helloA() {
            log.info("helloA");
        }
    }

    static class B {
        public void helloB() {
            log.info("helloB");
        }
    }

    static class AToBProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);
            if(bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }
}

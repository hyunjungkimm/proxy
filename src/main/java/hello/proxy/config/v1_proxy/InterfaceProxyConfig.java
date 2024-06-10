package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderService(trace));
        return new OrderControllerInterfaceProxy(orderController, trace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderRepository(trace));
        return new OrderServiceInterfaceProxy(orderService, trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepository, trace);
    }
}

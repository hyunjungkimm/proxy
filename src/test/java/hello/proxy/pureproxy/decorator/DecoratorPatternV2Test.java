package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.codeV2.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternV2Test {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient component = new DecoratorPatternClient(realComponent);
        component.execute();
    }

    @Test
    void decorator1() {
        Component component = new RealComponent();
        MessageDecorator decorator = new MessageDecorator(component);
        DecoratorPatternClient client = new DecoratorPatternClient(decorator);
        client.execute();
    }

    @Test
    void decorator2() {
        Component component = new RealComponent();
        MessageDecorator decorator = new MessageDecorator(component);
        TimeDecorator timeDecorator = new TimeDecorator(decorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}

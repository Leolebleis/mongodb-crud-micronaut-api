package hello.world.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;


public class HelloClientSpec {

    @Inject
    HelloClient client;

    @Test
    public void testHelloWorldResponse() {
        assertEquals("Hello World", client.hello().blockingGet());
    }
    
}
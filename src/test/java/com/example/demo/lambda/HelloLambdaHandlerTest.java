package com.example.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HelloLambdaHandlerTest {

    private final HelloLambdaHandler handler = new HelloLambdaHandler();
    private Context context;

    @BeforeEach
    void setUp() {
        context = mock(Context.class);
        LambdaLogger logger = mock(LambdaLogger.class);
        lenient().when(context.getLogger()).thenReturn(logger);
    }

    @Test
    void returns200WithExpectedMessage() {
        APIGatewayV2HTTPEvent event = APIGatewayV2HTTPEvent.builder().build();

        APIGatewayV2HTTPResponse response = handler.handleRequest(event, context);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().contains("Hello Commit Academy!"));
    }

    @Test
    void responseIncludesExpectedHeaders() {
        APIGatewayV2HTTPEvent event = APIGatewayV2HTTPEvent.builder().build();

        APIGatewayV2HTTPResponse response = handler.handleRequest(event, context);

        assertEquals("application/json", response.getHeaders().get("Content-Type"));
        assertEquals("student-dsimon", response.getHeaders().get("x-app-name"));
    }
}

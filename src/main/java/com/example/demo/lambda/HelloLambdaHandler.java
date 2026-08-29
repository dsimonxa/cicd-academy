package com.example.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.example.demo.HelloResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler de AWS Lambda para el endpoint HTTP GET /api/web/hello.
 *
 * No modifica el código original entregado en demo.zip (HelloController,
 * HelloResponse, DemoApplication). Simplemente reutiliza el record
 * HelloResponse y reproduce exactamente el mismo mensaje y cabecera que
 * HelloController devolvía en la app Spring Boot original, adaptándolo
 * al modelo de integración proxy de API Gateway (HTTP API, payload 2.0).
 */
public class HelloLambdaHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("x-app-name", "student-dsimon");

        try {
            HelloResponse body = new HelloResponse("Hello Commit Academy!");
            String json = MAPPER.writeValueAsString(body);

            return APIGatewayV2HTTPResponse.builder()
                    .withStatusCode(200)
                    .withHeaders(headers)
                    .withBody(json)
                    .build();
        } catch (Exception e) {
            context.getLogger().log("Error serializando la respuesta: " + e.getMessage());
            return APIGatewayV2HTTPResponse.builder()
                    .withStatusCode(500)
                    .withHeaders(headers)
                    .withBody("{\"error\":\"internal_error\"}")
                    .build();
        }
    }
}

package com.redis.pubsub.integration;

import com.redis.pubsub.integration.annotation.IntegrationTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

@IntegrationTest
class PubSubResourceTest {

    @Test
    void shouldPublishMessageInRedis() {
        RestAssured
                .given()
                    .body("Hello word redis with spring")
                .when()
                    .post("/pubsub/publisher")
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }
}

package com.redis.pubsub.integration;

import com.redis.pubsub.integration.annotation.IntegrationTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@IntegrationTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

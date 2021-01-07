package com.redis.pubsub.resource;

import com.redis.pubsub.redis.MessagePublisher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/pubsub")
@RequiredArgsConstructor
public class PubSubResource {

    @Qualifier("redisPublisher")
    private final MessagePublisher publisher;

    @Operation(summary = "Publish message for consume in listener")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message publish with sucess",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid message for publish",
                    content = @Content)
    })
    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    void publisher(final
                   @RequestBody
                   @Valid
                   @NotBlank
                   @Schema(title = "message", name = "message", required = true, nullable = true, minProperties = 1)
                           String message) {
        publisher.publish(message);
    }

}

package gawlowski.maciej.example.config;

import lombok.*;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public final class ServiceProfile {

    public static final String LOCAL = "local";
    public static final String INTEGRATION_TEST = "integration-test";
    public static final String OTHER_THAN_INTEGRATION_TEST = "!integration-test";
}

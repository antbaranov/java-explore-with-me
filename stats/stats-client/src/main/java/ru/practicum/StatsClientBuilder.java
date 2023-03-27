package ru.practicum;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class StatsClientBuilder {
    private String serverUrl;
    private RestTemplateBuilder builder;
    private RestTemplate restTemplate;
    private String serverUrl1;

    public StatsClientBuilder setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public StatsClientBuilder setBuilder(RestTemplateBuilder builder) {
        this.builder = builder;
        return this;
    }

    public StatsClientBuilder setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        return this;
    }

    public StatsClientBuilder setServerUrl1(String serverUrl1) {
        this.serverUrl1 = serverUrl1;
        return this;
    }

    public StatsClient createStatsClient() {
        return new StatsClient(serverUrl, builder);
    }
}
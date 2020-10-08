package com.anands.virtual.helpers.zoom;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("app")
@Configuration
@Component
public class ApplicationProperties {
    private Zoom zoom;

    @Data
    public static class Zoom {
        private String clientId;
        private String clientSecret;
        private String redirectUrl;
    }

    public Zoom getZoom() {
        return zoom;
    }

    public void setZoom(Zoom zoom) {
        this.zoom = zoom;
    }
}

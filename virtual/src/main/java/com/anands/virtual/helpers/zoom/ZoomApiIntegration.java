package com.anands.virtual.helpers.zoom;

import com.anands.virtual.constants.MessageConstants;
import com.anands.virtual.exception.ResourceNotFoundException;
import com.anands.virtual.helpers.generic.MeetingDetailsHelper;
import com.anands.virtual.helpers.generic.TokenResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Component
public class ZoomApiIntegration {
    private static final Logger LOG = LogManager.getLogger(ZoomApiIntegration.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public WebClient getWebClient() {
        return (WebClient.builder().build());
    }

    ZoomDetails zoomDetails = new ZoomDetails();

    // https://marketplace.zoom.us/docs/guides/auth/oauth
    public TokenResponse callTokenApi(String oAuthToken) {

        TokenResponse tokenResponse = new TokenResponse();

        WebClient webClient = getWebClient();

        String originalString = applicationProperties.getZoom().getClientId() + ":" + applicationProperties.getZoom().getClientSecret();

        String authorizationToken = "Basic " + Base64.getEncoder().encodeToString(originalString.getBytes());

        System.out.println("Authorization = " + authorizationToken);

        String zoomUrl = zoomDetails.accessTokenUrlFromOAuth;
        zoomUrl = zoomUrl.replace("{oAuthCode}", oAuthToken);
        zoomUrl = zoomUrl.replace("{redirectUrl}", applicationProperties.getZoom().getRedirectUrl());

        System.out.println("URL = " + zoomUrl);

        ClientResponse clientResponse = webClient.post()
                .uri(zoomUrl)
                .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                .exchange()
                .block();

        if (clientResponse.statusCode().isError()) {
            LOG.error("Failed to get the accessToken from oAuth. Error code = {}", clientResponse.statusCode().value());
            throw new ResourceNotFoundException(MessageConstants.VC_UNABLE_TO_GENERATE_TOKEN);
        }

        Mono<TokenResponse> tokenResponseMono = clientResponse.bodyToMono(TokenResponse.class);
        TokenResponse newTokenResponse = tokenResponseMono.block();
        return newTokenResponse;
    }

    // https://marketplace.zoom.us/docs/api-reference/zoom-api/meetings/meetingcreate
    public ClientResponse callCreateMeetingApi(MeetingDetailsHelper meetingDetails, ZoomMeetingRequest zoomMeetingRequest, String accessToken) {

        WebClient webClient = getWebClient();

        String authorizationToken = "Bearer " + accessToken;

        String zoomCreateMeetingUrl = zoomDetails.createMeetingUrl;
        zoomCreateMeetingUrl = zoomCreateMeetingUrl.replace("{userId}", meetingDetails.getUserId());
        ClientResponse clientResponse = webClient.post()
                .uri(zoomCreateMeetingUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                .body(Mono.just(zoomMeetingRequest), ZoomMeetingRequest.class)
                .exchange()
                .block();

        return clientResponse;
    }

    public PersonalDetailsResponse callCurrentUserApi(String accessToken) {
        WebClient webClient = getWebClient();
        String authorizationToken = "Bearer " + accessToken;

        PersonalDetailsResponse personalDetailsResponse = new PersonalDetailsResponse();
        String userZoomDetailsUrl = zoomDetails.userZoomDetailsUrl;
        ClientResponse clientResponse = webClient.get()
                .uri(userZoomDetailsUrl)
                .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                .exchange()
                .block();

        Mono<PersonalDetailsResponse> personalDetailsResponseMono = clientResponse.bodyToMono(PersonalDetailsResponse.class);
        PersonalDetailsResponse newPersonalDetailsResponse = personalDetailsResponseMono.block();

        return newPersonalDetailsResponse;
    }

    public ClientResponse callUpdateMeetingApi(ZoomMeetingRequest zoomMeetingRequest,
                                               String meetingId,
                                               String accessToken) {
        WebClient webClient = getWebClient();

        String authorizationToken = "Bearer " + accessToken;

        String zoomUrl = zoomDetails.updateMeetingUrl;
        zoomUrl = zoomUrl.replace("{meetingId}", meetingId);

        ClientResponse clientResponse = webClient.patch()
                .uri(zoomUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                .body(Mono.just(zoomMeetingRequest), ZoomMeetingRequest.class)
                .exchange()
                .block();

        return clientResponse;
    }
}

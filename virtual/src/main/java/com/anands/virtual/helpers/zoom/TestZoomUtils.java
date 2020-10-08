package com.anands.virtual.helpers.zoom;

import com.anands.virtual.constants.MessageConstants;
import com.anands.virtual.exception.InvalidInputException;
import com.anands.virtual.exception.ResourceNotFoundException;
import com.anands.virtual.helpers.generic.MeetingDetailsHelper;
import com.anands.virtual.helpers.generic.TokenResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

@Component
public class TestZoomUtils {
    private static final Logger LOG = LogManager.getLogger(TestZoomUtils.class);

    private String type;

    @Autowired
    private ZoomApiIntegration zoomApiIntegration;

    public TestZoomUtils(String type) {
        this.type = type;
    }

    public TestZoomUtils() {
        this.type = "zoom";
    }

    public ZoomMeetingResponse createMeetingWithOAuth(MeetingDetailsHelper meetingDetails, String oAuth) {

        // Get accessToken and Refresh Token using OAuth
        TokenResponse newTokenResponse = zoomApiIntegration.callTokenApi(oAuth);
        if (isNull(newTokenResponse)) {
            LOG.error("No access token could be generated.");
            throw new InvalidInputException(MessageConstants.NO_OAUTH_CODE_FOUND);
        }

        ZoomMeetingRequest zoomMeetingRequest = new ZoomMeetingRequest();
        zoomMeetingRequest.setAgenda(meetingDetails.getAgenda());
        zoomMeetingRequest.setTopic(meetingDetails.getTopic());
        zoomMeetingRequest.setDuration(meetingDetails.getDuration());
        if (!isNull(meetingDetails.getPassword()))
            zoomMeetingRequest.setPassword(meetingDetails.getPassword());

        zoomMeetingRequest.setStartTime(meetingDetails.getStartDateTime());
        zoomMeetingRequest.setTimezone(meetingDetails.getTimezone());
        zoomMeetingRequest.setType(2);

        // Create meeting
        ClientResponse clientResponse = zoomApiIntegration.callCreateMeetingApi(meetingDetails, zoomMeetingRequest, newTokenResponse.getAccessToken());
        Mono<ZoomMeetingResponse> zoomMeetingResponse = clientResponse.bodyToMono(ZoomMeetingResponse.class);

        ZoomMeetingResponse meetingDetailsResponseToUse = zoomMeetingResponse.block();

        return (meetingDetailsResponseToUse);
    }

    public ZoomMeetingResponse createMeetingWithAccessToken(MeetingDetailsHelper meetingDetails, String accessToken) {

        ZoomMeetingRequest zoomMeetingRequest = new ZoomMeetingRequest();
        zoomMeetingRequest.setAgenda(meetingDetails.getAgenda());
        zoomMeetingRequest.setTopic(meetingDetails.getTopic());
        zoomMeetingRequest.setDuration(meetingDetails.getDuration());
        if (!isNull(meetingDetails.getPassword()))
            zoomMeetingRequest.setPassword(meetingDetails.getPassword());

        zoomMeetingRequest.setStartTime(meetingDetails.getStartDateTime());
        zoomMeetingRequest.setTimezone(meetingDetails.getTimezone());
        zoomMeetingRequest.setType(2);

        // Get the access token for the new user for which the meeting needs to be created

        // Create meeting

        ClientResponse clientResponse = zoomApiIntegration.callCreateMeetingApi(meetingDetails, zoomMeetingRequest, accessToken);
        Mono<ZoomMeetingResponse> zoomMeetingResponse = clientResponse.bodyToMono(ZoomMeetingResponse.class);

        ZoomMeetingResponse meetingDetailsResponseToUse = zoomMeetingResponse.block();


        return (meetingDetailsResponseToUse);
    }

    public ZoomMeetingResponse updateMeetingWithAccessToken(MeetingDetailsHelper meetingDetails, String meetingId, String accessToken) {

        ZoomMeetingRequest zoomMeetingRequest = new ZoomMeetingRequest();
        zoomMeetingRequest.setAgenda(meetingDetails.getAgenda());
        zoomMeetingRequest.setTopic(meetingDetails.getTopic());
        zoomMeetingRequest.setDuration(meetingDetails.getDuration());
        if (!isNull(meetingDetails.getPassword()))
            zoomMeetingRequest.setPassword(meetingDetails.getPassword());

        zoomMeetingRequest.setStartTime(meetingDetails.getStartDateTime());
        zoomMeetingRequest.setTimezone(meetingDetails.getTimezone());
        zoomMeetingRequest.setType(2);


        ZoomMeetingResponse meetingDetailsResponse = null;
        ClientResponse clientResponse = zoomApiIntegration.callUpdateMeetingApi(zoomMeetingRequest,
                meetingId,
                accessToken);
        if (clientResponse.statusCode().isError()) {
            throw new ResourceNotFoundException("Failed to update meeting. Error = " + clientResponse.statusCode().value());
        } else {
            Mono<ZoomMeetingResponse> zoomMeetingResponse = clientResponse.bodyToMono(ZoomMeetingResponse.class);
            meetingDetailsResponse = zoomMeetingResponse.block();

            return (meetingDetailsResponse);
        }
    }

    public PersonalDetailsResponse getUserDetails(String accessToken) {
        PersonalDetailsResponse personalDetailsResponse = zoomApiIntegration.callCurrentUserApi(accessToken);

        return (personalDetailsResponse);
    }
}

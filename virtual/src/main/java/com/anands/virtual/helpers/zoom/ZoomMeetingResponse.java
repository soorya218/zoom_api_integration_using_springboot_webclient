package com.anands.virtual.helpers.zoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({
        "id",
        "agenda",
        "topic",
        "start_time",
        "duration",
        "timezone",
        "host_email",
        "start_url",
        "join_url",
        "password",
        "type",
        "uuid",
        "created_at",
        "user_id",
        "personal_meeting_url"
})
public class ZoomMeetingResponse implements Serializable {
    @JsonProperty("id")
    long meetingId;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("duration")
    int duration;
    @JsonProperty("host_id")
    String hostId;
    @JsonProperty("host_email")
    String hostEmail;
    @JsonProperty("join_url")
    String joinUrl;
    @JsonProperty("start_time")
    String startTime;
    @JsonProperty("start_url")
    String startUrl;
    @JsonProperty("status")
    String status;
    @JsonProperty("timezone")
    String timezone;
    @JsonProperty("topic")
    String topic;
    @JsonProperty("type")
    int type;
    @JsonProperty("uuid")
    String uuid;
    @JsonProperty("agenda")
    private String agenda;
    @JsonProperty("password")
    private String password;
    @JsonProperty("personal_meeting_url")
    private String personalMeetingUrl;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(long meetingId) {
        this.meetingId = meetingId;
    }

    public String getJoinUrl() {
        return joinUrl;
    }

    public void setJoinUrl(String joinUrl) {
        this.joinUrl = joinUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalMeetingUrl() {
        return personalMeetingUrl;
    }

    public void setPersonalMeetingUrl(String personalMeetingUrl) {
        this.personalMeetingUrl = personalMeetingUrl;
    }
}

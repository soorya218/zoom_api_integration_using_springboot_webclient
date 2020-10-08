package com.anands.virtual.helpers.generic;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "meeting_name",
        "agenda",
        "topic",
        "start_date_time",
        "end_date_time",
        "duration",
        "timezone",
        "user_id",
        "password",
        "type"
})
public class MeetingDetailsHelper implements Serializable {
    @JsonProperty("meeting_name")
    private String meetingName;
    @JsonProperty("agenda")
    private String agenda;
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("start_date_time")
    private String startDateTime;
    @JsonProperty("end_date_time")
    private String endDateTime;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("type")
    private String type;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7358039937780347836L;

    @JsonProperty("meeting_name")
    public String getMeetingName() {
        return meetingName;
    }

    @JsonProperty("meeting_name")
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    @JsonProperty("agenda")
    public String getAgenda() {
        return agenda;
    }

    @JsonProperty("agenda")
    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty("duration")
    public int getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty("start_date_time")
    public String getStartDateTime() {
        return startDateTime;
    }

    @JsonProperty("start_date_time")
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    @JsonProperty("end_date_time")
    public String getEndDateTime() {
        return endDateTime;
    }

    @JsonProperty("end_date_time")
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingDetailsHelper)) return false;
        MeetingDetailsHelper that = (MeetingDetailsHelper) o;
        return getMeetingName().equals(that.getMeetingName()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getAgenda(), that.getAdditionalProperties()) &&
                getStartDateTime().equals(that.getStartDateTime()) &&
                getEndDateTime().equals(that.getEndDateTime()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getTimezone(), that.getTimezone()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMeetingName(), getTopic(), getAgenda(), getTimezone(), getStartDateTime(), getEndDateTime(), getUserId(), getPassword());
    }

    @Override
    public String toString() {
        return "MeetingDetailsHelper{" +
                "meetingName='" + meetingName + '\'' +
                ", topic='" + topic + '\'' +
                ", agenda='" + agenda + '\'' +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}

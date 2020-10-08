package com.anands.virtual.helpers.zoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoomMeetingRequest {
    @JsonProperty("topic")
    String topic;
    @JsonProperty("type")
    int type;
    @JsonProperty("start_time")
    String startTime;
    @JsonProperty("duration")
    int duration;
    @JsonProperty("schedule_for")
    String scheduleFor;
    @JsonProperty("timezone")
    String timezone;
    @JsonProperty("password")
    String password;
    @JsonProperty("agenda")
    String agenda;

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty("type")
    public int getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(int type) {
        this.type = type;
    }

    @JsonProperty("start_time")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("duration")
    public int getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty("schedule_for")
    public String getScheduleFor() {
        return scheduleFor;
    }

    @JsonProperty("schedule_for")
    public void setScheduleFor(String scheduleFor) {
        this.scheduleFor = scheduleFor;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("agenda")
    public String getAgenda() {
        return agenda;
    }

    @JsonProperty("agenda")
    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}

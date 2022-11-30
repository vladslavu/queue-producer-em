package ro.dsrl.queueproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

public record Message(@JsonProperty("recordedAt") Timestamp recordedAt, @JsonProperty("consumption") Float consumption,
                      @JsonProperty("deviceId") String deviceId) implements Serializable {
}

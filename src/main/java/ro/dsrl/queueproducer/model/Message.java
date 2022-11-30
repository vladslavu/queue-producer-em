package ro.dsrl.queueproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

public record Message(@JsonProperty("timestamp") Timestamp timestamp, @JsonProperty("deviceId") String deviceId,
                      @JsonProperty("measurementValue") Float measurementValue) implements Serializable {
}

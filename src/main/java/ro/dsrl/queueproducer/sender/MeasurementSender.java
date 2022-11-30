package ro.dsrl.queueproducer.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.dsrl.queueproducer.model.Message;
import ro.dsrl.queueproducer.wrapper.MeasurementsWrapper;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class MeasurementSender {
    private final MeasurementSenderService measurementSenderService;
    private final MeasurementsWrapper measurementsWrapper;

    @Value("${energy.management.device-ids}")
    private String deviceIds;

    private List<String> deviceIdsList;

    @PostConstruct
    private void init() {
        deviceIdsList = Arrays.asList(deviceIds.split(","));
    }

    @Scheduled(initialDelay = 10000L, fixedDelay = 30000L)
    public void send() {
        final var message = new Message(new Timestamp(System.currentTimeMillis()),
                measurementsWrapper.getNextMeasurement(), getDeviceId());
        log.info("Sending message: " + message);
        measurementSenderService.send(message);
    }

    private String getDeviceId() {
        return deviceIdsList.get(ThreadLocalRandom.current().nextInt(0, 3));
    }
}

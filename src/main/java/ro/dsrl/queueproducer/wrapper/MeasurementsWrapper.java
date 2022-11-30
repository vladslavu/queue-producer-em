package ro.dsrl.queueproducer.wrapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ro.dsrl.queueproducer.constants.PublisherConstants.STARTING_CSV_INDEX;

@RequiredArgsConstructor
@Getter
public class MeasurementsWrapper {
    private final List<Float> measurements;
    private int currentIndex = STARTING_CSV_INDEX;

    public Float getNextMeasurement() {
        return measurements.get(currentIndex++);
    }
}

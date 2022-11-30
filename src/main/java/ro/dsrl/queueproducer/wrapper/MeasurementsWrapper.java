package ro.dsrl.queueproducer.wrapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class MeasurementsWrapper {
    private final List<Float> measurements;
    private int currentIndex = 0;

    public Float getNextMeasurement() {
        return measurements.get(currentIndex++);
    }
}

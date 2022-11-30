package ro.dsrl.queueproducer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.dsrl.queueproducer.sender.CsvReader;
import ro.dsrl.queueproducer.wrapper.MeasurementsWrapper;

@Configuration
public class PublisherConfig {
    @Value("${queue.name}")
    private String name;

    @Bean
    public Queue queue() {
        return new Queue(name, true);
    }

    @Bean
    public MeasurementsWrapper measurementsWrapper(CsvReader csvReader) {
        return new MeasurementsWrapper(csvReader.readAllValues());
    }
}

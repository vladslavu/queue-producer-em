package ro.dsrl.queueproducer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue() {
        return new Queue(name, true);
    }

    @Bean
    public MeasurementsWrapper measurementsWrapper(CsvReader csvReader) {
        return new MeasurementsWrapper(csvReader.readAllValues());
    }
}

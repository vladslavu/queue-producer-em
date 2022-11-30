package ro.dsrl.queueproducer.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.dsrl.queueproducer.model.Message;

@RequiredArgsConstructor
@Service
public class MeasurementSenderService {
    public final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    public void send(Message message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}

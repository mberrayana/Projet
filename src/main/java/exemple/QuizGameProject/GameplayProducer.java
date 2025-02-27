package exemple.QuizGameProject;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

public class GameplayProducer {
	private final KafkaProducer<String, String> producer;

	public GameplayProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
	}

	public void sendPlayerAnsweredEvent(String playerName, String answer) {
		String event = "Player-Answered:" + playerName + ":" + answer;
		producer.send(new ProducerRecord<>("Player-Answers", playerName, event));
	}

	public void close() {
		producer.close();
	}
}

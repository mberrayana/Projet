package exemple.QuizGameProject;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class PlayerManagementProducer {
private final KafkaProducer<String, String> producer;

public PlayerManagementProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
	}

	public void sendPlayerRegisteredEvent(String playerName) {
		String event = "Player_registered:" + playerName;
		producer.send(new ProducerRecord<>("Registred_Players", playerName, event));
	}

	public void close() {
		producer.close();
	}
}
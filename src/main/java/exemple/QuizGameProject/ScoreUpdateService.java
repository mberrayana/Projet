package exemple.QuizGameProject;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ScoreUpdateService {
	private final KafkaConsumer<String, String> consumer;

	public ScoreUpdateService() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "score-update-group");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("Player-Answers"));
	}

	public void processMessages() {
		while (true) {
		ConsumerRecords<String, String> records = consumer.poll(100);
		for (ConsumerRecord<String, String> record : records) {
				validateAnswerAndUpdateScore(record.value());
			}
		}
	}

	private void validateAnswerAndUpdateScore(String event) {
		
	}

	public void close() {
		consumer.close();
	}
}
package exemple.QuizGameProject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class StatisticsService {
	private final KafkaConsumer<String, String> consumer;

	public StatisticsService() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "statistics-group");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("Registred_Players"));
	}

	public void processMessages() {
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				analyzeGameData(record.value());
			}
		}
	}

	private void analyzeGameData(String event) {
	// Process event to gather statistics
		
	}

	public void close() {
	 consumer.close();
	}
}

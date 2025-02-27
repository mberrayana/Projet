package exemple.QuizGameProject;

import io.grpc.stub.StreamObserver;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Properties;


import org.springframework.stereotype.Service;

@Service
public class PlayerManagementService {
	
	ArrayList<Player> players = new ArrayList<Player>(); 
	
	public void register(Player p) {
		players.add(p);
	}
	
	public void playGame(String playerName, int givenAnswer) {
		Player player = findPlayerByName(playerName);
        if (player != null) {
            int correctAnswer = 42; 
            if (givenAnswer == correctAnswer) {
                player.incrementScore();
                System.out.println("Correct answer! Player's score: " + player.getScore());
            } else {
                System.out.println("Incorrect answer. Player's score remains: " + player.getScore());
            }
        } else {
            System.out.println("Player not found!");
        }
    }
	
	private Player findPlayerByName(String playerName) {
        for (Player p : players) {
            if (p.getPlayerName().equals(playerName)) {
                return p;
            }
        }
        return null;
	}
	
}


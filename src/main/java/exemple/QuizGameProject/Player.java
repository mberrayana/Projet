package exemple.QuizGameProject;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
    private String playerName;
    private int score;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    
    public Player(String playerName) {
        this.playerName = playerName;
        this.score = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

}

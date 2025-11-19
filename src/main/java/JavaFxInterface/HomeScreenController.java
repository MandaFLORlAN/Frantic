package JavaFxInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeScreenController {
    @FXML
    private VBox botlist;

    private List<String> players = new ArrayList<>();

    public void onAddLogicBot1() {
        players.add("LogicBot1: " + getRandomName());
        loadPlayers();
    }

    private void loadPlayers() {
        botlist.getChildren().clear();
        for (int i = 0; i < players.size(); i++) {
            String player = players.get(i);
            HBox line = new HBox();
            line.idProperty().set("botLine:" + i);
            line.getChildren().add(new Label(player));
            Button deleteButton = new Button("delete");
            deleteButton.setUserData(i);
            deleteButton.setOnAction(this::deleteLine);
            deleteButton.getStyleClass().add("deleteButton");
            line.getChildren().add(deleteButton);
            botlist.getChildren().add(line);
        }
    }

    private void deleteLine(ActionEvent event) {
        Node node = (Node) event.getSource();
        Integer index = (Integer) node.getUserData();
        players.remove(index.intValue());
        Node line = node.getScene().lookup("botLine:" + index);
        botlist.getChildren().remove(line);
        loadPlayers();
    }

    public void onStartGame() {
        System.out.println("Starting game");
    }

    private String getRandomName() {
        Random rand = new Random();
        String[] words = getWords();
        boolean isUnique = false;
        String name = "";
        while (!isUnique) {
            name = words[rand.nextInt(words.length)] + " " + words[rand.nextInt(words.length)];
            isUnique = true;
            for (String player : players) {
                if (player.equals(name)) {
                    isUnique = false;
                    break;
                }
            }
        }
        return name;
    }

    private static String[] getWords() {
        String text = """
                Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt 
                ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco 
                laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in 
                voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat 
                non proident sunt in culpa qui officia deserunt mollit anim id est laborum
                """;
        String[] words = text.replace("\n","").split(" ");
        return words;
    }
}

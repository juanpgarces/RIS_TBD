package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginPage extends Application {
	private double xOffset = 0;
	private double yOffset = 0;
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../com/RIS/view/LoginPage.fxml"));
		stage.setTitle("RIS Home");
        stage.setResizable(false);
        
        Scene scene = new Scene (root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
	}
 

	public static void main(String[] args) {
		launch(args);
	}

}
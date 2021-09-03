package AmirHauftmann_OrlyIsaharov;
	
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Company;
import view.View;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Company company = new Company();
		View view = new View(primaryStage);
		Controller Controller = new Controller(company, view);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

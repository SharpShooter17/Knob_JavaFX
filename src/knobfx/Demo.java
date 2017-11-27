import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Demo extends Application {
    public static void main(String args[]){
        Demo.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = getClass().getResource("resources/view.fxml").getPath();
        System.out.println("Sciezka: " + path);
        Parent root = FXMLLoader.load(getClass().getResource("resources/view.fxml"));
    
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Oscyloskop");
        stage.setScene(scene);
        stage.show();
    }
}

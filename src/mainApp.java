import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class mainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage secondaryStage;
    private Parent panel1, panel2;
    private Scene primaryScene, secondaryScene;



    @Override
    public void start(Stage primaryStage) throws IOException {
        // Инициализация окна #1
        panel1 = FXMLLoader.load(getClass().getResource("mainApp.fxml"));
        primaryScene = new Scene(panel1, 800, 530);
        GraphCanvas graphCanvas = new GraphCanvas(panel1);
        primaryStage.getIcons().add(new Image("file:iconv2.png"));
        primaryStage.setTitle("Визуализация движения частиц по регулярным сетям");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        // Инициализация окна #2
        secondaryStage = new Stage();
        panel2 = FXMLLoader.load(getClass().getResource("secondWindow.fxml"));
        secondaryScene = new Scene(panel2, 363, 363);
        secondaryStage.getIcons().add(new Image("file:iconv2.png"));
        secondaryStage.setTitle("Матрица смежности");
        secondaryStage.setScene(secondaryScene);

//        // Тест графа
//        Node node1 = new Node(50, 50, 10);
//        Node node2 = new Node(50, 100, 10);
//        PathLine pl = new PathLine(node1, node2, 1);
//        graphCanvas.addNode(node1);
//        graphCanvas.addNode(node2);
//        graphCanvas.addPath(pl);
//        graphCanvas.repaint();


    }

    public static void openSecondWindow(){
        secondaryStage.show();
    }
}

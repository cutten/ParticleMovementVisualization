import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.util.Arrays;

public class MainApp extends Application {

    private static final int nodesCount = 9;
    private static Stage secondaryStage;
    private static GraphCanvas graphCanvas;
    private static TheMatrixReload matrix;
    private Parent panel1;
    private Group panel2;
    private Scene primaryScene;
    private Scene secondaryScene;
    private static boolean[][] matr3 = new boolean[][]{
            {false, true, false, true, false, false, false, false, false},
            {true, false, true, false, true, false, false, false, false},
            {false, true, false, false, false, true, false, false, false},
            {true, false, false, false, true, false, true, false, false},
            {false, true, false, true, false, true, false, true, false},
            {false, false, true, false, true, false, false, false, true},
            {false, false, false, true, false, false, false, true, false},
            {false, false, false, false, true, false, true, false, true},
            {false, false, false, false, false, true, false, true, false}
    };
    static boolean[][] test;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        // Инициализация окна #1
        createPrimaryStage(primaryStage);
        // Инициализация окна #2
        matrix = new TheMatrixReload(nodesCount);
        matrix.setPreset(matr3);
        matrix.refreshMatrix();
        test = matrix.getBoolArray();

        boolean[][] matr = new boolean[][]{{false, true, true, false}, {true, false, false, true}, {true, false, false, true}, {false, true, true, false}};
        boolean[][] matr2 = new boolean[][]{{false, true, true, false}, {true, false, false, true}, {true, false, false, false}, {false, true, false, false}};


        //GraphGenerator.generate(test, graphCanvas);
        //GraphGenerator.generate(matr3, graphCanvas);




        //javafx.scene.control.TableView tableView = (javafx.scene.control.TableView) panel2.lookup("#matrix");
        //tableView.setItems();


//        // Тест графа
//        Node node1 = new Node(50, 50, 10);
//        Node node2 = new Node(50, 100, 10);
//        PathLine pl = new PathLine(node1, node2, 1);
//        canvas.addNode(node1);
//        canvas.addNode(node2);
//        canvas.addPath(pl);
//        canvas.repaint();


    }

    private void createPrimaryStage(Stage stage) throws IOException {
        panel1 = FXMLLoader.load(getClass().getResource("mainApp.fxml"));
        primaryScene = new Scene(panel1, 800, 530);
        graphCanvas = new GraphCanvas(panel1);
        stage.getIcons().add(new Image("file:iconv2.png"));
        stage.setTitle("Визуализация движения частиц по регулярным сетям");
        stage.setScene(primaryScene);
        stage.setResizable(false);
        stage.show();
    }

    static void mainGenerate(){
        matrix.fillBoolArray();
        GraphGenerator.generate(matrix.getBoolArray(),graphCanvas);

    }

}

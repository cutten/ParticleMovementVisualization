import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static final int nodesCount = 9;
    private static Stage secondaryStage;
    private static GraphCanvas graphCanvas;
    private static AdjMatrix matrix;
    private static Parent panel1;
    private Group panel2;
    private Scene primaryScene;
    private Scene secondaryScene;
    private static SequentialTransition particleMovement;
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
    private static boolean[][] test;
    private static Text errorLabel;
    private static Button okButton;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        // Инициализация окна #1
        createPrimaryStage(primaryStage);
        // Инициализация окна #2
        matrix = new AdjMatrix(nodesCount);
        matrix.setPreset(matr3);
        matrix.refreshMatrix();
        test = matrix.getBoolArray();
        displayError("Слишком круто...");
//        boolean[][] matr = new boolean[][]{{false, true, true, false}, {true, false, false, true}, {true, false, false, true}, {false, true, true, false}};
//        boolean[][] matr2 = new boolean[][]{{false, true, true, false}, {true, false, false, true}, {true, false, false, false}, {false, true, false, false}};



    }


    // Метод создания главного окна
    private void createPrimaryStage(Stage stage) throws IOException {
        panel1 = FXMLLoader.load(getClass().getResource("mainAppPane.fxml"));
        primaryScene = new Scene(panel1, 800, 530);
        graphCanvas = new GraphCanvas(panel1);
        errorLabel = (Text) panel1.lookup("#errorLabel");
        okButton = (Button) panel1.lookup("#okButton");
        okButton.setVisible(false);
        stage.getIcons().add(new Image("file:iconv2.png"));
        stage.setTitle("Визуализация движения частиц по регулярным сетям");
        stage.setScene(primaryScene);
        stage.setResizable(false);
        stage.show();
    }


    // Метод - "Оболочка" для вызова метода генерации
    static void mainGenerate(){

        if(matrix.fillBoolArray())
            GraphGenerator.generate(matrix.getBoolArray(),graphCanvas);
        else
            displayError("матрица смежности не является графом регулярной сети.");
    }

    // Метод - "Оболочка" для запуска анимации
    static void startAnimation() throws InterruptedException {
        try{
        Particle particle = new Particle(graphCanvas);
        particleMovement = new SequentialTransition(particle.moveRight(),particle.moveDown(),particle.moveLeft());
        particleMovement.play();}
        catch (IndexOutOfBoundsException e){
            displayError("не на чем показывать анимацию :P");
        }
    }

    static void displayError(String string){
        okButton.setVisible(true);
        errorLabel.setText("Ошибка: " + string);
    }

    static void hideError(){
        okButton.setVisible(false);
        errorLabel.setText("");
    }
}

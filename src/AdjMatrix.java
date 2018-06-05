import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AdjMatrix {
    private static boolean[][] boolArray; // Основной массив-матрица данных, по нему строится граф
    private int nodesCount; // Кол-во вершин в графе
    private static Stage stage; // Окно для таблицы
    private Scene scene; // Сцена-контейнер для таблицы
    private Group group; // Группировка ячеек
    private ArrayList<ArrayList<LimitedTextField>> textFieldsArr; // Массив ячеек
    private ArrayList<Label> horizontalLabels; // Массив подписей сверху таблицы
    private ArrayList<Label> verticalLabels; // Массив подписей  слева таблицы


    AdjMatrix(int nodesCount) {
        //Инициализация переменных
        this.group = new Group();
        this.nodesCount = nodesCount;
        this.textFieldsArr = new ArrayList<ArrayList<LimitedTextField>>();
        this.horizontalLabels = new ArrayList<Label>();
        this.verticalLabels = new ArrayList<Label>();
        boolArray = new boolean[nodesCount][nodesCount];

        // Алгоритм генерации таблицы размерности nodesCount
        for (int i = 0; i < nodesCount; i++) {
            Label verticalLabel = new Label("N" + i);
            verticalLabel.setLayoutY(30 + i * 25);
            verticalLabel.setLayoutX(6);
            verticalLabels.add(verticalLabel);
            group.getChildren().add(verticalLabel);
            ArrayList<LimitedTextField> a = new ArrayList<LimitedTextField>();
            textFieldsArr.add(a);
            for (int j = 0; j < nodesCount; j++) {
                if (i == 0) {
                    Label horizontalLabel = new Label("N" + j);
                    horizontalLabel.setLayoutX(30 + j * 25);
                    horizontalLabel.setLayoutY(6);
                    horizontalLabels.add(horizontalLabel);
                    group.getChildren().add(horizontalLabel);
                }
                LimitedTextField tf = new LimitedTextField();
                tf.setPrefSize(25, 25);
                tf.setLayoutX(25 + j * 25);
                tf.setLayoutY(25 + i * 25);
                tf.setFont(new Font(12));
                if (i==j){
                  tf.setEditable(false);
//                    tf.setText("X");
                }
                textFieldsArr.get(i).add(tf);
                group.getChildren().add(tf);
            }
            ///////////////////////////////////////////////
        }


        scene = new Scene(group, nodesCount * 25 + 50, nodesCount * 25 + 50);
        stage = new Stage();
        stage.getIcons().add(new Image("file:iconv2.png"));
        stage.setTitle("Матрица смежности");
        stage.setResizable(false);
        stage.setScene(scene);


    }


    static void showMatrix() {
        stage.show();
    }


    public boolean[][] getBoolArray() {
        return boolArray;
    }

    boolean fillBoolArray() {
        boolean flag = true;
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                String s = textFieldsArr.get(i).get(j).getText();
                boolArray[i][j] = strToBool(s);
            }
        }

        return checkBoolArray();
    }

    boolean checkBoolArray() {
        for (int i = 0; i < boolArray.length; i++) {
            for (int j = 0; j < boolArray.length; j++) {
                if (boolArray[i][j]) {
                    if (i == 0) {
                        if (j == 0)
                            if (boolArray[i][j + 1] || boolArray[i + 1][j])
                                return false;
                        if (j == boolArray.length - 1)
                            if (boolArray[i][j - 1] || boolArray[i + 1][j])
                                return false;
                        if (boolArray[i][j - 1] || boolArray[i][j + 1] || boolArray[i + 1][j])
                            return false;
                    }
                    if (i == boolArray.length - 1) {
                        if (j == 0)
                            if (boolArray[i][j + 1] || boolArray[i - 1][j])
                                return false;
                        if (j == boolArray.length - 1)
                            if (boolArray[i][j - 1] || boolArray[i - 1][j])
                                return false;
                        if (boolArray[i][j - 1] || boolArray[i][j + 1] || boolArray[i - 1][j])
                            return false;
                    }
                    if (j == 0) {
                        if (boolArray[i + 1][j] || boolArray[i - 1][j] || boolArray[i][j + 1])
                            return false;
                    }
                    if (j == boolArray.length - 1) {
                        if (boolArray[i + 1][j] || boolArray[i - 1][j] || boolArray[i][j - 1])
                            return false;
                    }
                    if (i!=0 && i!=boolArray.length-1 && j!=0 && j!=boolArray.length-1)
                    if (boolArray[i + 1][j] || boolArray[i - 1][j] || boolArray[i][j + 1] || boolArray[i][j - 1])
                        return false;
                }
            }
        }
        return true;
    }

    boolean strToBool(String string) {
        if (string.equals("0"))
            return false;
        else
            return true;
    }

    String boolToStr(boolean b) {
        if (!b)
            return "0";
        else
            return "1";
    }

    void setPreset(boolean[][] preset) {
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                boolArray[i][j] = preset[i][j];

            }
        }

    }

    void refreshMatrix() {
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                textFieldsArr.get(i).get(j).setText(boolToStr(boolArray[i][j]));
            }
        }
    }


}

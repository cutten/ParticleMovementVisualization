import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Matrix {

    private boolean[][] matrixArray;
    private static Stage matrixStage;
    private Scene matrixScene;
    private TableView<String> matrixTable;
    private Parent panel;
    private ArrayList<TableColumn<String,String>> tableColumns;
    private Group group;

    public Matrix(Parent panel) {
        this.group = new Group();
        this.matrixArray = new boolean[10][10];
        this.matrixStage = new Stage();
        this.matrixTable = new TableView<String>();
        tableColumns = new ArrayList<TableColumn<String, String>>();
        TableColumn<String, String> n = new TableColumn<String, String>();
        n.setText("N");
        n.setPrefWidth(33);
        tableColumns.add(n);
        matrixTable.getColumns().addAll(n);
        for (int i = 0; i < 10; i++) {
            TableColumn<String,String> node = new TableColumn<String,String>();
            node.setText("N" + i);
            node.setPrefWidth(33);
            tableColumns.add(node);
            matrixTable.getColumns().addAll(node);

        }
        matrixTable.setPrefSize(363,363);
        this.group = new Group(matrixTable);
        matrixScene = new Scene(group, 363, 363);
        matrixStage = new Stage();
        matrixStage.getIcons().add(new Image("file:iconv2.png"));
        matrixStage.setTitle("Матрица смежности");
        matrixStage.setScene(matrixScene);
    }

    public static void showMatrixTable() {

        matrixStage.show();
    }


}

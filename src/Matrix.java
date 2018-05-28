import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Matrix {

    private boolean[][] boolArray;
    private String[][] stringArray;
    private static Stage matrixStage;
    private Scene matrixScene;
    private TableView<String> matrixTable;
    private Parent panel;
    ObservableList<String> o1;
    private ArrayList<TableColumn<String, String>> tableColumns;
    private Group group;

    public Matrix(Parent panel) {
        this.stringArray = new String[10][10];
        this.boolArray = new boolean[][]{
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true},
                {false, false, false, false, false, true, false, true, false, true}
        };;
        fillStringArray();
        o1 = FXCollections.observableArrayList(stringArray[0]);
        //this.group = new Group();


        this.matrixStage = new Stage();
        this.matrixTable = new TableView<String>(o1);
        matrixTable.setEditable(false);

        tableColumns = new ArrayList<TableColumn<String, String>>();

        matrixTable.setPrefSize(363, 363);

        TableColumn<String, String> n = new TableColumn<String, String>();
        n.setText("N");
        n.setPrefWidth(33);
        tableColumns.add(n);
        matrixTable.getColumns().addAll(n);
        for (int i = 0; i < 10; i++) {
            TableColumn<String, String> node = new TableColumn<String, String>("N"+i);
            node.setPrefWidth(33);
            tableColumns.add(node);
            node.setCellValueFactory(new PropertyValueFactory<String,String>("Node"));
            matrixTable.getColumns().addAll(node);
        }


        fillTable();

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

    public void fillTable() {
        for (int i = 0; i < 10; i++) {
            ObservableList<String> o = FXCollections.observableArrayList(new String[]{"1","1","1","1","1","1","1","1","1","1",});
            matrixTable.setItems(o);
        }
    }


    public boolean[][] getBoolArray() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //matrixTable
            }
        }
        return null;
    }




    void fillStringArray(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (boolArray[i][j] == false)
                    stringArray[i][j] = "0";
                else
                    stringArray[i][j] = "1";

            }
        }
    }

    void getBoolArrayFromString(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (stringArray[i][j] == "0")
                    boolArray[i][j] = false;
                else
                    boolArray[i][j] = true;

            }
        }
    }

}

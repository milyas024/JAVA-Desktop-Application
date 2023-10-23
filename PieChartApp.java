


/**
 *
 * @author muhammad.ilyas
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
 
public class PieChartApp extends Application {
 @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Age Distribution");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data(">70.15%", 15),
                new PieChart.Data(">50 & <=70.35%", 35),
                new PieChart.Data(">20 & <=50.35%", 35),
                new PieChart.Data("<= 20.15%", 15));
        


               
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Age Distribution");
        chart.setLabelsVisible(true);
        

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}

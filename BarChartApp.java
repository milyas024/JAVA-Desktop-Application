/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarChartApp;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class BarChartApp extends Application {
    final static String HD = "HD";
    final static String DI = "DI";
    final static String CR = "CR";
    final static String PS = "PS";
    final static String PL = "PL";
    final static String other= "Other";
 
    @Override public void start(Stage stage) {
//        stage.setTitle("Bar Chart Sample");
         CategoryAxis xAxis = new CategoryAxis();
         NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Grade Distribution");
        xAxis.setLabel("Grade");       
        yAxis.setLabel("Number of Students");
 
        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(HD, 2));
        series1.getData().add(new XYChart.Data(DI, 1));
        series1.getData().add(new XYChart.Data(CR, 3));
        series1.getData().add(new XYChart.Data(PS , 3));
        series1.getData().add(new XYChart.Data(PL, 1));  
        series1.getData().add(new XYChart.Data(other, 0)); 
        

        Scene scene  = new Scene(bc,900,500);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * EX12.
 */
public class Main extends Application {
    /***/
    public static final int HOURS = 24;
    /***/
    public static final int WIDTH = 800;
    /***/
    public static final int HEIGHT = 600;

    /**
     * Show a graph of instagram likes depending from the time of the day.
     *
     * @param stage The main frame.
     * @throws IOException Error.
     */
    @Override
    public final void start(Stage stage) throws IOException {
        stage.setTitle("EX12");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis(0.0, 24.0, 1.0);
        System.out.println(xAxis.getTickUnit());
        System.out.println();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Kellaaeg");
        yAxis.setLabel("Populaarsus");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Instagrami pildi populaarsuse sõltuvus kellajast");
        //defining a series
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName("Populaarsus");
        //populating the series with data
        BufferedReader br = new BufferedReader(new FileReader("EX12Data.txt"));
        String[] info = br.readLine().trim().split(";");
        for (int i = 0; i < HOURS; i++) {
            series.getData().add(new XYChart.Data<Number, Number>(i, Double.parseDouble(info[i])));
        }
        Scene scene = new Scene(lineChart, WIDTH, HEIGHT);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main function.
     *
     * @param args Dunno.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

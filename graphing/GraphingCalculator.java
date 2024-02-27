

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polyline;
import javafx.collections.ObservableList;

public class GraphingCalculator extends Application {

@Override
public void start(Stage stage) {

Polyline points = new Polyline();

ObservableList<Double> list = points.getPoints();

	StackPane pane = new StackPane();

	for(int i = 1; i <= 10; i++) {
		list.addAll(i*10.0,Calculator.calculate("x^2",i));
	}
	points.setRotate(180);

	pane.getChildren().add(points);

	Scene scene = new Scene(pane,400,400);
	stage.setScene(scene);
	stage.show();




}


}

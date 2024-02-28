

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.collections.ObservableList;

public class GraphingCalculator extends Application {

@Override
public void start(Stage stage) {


// initializes stuff
int startX, endX , width, height;

startX = 1; endX = 100; width = 400; height = 400;

int domain = endX - startX;
double interval = (double)width/domain;


Polyline points = new Polyline();


ObservableList<Double> list = points.getPoints();

	Pane pane = new Pane();

	//
	// max and min y values
	double max = Double.MIN_VALUE;
	double min = Double.MAX_VALUE;

	// loop for finding max and min y values
	for(int i = startX, countMax = 0, countMin = 0; i <= endX; i++) {

		double temp =Calculator.calculate("x^2",i);
		if(temp > max) 
			max = temp;

		 if(temp < min)
			min = temp;
	}


	// setting range of function and splitting it into intervals for y
	double range = max - min;
	double yInterval = (double)height/range;
	
	// adds the points to the list
	for(int d = startX, counter = 1; d <= endX; d++) {

	 list.addAll((double)(d)*interval,(double)height - Calculator.calculate("x^2",d)*yInterval);

	}

	// puts graph onto pane and dispays it
	pane.getChildren().add(points);

	Scene scene = new Scene(pane,400,400);
	stage.setScene(scene);
	stage.show();




}


}

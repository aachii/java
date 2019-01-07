package circlerain;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleRain extends Application {
	private Random rand = new Random();
	private static int FLAKECOUNT = 2000; // the higher the more flakes can live
	private static int FLAKEMULTI = 5; // the higher the more flakes spawn
	private final Pane pane = new Pane();
	private ArrayList<Flake> flakes = new ArrayList<Flake>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		pane.setStyle("-fx-background-color: black");
	
		final Timeline animation = new Timeline(new KeyFrame(Duration.millis(15), event -> {
			pane.getChildren().clear();
	    	for (int i = 0; i < FLAKEMULTI; i++) {
	    		flakes.add(createFlake());
	    	}
	    	if (flakes.size() >= FLAKECOUNT) {
	    		for (int i = 0; i < FLAKEMULTI; i++) {
	    			flakes.remove(0);
	        	}
	    	}
	    	moveFlakes();
	    	for (Flake f : flakes) {
	    		pane.getChildren().add(f.getC());
	    	}
		}));
	
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
	
		primaryStage.setTitle("Circle Rain");
		primaryStage.setScene(new Scene(pane, 500, 500)); // starting size, can be adjusted after start
		primaryStage.show();
	}

	private void moveFlakes() {
		for (Flake f : flakes) {
			if (f.getC().getCenterY() > pane.getHeight()) {
				f.setV(f.getV()*(-1)/2); // bouncing back if flake lives long enough
				//f.setV(0); // stopping flakes on ground
			} else if (rand.nextInt(5) == 0) {
				f.setV(f.getV()+rand.nextFloat()); // slight chance of increasing falling speed
			}
			f.getC().setCenterY(f.getC().getCenterY()+f.getV());
		}
	}

	private Flake createFlake() {
		double r = rand.nextDouble()*pane.getHeight()/70+10; // size of flake
		double x = rand.nextDouble()*pane.getWidth(); // spawn on full width
		double y = 0; // spawn on top line
		double v = rand.nextDouble()*7+1; // initial speed of flake
		if (x-r < 0) {
			x = x+r;
		} else if (x+r > pane.getWidth()) { // dont spawn out of bound
			x = x-r;
		}
		System.out.printf("x:%5.0f y:%5.0f r:%5.0f v:%5.0f\n",x ,y ,r, v); // log data to console
		//Circle circle = new Circle(x, y, r, new Color(0,0,rand.nextFloat(),rand.nextDouble())); // only blue flakes
		Circle circle = new Circle(x, y, r, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),rand.nextDouble())); // all colors		
		Flake flake = new Flake(circle, v);
		return flake;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
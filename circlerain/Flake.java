package circlerain;

import javafx.scene.shape.Circle;

public class Flake {
	private Circle c;
	private double v;
	private static int DEFV = 1;
	
	public Flake(Circle c) {
		this.c = c;
		this.v = DEFV;
	}
	
	public Flake(Circle c, double v) {
		this.c = c;
		this.v = v;
	}
	
	public Circle getC() {
		return this.c;
	}
	public double getV() {
		return this.v;
	}
	
	public void setV(double v) {
		this.v = v;
	}
}

package de.hfu.simulator.core;

public class ProximityResult {

	private boolean objectDetected;
	private String point;
	
	public boolean hasObjectDetected() {
		return objectDetected;
	}
	
	public String getPoint() {
		return point;
	}
	
	public void setObjectDetected(boolean detected) {
		objectDetected = detected;
	}
	
	public void setPoint(String point) {
		this.point = point;
	}
	
}

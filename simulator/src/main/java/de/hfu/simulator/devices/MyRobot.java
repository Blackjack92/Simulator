package de.hfu.simulator.devices;

import de.hfu.simulator.core.API;
import de.hfu.simulator.core.ProximityResult;

public class MyRobot extends Device{

	private int[] sliderValues;
	private String command;
	private String name;
	
	public MyRobot(API api) {
		this(api, "myRobot");
		sliderValues = new int[2];
	}
	
	public MyRobot(API api, String name) {
		super(api);
		sliderValues = new int[2];
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	public boolean startSignal() {
		return api.simxSetIntegerSignal( "StartSignal", 1);
	}
	public boolean placeCuboid() {
		return api.simxCallScriptFunction(this, "Placecuboid");
	}
	public boolean openGripper() {
		return api.simxCallScriptFunction(this, "gripperopen");
	}

	public boolean closeGripper() {
		return api.simxCallScriptFunction(this, "gripperclose");
	}
	
	public boolean executeCode(String value) {
		command = value ;
		return api.simxCallScriptFunction(this, "executeCode_function", command);
	}
	public ProximityResult getProximityResult() {
		ProximityResult result = new ProximityResult();
		api.simxReadProximitySensor(this, result);
		return result;
	}
	
	public boolean moveFirstSlider(int value) {
		sliderValues[0] = value;
		return api.simxCallScriptFunction(this, "Slider_function1", sliderValues);
	}
	
	public boolean moveSecondSlider(int value) {
		sliderValues[1] = value;
		return api.simxCallScriptFunction(this, "Slider_function2", sliderValues);
	}
	
	
}

package de.hfu.simulator.devices;

import de.hfu.simulator.core.API;
import de.hfu.simulator.core.ProximityResult;

public class Jaco extends Device{

	private int[] sliderValues;
	
	public Jaco(API api) {
		super(api);
		sliderValues = new int[6];
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Jaco";
		
	}
	public boolean openGripper() {
		return api.simxCallScriptFunction(this, "gripperopen");
	}
	public boolean closeGripper() {
		return api.simxCallScriptFunction(this, "gripperclose");
	}
	public boolean moveFirstSlider(int value) {
		sliderValues[0] = value;
		return api.simxCallScriptFunction(this, "Slider_function1", sliderValues);
	}
	
	public boolean moveSecondSlider(int value) {
		sliderValues[1] = value;
		return api.simxCallScriptFunction(this, "Slider_function2", sliderValues);
	}
	
	public boolean moveThirdSlider(int value) {
		sliderValues[2] = value;
		return api.simxCallScriptFunction(this, "Slider_function3", sliderValues);
	}
	
	public boolean moveFourthSlider(int value) {
		sliderValues[3] = value;
		return api.simxCallScriptFunction(this, "Slider_function4", sliderValues);
	}
	
	public boolean moveFifthSlider(int value) {
		sliderValues[4] = value;
		return api.simxCallScriptFunction(this, "Slider_function5", sliderValues);
	}
	public boolean moveSixthSlider(int value) {
		sliderValues[5] = value;
		return api.simxCallScriptFunction(this, "Slider_function6", sliderValues);
	}
	
	public ProximityResult getProximityResult() {
		ProximityResult result = new ProximityResult();
		api.simxReadProximitySensor(this, result);
		return result;
	}
}


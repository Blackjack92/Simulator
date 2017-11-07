package de.hfu.simulator.devices;

import de.hfu.simulator.core.API;
import de.hfu.simulator.core.ProximityResult;

public class PhantomXPincher extends Device {

	private int[] sliderValues;
	
 	public PhantomXPincher(API api) {
		super(api);
		
		sliderValues = new int[4];
	}

	@Override
	public String getName() {
		return "PhantomXPincher";
	}

	public boolean openGripper() {
		return api.simxCallScriptFunction(this, "gripperopen");
	}

	public boolean closeGripper() {
		return api.simxCallScriptFunction(this, "gripperclose");
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
	
	public boolean moveThirdSlider(int value) {
		sliderValues[2] = value;
		return api.simxCallScriptFunction(this, "Slider_function3", sliderValues);
	}
	
	public boolean moveFourthSlider(int value) {
		sliderValues[3] = value;
		return api.simxCallScriptFunction(this, "Slider_function4", sliderValues);
	}
}

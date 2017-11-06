package de.hfu.simulator.devices;

import coppelia.StringWA;
import de.hfu.simulator.core.API;

public class PhantomXPincher extends Device {

	public PhantomXPincher(API api) {
		super(api);
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
	
}

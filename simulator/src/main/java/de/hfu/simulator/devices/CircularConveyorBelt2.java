package de.hfu.simulator.devices;

import de.hfu.simulator.core.API;

public class CircularConveyorBelt2 extends Device{
	private String name;
	
	public CircularConveyorBelt2(API api) {
		this(api, "CircularConveyorBelt");
		// TODO Auto-generated constructor stub
	}
	
	public CircularConveyorBelt2(API api, String name) {
		super(api);
		
		this.name = name;
	}
	@Override
	public String getName() {
		
		return name;
	}
	
	public boolean Stop2() {
		return api.simxSetIntegerSignal("beltstop2", 1);
	}
	
	public boolean Run2() {
		return  api.simxSetIntegerSignal("beltstop2", 0);
	}
}

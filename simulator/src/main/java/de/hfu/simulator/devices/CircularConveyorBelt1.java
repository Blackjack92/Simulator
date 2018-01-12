package de.hfu.simulator.devices;

import de.hfu.simulator.core.API;

public class CircularConveyorBelt1 extends Device{
	private String name;
	
	public CircularConveyorBelt1(API api) {
		this(api, "CircularConveyorBelt");
		// TODO Auto-generated constructor stub
	}
	
	public CircularConveyorBelt1(API api, String name) {
		super(api);
		
		this.name = name;
	}
	@Override
	public String getName() {
		
		return name;
	}
	
	public boolean Stop1() {
		return api.simxSetIntegerSignal("beltstop1", 1);
	}
	
	public boolean Run1() {
		return  api.simxSetIntegerSignal("beltstop1", 0);
	}
}

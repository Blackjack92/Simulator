package de.hfu.simulator.devices;

import de.hfu.simulator.core.API;

public abstract class Device {

	protected API api;
	
	public Device(API api) {
		this.api = api;
	}
	
	public abstract String getName();
	
}

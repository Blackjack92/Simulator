package de.hfu.simulator.core;

import coppelia.StringWA;
import coppelia.remoteApi;
import de.hfu.simulator.Config;
import de.hfu.simulator.devices.Device;

public class API {

	private Config config;
	private remoteApi api;
	private int clientId;
	
	public API(Config config) {
		this.config = config;
		
		// At creation no connection was established
		this.clientId = -1;
		this.api = null;
	}

	public boolean isConnectionEstablished() {
		return clientId != -1;
	}
	
	public boolean connect() {
		api = new remoteApi();
		
		// Close all opened connections
		api.simxFinish(-1); 
		
		// Establish connection 
		clientId = api.simxStart(config.getIp(), config.getPort(), true, true, 5000, 5);
		
		// Check connection was successful
		return isConnectionEstablished();
	}

	public boolean simxCallScriptFunction(Device device, String command) {
		
		// In and out strings are necessary for the example script
		StringWA inStrings = new StringWA(1);
		inStrings.getArray()[0] = "Hello world!";
		StringWA outStrings = new StringWA(0);
		
		int returnCode = api.simxCallScriptFunction(clientId, device.getName(), api.sim_scripttype_childscript,
				command, null, null, inStrings, null, null, null, outStrings, null, api.simx_opmode_blocking);
		return returnCode == api.simx_return_ok;
	}

	public remoteApi getRemoteAPI() {
		return api;
	}

	public int getClientId() {
		return clientId;
	}
}

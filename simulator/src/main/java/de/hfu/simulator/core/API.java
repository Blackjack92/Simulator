package de.hfu.simulator.core;

import java.util.Arrays;

import coppelia.BoolW;
import coppelia.FloatWA;
import coppelia.IntW;
import coppelia.IntWA;
import coppelia.StringWA;
import coppelia.remoteApi;
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
		
		
		StringWA outStrings = new StringWA(0);
		
		int returnCode = api.simxCallScriptFunction(clientId, device.getName(), remoteApi.sim_scripttype_childscript,
				command, null, null, null, null, null, null, outStrings, null, remoteApi.simx_opmode_oneshot);
		return returnCode == remoteApi.simx_return_ok;
	}

	
	public boolean simxCallScriptFunction(Device device, String command, int[] input) {
		IntWA inInts = new IntWA(input.length);
		for (int i = 0; i < input.length; i++) {
			inInts.getArray()[i] = input[i];	
		}

		StringWA outStrings = new StringWA(0);
		int returnCode = api.simxCallScriptFunction(clientId, device.getName(),
				remoteApi.sim_scripttype_childscript, command, inInts, null, null, null, null, null,
				outStrings, null, remoteApi.simx_opmode_oneshot);
		
		return returnCode == remoteApi.simx_return_ok;
	}
	
	public boolean simxCallScriptFunction(Device device, String command, String input) {
		 StringWA inStrings=new StringWA(1);
		 inStrings.getArray()[0] = input;
		 
		StringWA outStrings = new StringWA(0);
		int returnCode = api.simxCallScriptFunction(clientId, device.getName(),
				remoteApi.sim_scripttype_childscript, command, null, null, inStrings, null, null, null,
				outStrings, null, remoteApi.simx_opmode_oneshot);
		
		return returnCode == remoteApi.simx_return_ok;
	}
	
	public boolean simxReadProximitySensor(Device device, ProximityResult result) {
		 
		IntW sensor = new IntW(0);
		
		int returnCode = api.simxGetObjectHandle(clientId, device.getName()+"Proximity_sensor", sensor, remoteApi.simx_opmode_blocking);
		if (returnCode != remoteApi.simx_return_ok) {
			return false;
		}
		
		BoolW detState = new BoolW(false);
		FloatWA detectedPoint = new FloatWA(0);
		IntW detectedObjectHandle = new IntW(1);
		FloatWA SurfaceNormalVector = new FloatWA(1);
		
		returnCode = api.simxReadProximitySensor(clientId, sensor.getValue(), detState, detectedPoint,
				detectedObjectHandle, SurfaceNormalVector, remoteApi.simx_opmode_blocking);
		
		result.setObjectDetected(detState.getValue());
		result.setPoint(Arrays.toString(detectedPoint.getArray()));
		
		return returnCode == remoteApi.simx_return_ok;
	}
	public boolean simxSetIntegerSignal(String signalname, Integer value) {
		
		int returnCode = api.simxSetIntegerSignal(clientId, signalname, value, remoteApi.simx_opmode_oneshot);
		
		return returnCode == remoteApi.simx_return_ok;
		
	}


public boolean simxSetFloatSignal(String signalname, Float value) {
	
	int returnCode = api.simxSetFloatSignal(clientId, signalname, value, remoteApi.simx_opmode_oneshot);
	
	return returnCode == remoteApi.simx_return_ok;
	
}
}

package de.hfu.simulator.rest.commands;

import de.hfu.simulator.core.ProximityResult;
import de.hfu.simulator.devices.PhantomXPincher;

public class GetProximityCommand implements IGetCommand {

    private PhantomXPincher device;

    public GetProximityCommand(PhantomXPincher device) {
        this.device = device;
    }

    public String getUri() {
        return "/getProximity";
    }

    public String execute() {
        ProximityResult proximity = device.getProximityResult();
        String response = "\n detected point coordinates: " + proximity.getPoint()
                    + "\n object detection state = " + proximity.hasObjectDetected();
        return response;
    }

}

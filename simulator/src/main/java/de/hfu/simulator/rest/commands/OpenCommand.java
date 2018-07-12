package de.hfu.simulator.rest.commands;

import de.hfu.simulator.devices.PhantomXPincher;

public class OpenCommand implements IGetCommand {

    private PhantomXPincher device;

    public OpenCommand(PhantomXPincher device) {
        this.device = device;
    }

    public String getUri() {
        return "/open";
    }

    public String execute() {
        device.openGripper();
        String response = "\n gripper opened!";
        return response;
    }

}

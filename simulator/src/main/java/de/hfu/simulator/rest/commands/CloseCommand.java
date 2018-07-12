package de.hfu.simulator.rest.commands;

import de.hfu.simulator.devices.PhantomXPincher;

public class CloseCommand implements IGetCommand {

    private PhantomXPincher device;

    public CloseCommand(PhantomXPincher device) {
        this.device = device;
    }

    public String getUri() {
        return "/close";
    }

    public String execute() {
        device.closeGripper();
        String response = "\n gripper closed!";
        return response;
    }

}

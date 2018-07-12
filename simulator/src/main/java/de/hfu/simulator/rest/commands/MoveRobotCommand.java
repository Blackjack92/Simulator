package de.hfu.simulator.rest.commands;

import de.hfu.simulator.devices.PhantomXPincher;

public class MoveRobotCommand implements IGetCommand {

    private PhantomXPincher device;

    public MoveRobotCommand(PhantomXPincher device) {
        this.device = device;
    }

    public String getUri() {
        return "/moveRobot";
    }

    public String execute() {
        // int value = 50;
        // boolean result = device.moveFirstSlider(value);
        String response = "Can only move robot with POST request containing value of movement"
            + "\n accepted values : -180 to 180" + "\n Available movements: " + "\n /moveRobot/Slider1"
            + "\n /moveRobot/Slider2" + "\n /moveRobot/Slider3" + "\n /moveRobot/Slider4";
        return response;
    }

}

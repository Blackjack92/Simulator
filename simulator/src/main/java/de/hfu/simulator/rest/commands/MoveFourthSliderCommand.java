package de.hfu.simulator.rest.commands;


import de.hfu.simulator.devices.PhantomXPincher;

public class MoveFourthSliderCommand extends MoveSliderCommand {

    public MoveFourthSliderCommand(PhantomXPincher device) {
		super(device);
	}

	public String getUri() {
        return "/moveRobot/Slider4";
    }

    protected boolean moveSlider(int value) {
        return device.moveFourthSlider(value);
    }
}

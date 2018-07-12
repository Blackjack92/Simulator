package de.hfu.simulator.rest.commands;

import de.hfu.simulator.devices.PhantomXPincher;

public class MoveFirstSliderCommand extends MoveSliderCommand {

    public MoveFirstSliderCommand(PhantomXPincher device) {
		super(device);
	}

	public String getUri() {
        return "/moveRobot/Slider1";
    }

    protected boolean moveSlider(int value) {
        return device.moveFirstSlider(value);
    }
}

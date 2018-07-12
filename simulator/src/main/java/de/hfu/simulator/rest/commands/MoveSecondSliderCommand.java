package de.hfu.simulator.rest.commands;


import de.hfu.simulator.devices.PhantomXPincher;

public class MoveSecondSliderCommand extends MoveSliderCommand {

    public MoveSecondSliderCommand(PhantomXPincher device) {
		super(device);
	}

	public String getUri() {
        return "/moveRobot/Slider2";
    }

    protected boolean moveSlider(int value) {
        return device.moveSecondSlider(value);
    }
}

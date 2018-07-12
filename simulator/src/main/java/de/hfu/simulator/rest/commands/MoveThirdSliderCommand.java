package de.hfu.simulator.rest.commands;


import de.hfu.simulator.devices.PhantomXPincher;

public class MoveThirdSliderCommand extends MoveSliderCommand {

    public MoveThirdSliderCommand(PhantomXPincher device) {
		super(device);
	}

	public String getUri() {
        return "/moveRobot/Slider3";
    }

    protected boolean moveSlider(int value) {
        return device.moveThirdSlider(value);
    }
}

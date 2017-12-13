package de.hfu.simulator;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import de.hfu.simulator.core.API;
import de.hfu.simulator.core.Config;
import de.hfu.simulator.devices.MyRobot;
import de.hfu.simulator.visualisation.DeviceUIFactory;

public class Main {

	private final static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws Throwable {

		log.setLevel(Level.ALL);
		log.log(Level.INFO, "Program starting...");

		Config config = Config.getLocalhostConfig();
		log.log(Level.INFO, "Acquired configuration...");
		API api = new API(config);
		log.log(Level.INFO, "Initialized API...");
		api.connect();
		log.log(Level.INFO, "Connected api...");

		log.log(Level.INFO, "Generated device...");

		Main main = new Main();
		main.run(api);
		log.log(Level.INFO, "Program finished...");
	}

	private void run(API api) {
		// Only a single device is tested (myRobot)
		// PhantomXPincher device = new PhantomXPincher(api);
		// JFrame deviceUI =
		// DeviceUIFactory.getInstance().createPhantomXPincherUI(device);
		// deviceUI.setVisible(true);

		// Jaco deviceJ = new Jaco(api);
		// JFrame deviceJUI = DeviceUIFactory.getInstance().createJacoUI(deviceJ);
		// deviceJUI.setVisible(true);

		MyRobot deviceM = new MyRobot(api);
		JFrame deviceMUI = DeviceUIFactory.getInstance().createMyRobotUI(deviceM);
		deviceMUI.setVisible(true);

		MyRobot deviceM2 = new MyRobot(api, "myRobot1");
		JFrame deviceM2UI = DeviceUIFactory.getInstance().createMyRobotUI(deviceM2);
		deviceM2UI.setVisible(true);
	}
}

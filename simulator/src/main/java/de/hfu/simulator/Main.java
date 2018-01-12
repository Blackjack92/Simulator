package de.hfu.simulator;

import java.awt.GridLayout;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import de.hfu.simulator.core.API;
import de.hfu.simulator.core.Config;
import de.hfu.simulator.devices.CircularConveyorBelt1;
import de.hfu.simulator.devices.CircularConveyorBelt2;
import de.hfu.simulator.devices.MyRobot;
import de.hfu.simulator.visualisation.CustomOutputStream;
import de.hfu.simulator.visualisation.DeviceUIFactory;

public class Main {

	
	
	public static void main(String[] args) throws Throwable {

		
			
		
		Config config = Config.getLocalhostConfig();
		API api = new API(config);
		api.connect();
		Main main = new Main();
		main.run(api);
		
	
	}

	private void run(API api) {
		// Only a single device is tested (myRobot)

		
		
		//Frame
		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 0, 0, 7));
		frame.setTitle("Industry 4.0 simulator");
		//Tabbed Pane for devices
		JTabbedPane tabpane = new JTabbedPane
	            (JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	
		
		//Text Area console output
		JTextArea textArea = new JTextArea(100, 60);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);
		//Text Panel
		JPanel textpanel = new JPanel();
		textpanel.setSize(345, 50);
		textpanel.setVisible(true);
		
		
		
		//Panel for myRobot
		MyRobot deviceM = new MyRobot(api);
		JPanel deviceMUI = DeviceUIFactory.getInstance().createMyRobotUI(deviceM);
		
		
		tabpane.addTab("MyRobot", deviceMUI);
		
		//Panel for myRobot1
		MyRobot deviceM2 = new MyRobot(api, "myRobot1");
		
		
		JPanel deviceM2UI = DeviceUIFactory.getInstance().createMyRobotUI(deviceM2);
		tabpane.addTab("MyRobot1", deviceM2UI);
		
		
		//Panel for CircularConveyorBelt1
		
		CircularConveyorBelt1 deviceC = new CircularConveyorBelt1(api,"CircularConveyorBelt1");
		
		JPanel deviceCUI = DeviceUIFactory.getInstance().createCircularConveyorBeltUI(deviceC);
		tabpane.addTab("CircularConveyorBelt1", deviceCUI);
		
		//Panel for CircularConveyorBelt2#1
		CircularConveyorBelt2 deviceC1 = new CircularConveyorBelt2(api,"CircularConveyorBelt2#1");
		
		JPanel deviceC2UI = DeviceUIFactory.getInstance().createCircularConveyorBeltUI2(deviceC1);
		tabpane.addTab("CircularConveyorBelt2#1", deviceC2UI);
		
		frame.add(tabpane);
		textpanel.add(textArea);
		frame.add(textpanel);
		tabpane.setVisible(true);
		frame.pack();
		frame.setVisible(true);
	}
}

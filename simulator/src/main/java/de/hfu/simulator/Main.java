package de.hfu.simulator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hfu.simulator.core.API;
import de.hfu.simulator.core.Config;
import de.hfu.simulator.core.ProximityResult;
import de.hfu.simulator.devices.PhantomXPincher;

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

		PhantomXPincher device = new PhantomXPincher(api);
		log.log(Level.INFO, "Generated device...");
		
		Main main = new Main();
		main.run(device);
		
		log.log(Level.INFO, "Program finished...");
	}

	public void sensor(PhantomXPincher device) {

		ProximityResult result = device.getProximityResult();
		
		GridLayout gridLayout = new GridLayout(0, 2);
		JDialog meinJDialog = new JDialog();
		
		meinJDialog.setTitle("Sensormessung Proximity sensor ");
		meinJDialog.setSize(945, 150);
		meinJDialog.setModal(true);
		
		JLabel lab1 = new JLabel("detected point coordinates: " + result.getPoint());
		JLabel lab2 = new JLabel("detection state = " + result.hasObjectDetected());

		meinJDialog.add(lab1);
		meinJDialog.add(lab2);
		meinJDialog.setLayout(gridLayout);
		meinJDialog.setVisible(true);

	}

	public void run(final PhantomXPincher device) {

		JPanel panel = new JPanel();
		panel.setSize(400, 200);
		panel.setVisible(true);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 0, 0, 7));

		JSlider slider1 = new JSlider(-180, 180, 0);
		slider1.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider) e.getSource()).getValue();
				boolean result = device.moveFirstSlider(value);
				log.log(Level.INFO, "First slider moved: " + result);
			}
		});

		JSlider slider2 = new JSlider(-180, 180, 0);
		slider2.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider) e.getSource()).getValue();
				boolean result = device.moveSecondSlider(value);
				log.log(Level.INFO, "Second slider moved: " + result);

			}
		});

		JSlider slider3 = new JSlider(-180, 180, 0);
		slider3.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider) e.getSource()).getValue();
				boolean result = device.moveThirdSlider(value);
				log.log(Level.INFO, "Third slider moved: " + result);
			}
		});

		JSlider slider4 = new JSlider(-180, 180, 0);
		slider4.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider) e.getSource()).getValue();
				boolean result = device.moveFourthSlider(value);
				log.log(Level.INFO, "Fourth slider moved: " + result);
			}
		});

		JButton gripperopen = new JButton("Open Gripper");
		gripperopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = device.openGripper();
				log.log(Level.INFO, "Gripper opened: " + result);
			}
		});

		JButton gripperclose = new JButton("Close Gripper");
		gripperclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = device.closeGripper();
				log.log(Level.INFO, "Gripper closed: " + result);
			}
		});

		JButton Sensor = new JButton("Sensor prüfen");
		Sensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sensor(device);
			}
		});

		frame.add(panel);
		panel.add(gripperopen);
		frame.add(slider1);
		frame.add(slider2);
		frame.add(slider3);
		frame.add(slider4);
		panel.add(gripperclose);
		panel.add(Sensor);
		frame.pack();
		frame.setVisible(true);

	}
}

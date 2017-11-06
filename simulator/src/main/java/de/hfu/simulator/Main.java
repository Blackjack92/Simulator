package de.hfu.simulator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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

import coppelia.BoolW;
import coppelia.FloatWA;
import coppelia.IntW;
import coppelia.IntWA;
import coppelia.StringWA;
import coppelia.remoteApi;
import de.hfu.simulator.core.API;
import de.hfu.simulator.devices.PhantomXPincher;

public class Main {

	private final static Logger log = Logger.getLogger(Main.class.getName());

	static int etest = 0;
	static int etest2 = 0;
	static int etest3 = 0;
	static int etest4 = 0;
	static boolean sensordata;

	static API api;
	static remoteApi vrep;
	static int clientID;

	static PhantomXPincher device;
	
	public static void main(String[] args) throws Throwable {

		Config config = Config.getLocalhostConfig();
		api = new API(config);
		api.connect();
		
		device = new PhantomXPincher(api);
		
		vrep = api.getRemoteAPI();
		clientID = api.getClientId();
		
		log.setLevel(Level.ALL);
		log.log(Level.INFO, "Program starting...");
		slider();
		log.log(Level.INFO, "Program finished...");
	}

	public static void sensor() {

			System.out.println("Connected to remote API server");
			IntW sensor = new IntW(0);

			int ret = vrep.simxGetObjectHandle(clientID, "Proximity_sensor", sensor, vrep.simx_opmode_blocking);
			BoolW detState = new BoolW(false);
			FloatWA detectedPoint = new FloatWA(0);
			IntW detectedObjectHandle = new IntW(1);
			FloatWA SurfaceNormalVector = new FloatWA(1);
			// System.out.println(sensor.getValue());
			// vrep.simxReadProximitySensor(clientID, sensor.getValue(), detState, detPoint,
			// detectedObjectHandle, SurfaceNormalVector, vrep.simx_opmode_streaming);

			ret = vrep.simxReadProximitySensor(clientID, sensor.getValue(), detState, detectedPoint,
					detectedObjectHandle, SurfaceNormalVector, vrep.simx_opmode_blocking); // Try to retrieve the
																							// streamed data
			// After initialization of streaming, it will take a few ms before the first
			// value arrives, so check the return code
			// System.out.format("Mouse position x: %d\n",mouseX.getValue()); // Mouse
			// position x is actualized when the cursor is over V-REP's window
			// System.out.println("is ok");

			System.out.println("detected point coordinates: " + Arrays.toString(detectedPoint.getArray()));

			sensordata = detState.getValue();
			String s = String.valueOf(sensordata);
			System.out.println("detection state = " + s);

			GridLayout gridLayout = new GridLayout(0, 2);

			JDialog meinJDialog = new JDialog();
			// Titel wird gesetzt
			meinJDialog.setTitle("Sensormessung Proximity sensor ");
			// Breite und Höhe des Fensters werden
			// auf 200 Pixel gesetzt
			meinJDialog.setSize(945, 50);
			// Dialog wird auf modal gesetzt
			meinJDialog.setModal(true);
			// Wir lassen unseren Dialog anzeigen
			JLabel lab1 = new JLabel("detected point coordinates: " + Arrays.toString(detectedPoint.getArray()));
			JLabel lab2 = new JLabel("detection state = " + s);

			meinJDialog.add(lab1);
			meinJDialog.add(lab2);
			meinJDialog.setLayout(gridLayout);
			meinJDialog.setVisible(true);

	}

	public static void slider() {

		JPanel panel = new JPanel();
		panel.setSize(400, 200);
		panel.setVisible(true);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 0, 0, 7));

		JSlider slider1 = new JSlider(-180, 180, 0);
		slider1.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) { // vel


					// 1. First send a command to display a specific message in a dialog box:
					// IntWA inInts=new IntWA(1);
					IntWA inInts = new IntWA(4);
					etest = ((JSlider) e.getSource()).getValue();
					inInts.getArray()[0] = etest;

					inInts.getArray()[1] = etest2;
					inInts.getArray()[2] = etest3;
					inInts.getArray()[3] = etest4;
					// inInts.getArray()[0]=((JSlider) e.getSource()).getValue();
					StringWA outStrings = new StringWA(0);
					int result = vrep.simxCallScriptFunction(clientID, "PhantomXPincher",
							vrep.sim_scripttype_childscript, "Slider_function1", inInts, null, null, null, null, null,
							outStrings, null, vrep.simx_opmode_blocking);
					if (result == vrep.simx_return_ok)
						System.out.format("Returned message: %s\n", outStrings.getArray()[0]); // display the reply from
																								// V-REP (in this case,
																								// just a string)
					else
						System.out.format("Remote function call failed\n");

				}

				// System.out.println( ((JSlider) e.getSource()).getValue() );
		});

		JSlider slider2 = new JSlider(-180, 180, 0);
		;
		slider2.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) { // vel

					// 1. First send a command to display a specific message in a dialog box:
					IntWA inInts = new IntWA(4);

					etest2 = ((JSlider) e.getSource()).getValue();

					inInts.getArray()[0] = etest;

					inInts.getArray()[1] = etest2;
					inInts.getArray()[2] = etest3;
					inInts.getArray()[3] = etest4;
					StringWA outStrings = new StringWA(0);
					int result = vrep.simxCallScriptFunction(clientID, "PhantomXPincher",
							vrep.sim_scripttype_childscript, "Slider_function2", inInts, null, null, null, null, null,
							outStrings, null, vrep.simx_opmode_blocking);
					if (result == vrep.simx_return_ok)
						System.out.format("Returned message: %s\n", outStrings.getArray()[0]); // display the reply from
																								// V-REP (in this case,
																								// just a string)
					else
						System.out.format("Remote function call failed\n");

				}

		});

		// frame.add( slider2 );

		JSlider slider3 = new JSlider(-180, 180, 0);
		;
		// slider3.setPaintTicks( true );
		// slider3.setMajorTickSpacing( 10 );
		slider3.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

					// 1. First send a command to display a specific message in a dialog box:
					IntWA inInts = new IntWA(4);
					etest3 = ((JSlider) e.getSource()).getValue();

					inInts.getArray()[0] = etest;

					inInts.getArray()[1] = etest2;
					inInts.getArray()[2] = etest3;
					inInts.getArray()[3] = etest4;
					StringWA outStrings = new StringWA(0);
					int result = vrep.simxCallScriptFunction(clientID, "PhantomXPincher",
							vrep.sim_scripttype_childscript, "Slider_function3", inInts, null, null, null, null, null,
							outStrings, null, vrep.simx_opmode_blocking);
					if (result == vrep.simx_return_ok)
						System.out.format("Returned message: %s\n", outStrings.getArray()[0]); // display the reply from
																								// V-REP (in this case,
																								// just a string)
					else
						System.out.format("Remote function call failed\n");


				// System.out.println( ((JSlider) e.getSource()).getValue() );
			}
		});

		JSlider slider4 = new JSlider(-180, 180, 0);

		slider4.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

					// 1. First send a command to display a specific message in a dialog box:
					IntWA inInts = new IntWA(4);
					etest4 = ((JSlider) e.getSource()).getValue();

					inInts.getArray()[0] = etest;

					inInts.getArray()[1] = etest2;
					inInts.getArray()[2] = etest3;
					inInts.getArray()[3] = etest4;
					StringWA outStrings = new StringWA(0);
					int result = vrep.simxCallScriptFunction(clientID, "PhantomXPincher",
							vrep.sim_scripttype_childscript, "Slider_function4", inInts, null, null, null, null, null,
							outStrings, null, vrep.simx_opmode_blocking);
					if (result == vrep.simx_return_ok)
						System.out.format("Returned message: %s\n", outStrings.getArray()[0]); // display the reply from
																								// V-REP (in this case,
																								// just a string)
					else
						System.out.format("Remote function call failed\n");

			}
		});

		JButton gripperopen = new JButton("Open Gripper");
		gripperopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				device.openGripper();
			}
		});

		JButton gripperclose = new JButton("Close Gripper");
		gripperclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				device.closeGripper();
			}
		});

		JButton Sensor = new JButton("Sensor prüfen");
		Sensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sensor();

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

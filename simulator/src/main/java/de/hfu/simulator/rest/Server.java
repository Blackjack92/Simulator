package de.hfu.simulator.rest;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.net.InetSocketAddress;
import java.net.URI;

import java.io.*;

import de.hfu.simulator.rest.commands.*;

@SuppressWarnings("restriction")
public class Server {

	private final static Logger log = Logger.getLogger(Server.class.getName());

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

		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		log.log(Level.INFO, "Started webserver...");

		MyHandler handler = new MyHandler(device);
		server.createContext("/", handler);
		server.setExecutor(null); // creates a default executor
		server.start();
	}

	public Object commandList;

	static class MyHandler implements HttpHandler {

		private final PhantomXPincher device;;
		private final List<IGetCommand> getCommandList;
		private final List<IPostCommand> postCommandList;
		
		public MyHandler(PhantomXPincher device) {
			this.device = device;
			
			// GET commands
			IGetCommand open = new OpenCommand(device);
			IGetCommand close = new CloseCommand(device);
			IGetCommand proximity = new GetProximityCommand(device);
			IGetCommand moveRobot = new MoveRobotCommand(device);

			// POST commands
			IPostCommand execute = new ExecuteCommand(device);
			IPostCommand firstSlider = new MoveFirstSliderCommand(device);
			IPostCommand secondSlider = new MoveSecondSliderCommand(device);
			IPostCommand thirdSlider = new MoveThirdSliderCommand(device);
			IPostCommand fourthSlider = new MoveFourthSliderCommand(device);
			
			getCommandList = new ArrayList<IGetCommand>();
			postCommandList = new ArrayList<IPostCommand>();
			
			getCommandList.add(open);
			getCommandList.add(close);
			getCommandList.add(proximity);
			getCommandList.add(moveRobot);
			
			postCommandList.add(execute);
			postCommandList.add(firstSlider);
			postCommandList.add(secondSlider);
			postCommandList.add(thirdSlider);
			postCommandList.add(fourthSlider);
		}

		public void handle(HttpExchange t) throws IOException {
			String response;
			String uri = t.getRequestURI().toString();

			if (t.getRequestMethod().equalsIgnoreCase("GET")) {
				response = "GET requested";

				for (IGetCommand temp : getCommandList) {
					if (temp.getUri().equalsIgnoreCase(uri))
						response += temp.execute();
				}

			} else {
				response = "POST requested";

				for (IPostCommand temp : postCommandList) {
					if (temp.getUri().equalsIgnoreCase(uri))
						response += temp.execute(t);
				}
			}

			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}
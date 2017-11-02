package Simulator;

public class Config {

	private String ip;
	private int port;
	
	private Config(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	private static Config localhostConfig;
	
	public static Config getLocalhostConfig() {
		if (localhostConfig == null) {
			localhostConfig = new Config("127.0.0.1", 19999);
		}
		
		return localhostConfig;
	}

	public String getIp() {
		return this.ip;
	}
	
	public int getPort() {
		return this.port;
	}
	
	@Override
	public String toString() {
		return "IP: " + ip + "\t Port: " + port;
	}
}

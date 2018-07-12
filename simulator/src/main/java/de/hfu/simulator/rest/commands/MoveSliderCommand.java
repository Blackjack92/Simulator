package de.hfu.simulator.rest.commands;

import com.sun.net.httpserver.HttpExchange;

import de.hfu.simulator.devices.PhantomXPincher;

import java.io.*;

public abstract class MoveSliderCommand implements IPostCommand {

    protected PhantomXPincher device;

    public MoveSliderCommand(PhantomXPincher device) {
        this.device = device;
    }

    protected abstract boolean moveSlider(int value);
    
    public String execute(HttpExchange t) throws IOException {
    	if (t == null) {
    		return "No params are given.";
    	}
    	
        InputStreamReader inputStreamReader = new InputStreamReader(t.getRequestBody());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        // Read everything into a StringBuilder
        stringBuilder.append(bufferedReader.readLine());
        while (bufferedReader.ready()) {
            stringBuilder.append("\r\n");
            stringBuilder.append(bufferedReader.readLine());
        }

        String inputData = stringBuilder.toString();
        int value = Integer.parseInt(inputData);
        boolean result = moveSlider(value);
        String response = "\n path:" + getUri() + "\n" + "\n Slider moved by value = " + value;
		return response;
    }

}

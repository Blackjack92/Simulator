package de.hfu.simulator.rest.commands;

import com.sun.net.httpserver.HttpExchange;

import de.hfu.simulator.devices.PhantomXPincher;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class ExecuteCommand implements IPostCommand {

    private PhantomXPincher device;

    public ExecuteCommand(PhantomXPincher device) {
        this.device = device;
    }

    public String getUri() {
        return "/executeCommands";
    }

    public String execute(HttpExchange t) {
    	if (t == null) {
    		return "No params are given.";
    	}
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(t.getRequestBody());
            // get the first element
            Element element = doc.getDocumentElement();
            // get all child nodes
            NodeList nodes = element.getChildNodes();
            // print the text content of each child
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getTextContent());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Führe Befehle aus
        device.closeGripper();

        String response = "\n path:" + getUri() + "\n";
        return response;
    }

}

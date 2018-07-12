package de.hfu.simulator.rest.commands;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;

public interface IPostCommand {

    public String getUri();

    public String execute(HttpExchange t) throws IOException;

}
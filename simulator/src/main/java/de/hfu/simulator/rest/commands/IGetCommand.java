package de.hfu.simulator.rest.commands;

import java.io.IOException;

public interface IGetCommand {

    public String getUri();

    public String execute() throws IOException;

}
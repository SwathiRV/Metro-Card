package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class Geektrust {

	public static void main(String[] args) {
		for(int i=0;i<args.length;i++){
			run(args[i]);
		}
	}

	public static void run(String commandLineArgs){
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		BufferedReader reader;
		String inputfile = commandLineArgs;
		try{
			reader = new BufferedReader(new FileReader(inputfile));
			String line = reader.readLine();
			while(line != null){
				List<String> tokens = Arrays.asList(line.split(" "));
				commandInvoker.executeCommand(tokens.get(0), tokens);
				line = reader.readLine();
			}
			reader.close();
		}catch(IOException | NoSuchCommandException e){
			e.printStackTrace();
		}
	}

}
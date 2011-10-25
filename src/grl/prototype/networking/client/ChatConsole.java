package grl.prototype.networking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import grl.prototype.networking.Client;
import grl.prototype.scripting.Console;

public class ChatConsole extends Thread{
	Client client;
	public ChatConsole(Client client){
		this.client = client;
	}
	
	
	@Override
	public void run(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				String line = reader.readLine();
				client.sendMessage(ChatProcessor.createBroadcast(client, line));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String getInput(String prompt){
		System.out.print(prompt);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}
}

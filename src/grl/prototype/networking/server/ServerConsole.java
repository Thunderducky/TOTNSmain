package grl.prototype.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.python.core.PyObject;

import grl.prototype.networking.client.ChatProcessor;
import grl.prototype.scripting.Console;

import com.linearoja.Assets;
import com.linearoja.cm.Script;

public class ServerConsole extends Thread{
	private Script serverScript;
	private PyObject command;
	public ServerConsole(){
		serverScript = Assets.scripts.server;
		serverScript.loadData();
		Console.exec(serverScript);
		PyObject obj = Console.get("ServerCommands");
		System.out.println(obj.__dir__().toString());
	}
	@Override
	public void run(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				String line = reader.readLine();
				String[] parts = line.split(" ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

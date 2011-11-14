package grl.prototype.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.python.core.PyObject;

import grl.prototype.example.client.messages.chat.ChatInProcessor;
import grl.prototype.networking.Server;
import grl.prototype.scripting.Console;
import grl.prototype.scripting.InteractiveConsole;

import com.linearoja.Assets;
import com.linearoja.cm.Script;

public class ServerConsole extends Thread{
	private Script serverScript;
	private PyObject command;
	private Server server;
	public ServerConsole(Server server){
		this.server = server;
		serverScript = Assets.scripts.server;
		serverScript.loadData();
		Console.exec(serverScript);
		//PyObject obj = Console.get("ServerCommands");
		new InteractiveConsole().start();
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

package grl.prototype.scripting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveConsole extends Thread{
	@Override
	public void run(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				String line = reader.readLine();
				Console.exec(line);
			}
			catch (org.python.core.PyException e) {
				//e.printStackTrace();
				System.err.println("Unable to process command. Type 'help()' if you need a list of commands.");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

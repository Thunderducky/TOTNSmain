package grl.prototype.scripting;

import org.python.util.PythonInterpreter;

public class ScriptTest {
	public static int thevalue = 10;
	public static void main(String[] args){
        Console.exec("from grl.prototype.scripting import ScriptTest");
        Console.exec("ScriptTest.setValue(9*11)");
        Console.exec("print(ScriptTest.getValue())");
        Console.exec("print(\"test of printing\")");
	}
	public static int getValue(){
		return thevalue;
	}
	public static void setValue(int newvalue){
		thevalue = newvalue;
	}
}

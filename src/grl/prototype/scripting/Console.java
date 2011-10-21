package grl.prototype.scripting;

import org.python.util.PythonInterpreter;

public class Console {
    private static PythonInterpreter interp = new PythonInterpreter();
    private static LoggedPrintStream outstream;
    static {
        outstream = LoggedPrintStream.create(System.out);
        interp.setOut(outstream);
    }
	public static void exec(String command){
		interp.exec(command);
	}
	public static void eval(String thing){
		interp.eval(thing);
	}
	public static String getBuffer(){
		return outstream.buf.toString();
	}
}

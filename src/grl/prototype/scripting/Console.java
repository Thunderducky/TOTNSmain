package grl.prototype.scripting;

import org.python.util.PythonInterpreter;

import com.linearoja.cm.Script;

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
	public static void exec(Script script){
		interp.exec(script.getContents());
	}
	public static void eval(String thing){
		interp.eval(thing);
	}
	public static void eval(Script script){
		interp.eval(script.getContents());
	}
	public static String getBuffer(){
		return outstream.buf.toString();
	}
}

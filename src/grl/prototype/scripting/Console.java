package grl.prototype.scripting;

import java.io.OutputStream;

import org.python.core.PyObject;
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
		interp.exec(script.getCompiled());
	}
	public static void eval(String thing){
		interp.eval(thing);
	}
	public static void eval(Script script){
		interp.eval(script.getContents());
	}
	public static PyObject get(String name){
		return interp.get(name);
	}
	public static OutputStream getOutputStream(){
		return outstream;
	}
	public static StringBuilder getOuputBuffer(){
		return outstream.buf;
	}
	public static PyObject compileScript(Script script){
		return interp.compile(script.getContents());
	}
}

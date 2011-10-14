package grl.prototype.messaging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Message {
	private Stack<String> typeHierarchy = new Stack<String>();
	private Map<String,Object> arguments = new HashMap<String,Object>();
	public Message(String type){
		String[] types = type.split("\\.");
		for(int i=types.length-1; i>=0; i--){
			typeHierarchy.push(types[i]);
		}

	}
	public Message(String type, Map<String,Object> arguments){
		this(type);
		this.arguments = arguments;
	}
	public Map<String,Object> getArguments(){
		return arguments;
	}
	public void setArgument(String key, Object value){
		arguments.put(key, value);
	}
	public boolean hasArgument(String key){
		return arguments.containsKey(key);
	}
	public Object getArgument(String key){
		Object obj = arguments.get(key);
		if(obj!=null)
			return obj;
		throw new RuntimeException("Null value for key '"+key+"'");
	}
	public String getArgumentString(String key){
		Object obj = getArgument(key);
		return obj.toString();
	}
	public int getArgumentInt(String key){
		Object obj = getArgument(key);
		if(obj instanceof Integer)
			return ((Integer)obj).intValue();
		else
			return Integer.parseInt(obj.toString());
	}
	public double getArgumentDouble(String key){
		Object obj = getArgument(key);
		if(obj instanceof Double)
			return ((Double)obj).doubleValue();
		else
			return Double.parseDouble(obj.toString());
	}

	public Stack<String> getTypeHierarchy(){
		return typeHierarchy;
	}
}

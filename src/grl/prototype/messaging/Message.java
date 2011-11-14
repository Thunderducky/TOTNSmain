package grl.prototype.messaging;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Message implements Serializable{
	//private Map<String,Object> arguments = new HashMap<String,Object>();
	//private String type = "Message";
	/*public Message(){
		
	}
	public Message(KeyValuePair... arguments){
		for(KeyValuePair entry:arguments){
			this.arguments.put(entry.getKey(), entry.getValue());
		}
	}
	public Message( Map<String,Object> arguments){
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
	*/
}

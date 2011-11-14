package grl.prototype.messaging;

import java.util.Map.Entry;

public class KeyValuePair implements Entry<String, Object> {
	private String key;
	private Object value;
	public KeyValuePair(String key, Object value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object setValue(Object value) {
		Object oldVal = this.value;
		this.value = value;
		return oldVal;
	}
	
}

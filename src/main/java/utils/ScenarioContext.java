package utils;

import java.util.HashMap;
import java.util.Map;
public class ScenarioContext {

    private Map<String, Object> data = new HashMap<>();


    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }

    public void clear() {
        data.clear();
    }
}

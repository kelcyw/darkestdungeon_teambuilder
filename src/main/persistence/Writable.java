package persistence;

import org.json.JSONObject;

// modelled after JsonSerializationDemo example

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

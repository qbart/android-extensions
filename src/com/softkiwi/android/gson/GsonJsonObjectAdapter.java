package com.softkiwi.android.gson;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Handles serialization and deserialization of JsonObject.
 */
public class GsonJsonObjectAdapter implements JsonDeserializer<JsonObject>, JsonSerializer<JsonObject> {

	@Override
	public JsonObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		//take json object if you can
		if (jsonElement != null && jsonElement.isJsonObject()) {
			return jsonElement.getAsJsonObject();
		}

		return null;
	}

	@Override
	public JsonElement serialize(JsonObject jsonObject, Type type, JsonSerializationContext jsonSerializationContext) {
		//simply pass json object for later usage
		return jsonObject;
	}

}

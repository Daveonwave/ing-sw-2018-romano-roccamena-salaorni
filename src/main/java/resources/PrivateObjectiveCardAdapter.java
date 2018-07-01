package resources;

import com.google.gson.*;

import java.lang.reflect.Type;

public class PrivateObjectiveCardAdapter implements JsonSerializer<PrivateObjectiveCardAdapter>,
        JsonDeserializer<PrivateObjectiveCardAdapter> {


    @Override
    public JsonElement serialize(PrivateObjectiveCardAdapter src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }



    @Override
    public PrivateObjectiveCardAdapter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try{
            return context.deserialize(element, Class.forName("mvc.model.objects." + type));
        } catch(ClassNotFoundException e){
            throw new JsonParseException("Unknown element type: " + type, e);
        }
    }

}

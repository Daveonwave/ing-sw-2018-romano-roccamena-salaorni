package resources.writers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import mvc.model.objects.PrivateObjectiveCard;

import java.io.IOException;

public class PrivateObjectiveCardAdapter extends TypeAdapter<PrivateObjectiveCard> {

    @Override
    public void write(JsonWriter out, PrivateObjectiveCard value) throws IOException {
        out.beginObject();
        out.name("name").value(value.getName());
        out.name("description").value(value.getDescription());
        out.name("color").value(value.getColor().toString());
        out.endObject();
    }

    @Override
    public PrivateObjectiveCard read(JsonReader in) throws IOException {
        PrivateObjectiveCard card;

        in.beginObject();
        while(in.hasNext()){
            switch (in.nextName()){
                case "name":
                    //Todo: typeAdapter

            }
        }



        return null;
    }
}

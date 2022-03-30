package org.acme.mongodb.panache.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class StartTimeSerializer extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Date startTime = (Date) value;
        gen.writeStartObject();
//        gen.writeNumberField("minutes", startTime.toMinutes());
//        gen.writeStringField("text_rep", String.format("%d:%02d", dur.toHours(), dur.toMinutesPart()));
        gen.writeEndObject();
    }
}

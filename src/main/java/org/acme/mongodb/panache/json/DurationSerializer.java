package org.acme.mongodb.panache.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.Format;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DurationSerializer extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Duration dur = (Duration) value;
        gen.writeStartObject();
        gen.writeNumberField("minutes", dur.toMinutes());
        gen.writeStringField("text_rep", String.format("%d:%02d", dur.toHours(), dur.toMinutesPart()));
        gen.writeEndObject();
    }
}

package org.acme.mongodb.panache.bson;

import org.acme.mongodb.panache.entity.Training;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class TrainingCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz.equals(Training.class)) {
            return (Codec<T>) new TrainingCodec();
        }
        return null;
    }
}

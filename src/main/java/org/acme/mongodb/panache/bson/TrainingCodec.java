package org.acme.mongodb.panache.bson;

import com.mongodb.MongoClientSettings;
import org.acme.mongodb.panache.entity.Sport;
import org.acme.mongodb.panache.entity.Training;
import org.bson.Document;
import org.bson.BsonWriter;
import org.bson.BsonValue;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.UUID;

public class TrainingCodec implements CollectibleCodec<Training> {
    private final Codec<Document> documentCodec;

    public TrainingCodec() {
        this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public void encode(BsonWriter writer, Training training, EncoderContext encoderContext) {
        Document doc = training.document;
        doc.put("pic_url_list", training.picUrlList);
        // ...
//        doc.put("description", fruit.getDescription());
        documentCodec.encode(writer, doc, encoderContext);


        System.out.println("encode: " + doc);
    }

    @Override
    public Class<Training> getEncoderClass() {
        return Training.class;
    }

    @Override
    public Training generateIdIfAbsentFromDocument(Training document) {
        if (!documentHasId(document)) {
            document.id = new ObjectId(UUID.randomUUID().toString());
        }
        return document;
    }

    @Override
    public boolean documentHasId(Training document) {
        return document.id != null;
    }

    @Override
    public BsonValue getDocumentId(Training document) {
        return new BsonString(document.id.toString());
    }

    @Override
    public Training decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        Training training = new Training();
        training.document = document;
        if (document.get("_id") != null) {
            training.id = (ObjectId) document.get("_id");
        }
        training.sport = Sport.of(document.getInteger("sport"));
        training.startTime = document.getDate("start_time");
        Number duration_sec = document.get("duration_sec", Number.class);
        Number duration = document.get("duration", Number.class);
        int durSeconds = (duration_sec != null) ? duration_sec.intValue() :
                (duration != null) ? duration.intValue() : 0;
        training.duration = Duration.ofSeconds(durSeconds);

        List picUrlList = document.get("pic_url_list", List.class);
        training.picUrlList = picUrlList;

        return training;
    }

}

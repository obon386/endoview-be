package org.acme.mongodb.panache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.acme.mongodb.panache.json.DurationSerializer;
import org.acme.mongodb.panache.json.StartTimeSerializer;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@MongoEntity(collection = "Trainings")
public class Training extends PanacheMongoEntity {
    @JsonIgnore
    public Document document;

    public Sport sport;

//    @JsonSerialize(using = StartTimeSerializer.class)
    public Date startTime;

    @JsonSerialize(using = DurationSerializer.class)
    public Duration duration;

    @BsonProperty("pic_url_list")
    public List<String> picUrlList;

}

package org.acme.mongodb.panache.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.List;

public class Pictures extends PanacheMongoEntity {
    public String trainingId;
    public List<String> pictureUrlList;


}

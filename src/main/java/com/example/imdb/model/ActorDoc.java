package com.example.imdb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("actors")
public class ActorDoc {

    @Id
    private String mongoId;

    @Indexed(unique = true)
    private Integer id;

    private String name;

    public ActorDoc() {}

    public ActorDoc(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getMongoId() { return mongoId; }
    public void setMongoId(String mongoId) { this.mongoId = mongoId; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

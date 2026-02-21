package com.example.imdb.graphql;

import com.example.imdb.dto.ActorInput;
import com.example.imdb.dto.ActorUpdateInput;
import com.example.imdb.model.ActorDoc;
import com.example.imdb.service.ActorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ActorGraphql {

    private final ActorService actorService;

    public ActorGraphql(ActorService actorService) {
        this.actorService = actorService;
    }

    @QueryMapping
    public List<ActorDoc> getAllActors() {
        return actorService.getAllActors();
    }

    @QueryMapping
    public ActorDoc getActorById(@Argument int id) {
        return actorService.getActorById(id);
    }

    @QueryMapping
    public List<ActorDoc> searchActorsByName(@Argument String nameContains) {
        return actorService.searchActorsByName(nameContains);
    }

    @MutationMapping
    public ActorDoc createActor(@Argument ActorInput actor) {
        return actorService.createActor(actor);
    }

    @MutationMapping
    public ActorDoc updateActor(@Argument int id, @Argument ActorUpdateInput update) {
        return actorService.updateActor(id, update);
    }

    @MutationMapping
    public String deleteActor(@Argument int id) {
        return actorService.deleteActor(id);
    }
}

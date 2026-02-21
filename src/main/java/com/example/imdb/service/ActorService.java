package com.example.imdb.service;

import com.example.imdb.dto.ActorInput;
import com.example.imdb.dto.ActorUpdateInput;
import com.example.imdb.model.ActorDoc;
import com.example.imdb.repo.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepo;

    public ActorService(ActorRepository actorRepo) {
        this.actorRepo = actorRepo;
    }

    public List<ActorDoc> getAllActors() {
        return actorRepo.findAll();
    }

    public ActorDoc getActorById(int id) {
        return actorRepo.findByIdIs(id).orElse(null);
    }

    public List<ActorDoc> searchActorsByName(String nameContains) {
        return actorRepo.findByNameContainingIgnoreCase(nameContains);
    }

    public ActorDoc createActor(ActorInput actor) {
        ActorDoc doc = new ActorDoc(actor.getId(), actor.getName());
        return actorRepo.save(doc);
    }

    public ActorDoc updateActor(int id, ActorUpdateInput update) {
        ActorDoc existing = actorRepo.findByIdIs(id)
                .orElseThrow(() -> new NotFoundException("Actor not found: " + id));

        if (update.getName() != null) existing.setName(update.getName());

        return actorRepo.save(existing);
    }

    public String deleteActor(int id) {
        actorRepo.deleteByIdIs(id);
        return "Actor deleted: " + id;
    }

    /**
     * Upsert helper used when movies contain embedded actors.
     */
    public void upsertActor(int id, String name) {
        ActorDoc doc = actorRepo.findByIdIs(id).orElse(new ActorDoc(id, name));
        doc.setName(name);
        actorRepo.save(doc);
    }
}

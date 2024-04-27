package com.github.beatrizgomees.api.rheumanotes.notes.data;

import com.github.beatrizgomees.api.rheumanotes.notes.entity.Note;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;


@ApplicationScoped
public class NoteData implements PanacheMongoRepository {
    private final MongoClient mongoClient;

    public NoteData(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public InsertOneResult createNote(Document document) {
        return getCollection("notes").insertOne(document);
    }

    public FindIterable<Document> getAllNote(FindIterable<Document> documents) {
         documents = getCollection("notes").find();
        return documents;
    }

    public Document findNoteById(String id){
        return getCollection("notes").find(eq("_id", new ObjectId(String.valueOf(id)))).first();
    }

    public void deleteNoteById(String id) {
        getCollection("notes").findOneAndDelete(eq("_id", new ObjectId(id)));
    }

    private MongoCollection<Document> getCollection(String collectionName) {
        return mongoClient.getDatabase("rheumaplanner").getCollection(collectionName);
    }
}



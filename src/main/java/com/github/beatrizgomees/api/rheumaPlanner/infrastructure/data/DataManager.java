package com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


@ApplicationScoped
public class DataManager implements PanacheMongoRepository {
    private final MongoClient mongoClient;

    public DataManager(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public InsertOneResult create(Document document, String collectionName) {
        return getCollection(collectionName).insertOne(document);
    }

    public List<Document> getAll(String collectionName) {
        FindIterable<Document> iterable = getCollection(collectionName).find();
        List<Document> documents = new ArrayList<>();

        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }

        return documents;
    }

    public Document findByIdGeneral(String id, String collectionName){
        return getCollection(collectionName).find(eq("_id", new ObjectId(String.valueOf(id)))).first();
    }

    public Document findByNameGeneral(String name, String collectionName) {
        return getCollection(collectionName).find(eq("name", name)).first();
    }

    public void delete(String id, String collectionName) {
        getCollection(collectionName).findOneAndDelete(eq("_id", new ObjectId(id)));
    }

    public Document updateGenereal(String id, Document updateNote, String collectionName){
        Document filter = new Document("_id", new ObjectId(id));
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER);
        return getCollection(collectionName).findOneAndUpdate(filter, updateNote, options);
    }


    private MongoCollection<Document> getCollection(String collectionName) {
        return mongoClient.getDatabase("planner").getCollection(collectionName);
    }
}



package com.github.beatrizgomees.api.rheumanotes.notes;

import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class NoteControllerTest {


    @Test
    public void createNotesTest(NoteRequest noteRequest){
        given().when().get("/notes").then().statusCode(201).body(is(noteRequest));
    }
}

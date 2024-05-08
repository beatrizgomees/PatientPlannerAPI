package com.github.beatrizgomees.api.rheumaPlanner.domain.todoList;


import com.github.beatrizgomees.api.rheumaPlanner.domain.note.Note;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@MongoEntity(collection = "todoList")

public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private boolean done;

    private LocalDateTime reminder;

    @ManyToOne
    @JoinColumn(name = "notes_id")
    private Note note;



    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getReminder() {
        return reminder;
    }

    public void setReminder(LocalDateTime reminder) {
        this.reminder = reminder;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", reminder=" + reminder +
                '}';
    }
}

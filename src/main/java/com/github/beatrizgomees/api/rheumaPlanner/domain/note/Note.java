package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoList;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@MongoEntity(collection = "notes")
public class Note{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "date_consult")
    private LocalDateTime dateConsult;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "note")
    private List<TodoList> todoList;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateConsult() {
        return dateConsult;
    }

    public void setDateConsult(LocalDateTime dateConsult) {
        this.dateConsult = dateConsult;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", doctor=" + doctor +
                ", dateConsult=" + dateConsult +
                ", createdAt=" + createdAt +
                '}';
    }
}

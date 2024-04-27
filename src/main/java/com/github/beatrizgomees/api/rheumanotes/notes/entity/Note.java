package com.github.beatrizgomees.api.rheumanotes.notes.entity;

import com.github.beatrizgomees.api.rheumanotes.doctor.entity.Doctor;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.*;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
public class Note{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime dateConsult;

    private LocalDateTime createdAt;

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
}

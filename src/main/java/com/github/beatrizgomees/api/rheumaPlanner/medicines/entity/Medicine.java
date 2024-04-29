package com.github.beatrizgomees.api.rheumaPlanner.medicines.entity;


import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@MongoEntity(collection = "medicines")
public class Medicine extends PanacheMongoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private int amount;

    private LocalDateTime reminder;

    private MedicalSpecialty medicalSpecialty;


    public UUID getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getReminder() {
        return reminder;
    }

    public void setReminder(LocalDateTime reminder) {
        this.reminder = reminder;
    }

    public MedicalSpecialty getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(MedicalSpecialty medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", reminder=" + reminder +
                '}';
    }
}

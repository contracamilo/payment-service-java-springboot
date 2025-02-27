package com.payment_system.u_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String id;

    private String name;
    private String lastName;

    @Column(unique = true)
    private String code;

    private String email;
    private String programId;
    private String photo;
}

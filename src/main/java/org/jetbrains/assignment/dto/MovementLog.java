package org.jetbrains.assignment.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movement_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_data")
    private String inputData;

    @Column(name = "output_data")
    private String outputData;
}
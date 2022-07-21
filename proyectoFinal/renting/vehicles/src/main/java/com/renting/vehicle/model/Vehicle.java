package com.renting.vehicle.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "vehicle")
public class Vehicle {
    @Transient
    public static final String SEQUENCE_NAME = "vehicle_sequence";
    @Indexed(unique=true, direction= IndexDirection.DESCENDING)
    @Id
    private Long id;
    
    private String placa;
    private String descriptionVehicle;
    //@JsonFormat
    private String datePurchase;
    private String status;

}

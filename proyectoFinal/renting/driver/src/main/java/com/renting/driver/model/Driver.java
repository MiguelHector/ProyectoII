package com.renting.driver.model;

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
@Document(collection = "driver")
public class Driver {
    @Transient
    public static final String SEQUENCE_NAME = "driver_sequence";
    @Indexed(unique=true, direction= IndexDirection.DESCENDING)
    @Id
    private Long id;

    private String dni;
    private String names;
    private String lastName;
    private String license;
    private String category;
    private String dateBirth;
    private String status;

}

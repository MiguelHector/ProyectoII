package com.renting.serviceLog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "driver")
public class Driver {

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

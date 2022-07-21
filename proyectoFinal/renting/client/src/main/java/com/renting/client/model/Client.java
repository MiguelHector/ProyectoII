package com.renting.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "client")
public class Client {
    @Transient
    public static final String SEQUENCE_NAME = "client_sequence";
    @Indexed(unique=true, direction= IndexDirection.DESCENDING)
    @Id
    private Long id;
    
    private String ruc;
    private String companyName;
    private String department;
    private String province;
    private String district;
    private String status;


}

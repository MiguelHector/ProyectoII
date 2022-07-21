package com.renting.serviceTrans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "service")
public class ServiceTrans {
    @Transient
    public static final String SEQUENCE_NAME = "service_sequence";
    @Indexed(unique=true, direction= IndexDirection.DESCENDING)
    @Id
    private Long id;

    private String dniDriver;
    private String ruc;
    private String placa;
    private String dateExit;
    private String dateReturn;
    private String destinity;
    private Double costService;
    private Double feeDriver;
    private String status;
}

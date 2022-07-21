package com.renting.serviceLog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Document(collection = "serviceLog")
public class ServiceLog {
    @Transient
    public static final String SEQUENCE_NAME = "serviceLog_sequence";
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @Id
    private Long id;

    private String idServTrans;
    private String typeLog;//SALIDA,LLEGADA_DESTINO,EN_TRANSITO,DE_RETORNO,LLEGADA_COCHERA
    private String dateLog;
    private String km; //kilometraje
    private String observation;

    @JsonIgnore
    private String dniDriver;
    @JsonIgnore
    private String ruc;
    @JsonIgnore
    private String placa;

}

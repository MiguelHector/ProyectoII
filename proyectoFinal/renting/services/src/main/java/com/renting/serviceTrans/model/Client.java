package com.renting.serviceTrans.model;

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
@Document(collection = "client")
public class Client {

    @Id
    private Long id;

    private String ruc;
    private String companyName;
    private String department;
    private String province;
    private String district;
    private String status;

}

package net.apmoller.athena.microservices.CurrencyProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class RoundOff
{
    @Id
    private String roundingOffPoint;
    private int roundOffValue;
    private String createdBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdDate;


}
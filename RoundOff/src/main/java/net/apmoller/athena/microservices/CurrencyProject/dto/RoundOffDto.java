package net.apmoller.athena.microservices.CurrencyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class    RoundOffDto
{
    private String roundingOffPoint;
    private int roundOffValue;
    private String createdBy;
    private String createdDate;

}


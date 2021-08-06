package net.apmoller.athena.microservices.CurrencyProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoundOffError {
    private String Details;
    private String errorMessage;

}

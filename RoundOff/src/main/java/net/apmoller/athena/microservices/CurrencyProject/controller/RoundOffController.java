package net.apmoller.athena.microservices.CurrencyProject.controller;

import net.apmoller.athena.microservices.CurrencyProject.dto.RoundOffDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.RoundOffException;
import net.apmoller.athena.microservices.CurrencyProject.services.RoundOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/roundoff")

public class RoundOffController
{
    @Autowired
    private RoundOffService roundOffService;
    @PostMapping                                                    //INSERT ROUND OFF DATA
    public Mono<RoundOffDto> saveRoundData(@RequestBody Mono<RoundOffDto> roundOffDtoMono)  {
        return roundOffService.saveRoundOffData(roundOffDtoMono);
    }

    @GetMapping                                                     //GET ALL ROUND OFF DATAS
    public Flux<RoundOffDto> getAllRoundDatas()
    {
        return roundOffService.getAllRoundOffDatas();
    }

    @GetMapping("/{id}")                                             //GET DATA BY ROUNDOFFRULE
    public Mono<RoundOffDto> getDataByRoundOffRule(@PathVariable String id) throws RoundOffException {
        Mono<RoundOffDto> getDataByRoundOffRule = roundOffService.getDataByRoundOffRule(id) ;
        return getDataByRoundOffRule;
    }

    @GetMapping("/allcodes")                                         //GET ALL ROUND OFF CODES
    public Mono<List<String>> getAllCodes()
    {
        return roundOffService.getAllRoundOffCode();
    }

}

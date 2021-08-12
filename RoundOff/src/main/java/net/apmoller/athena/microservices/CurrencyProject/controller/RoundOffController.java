package net.apmoller.athena.microservices.CurrencyProject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.apmoller.athena.microservices.CurrencyProject.dto.RoundOffDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.RoundOffException;
import net.apmoller.athena.microservices.CurrencyProject.services.RoundOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/roundoff")
@ApiResponses(value =
        {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                @ApiResponse(code = 202,message = "Inserted sucessfully")
        }
)
@Api(value="Round Off EndPoints", description="Operations are performed in Round Off")


public class RoundOffController
{
    @Autowired
    private RoundOffService roundOffService;

    //GET ALL ROUND OFF DATAS
    @ApiOperation(value = "To Get All Round Off Records",produces = "application/JSOn")
    @GetMapping
    public Flux<RoundOffDto> getAllRoundDatas()
    {
        return roundOffService.getAllRoundOffDatas();
    }

    //INSERT ROUND OFF DATA
    @ApiOperation(value = "To Save the Round Off Records into The DataBase", response = HttpStatus.class)
    @PostMapping
    public Mono<RoundOffDto> saveRoundData(@RequestBody Mono<RoundOffDto> roundOffDtoMono)  {
        return roundOffService.saveRoundOffData(roundOffDtoMono);
    }

    //GET DATA BY ROUNDOFFRULE
    @ApiOperation(value = "To Get All Round Off Records by Rounding off point ",produces = "application/JSOn")
    @GetMapping("/{id}")
    public Mono<RoundOffDto> getDataByRoundOffRule(@PathVariable String id) throws RoundOffException {
        Mono<RoundOffDto> getDataByRoundOffRule = roundOffService.getDataByRoundOffRule(id) ;
        return getDataByRoundOffRule;
    }


    @ApiOperation(value = "To Get All Round Off Codes",produces = "application/JSOn")
    @GetMapping("/allcodes")
    public Mono<List<String>> getAllCodes()
    {
        return roundOffService.getAllRoundOffCode();
    }

    //UPDATE ROUND OFF DATA BY ID
//    @ApiOperation(value = "To Save the Currency Conversion into The DataBase", response = HttpStatus.class)
//    @PutMapping("/update/{id}")
//    public Mono<RoundOffDto> updateRoundData(@RequestBody Mono<RoundOffDto> roundOffDtoMono, @PathVariable String id)
//    {
//        return roundOffService.updateRoundOffData(roundOffDtoMono,id);
//    }


}

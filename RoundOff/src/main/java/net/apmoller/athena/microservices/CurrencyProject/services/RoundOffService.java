package net.apmoller.athena.microservices.CurrencyProject.services;

import net.apmoller.athena.microservices.CurrencyProject.dto.RoundOffDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.RoundOffException;
import net.apmoller.athena.microservices.CurrencyProject.repository.RoundOffRepository;
import net.apmoller.athena.microservices.CurrencyProject.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundOffService
{
    @Autowired
    private RoundOffRepository roundOffRepository;

    public Flux<RoundOffDto> getAllRoundOffDatas()
    {
        return roundOffRepository.findAll()
                .map(AppUtils::roundOffEntityToDto);
    }

    public Mono<RoundOffDto> saveRoundOffData(Mono<RoundOffDto> roundOffDtoMono)
    {
        return roundOffDtoMono.map(AppUtils::roundOffDtoToEntity)
                .flatMap(roundOffRepository::insert)
                .map(AppUtils::roundOffEntityToDto);

    }

    public Mono<RoundOffDto> getDataByRoundOffRule(String id) throws RoundOffException
    {
        return roundOffRepository
                .findById(id)
                .map(AppUtils::roundOffEntityToDto).switchIfEmpty(Mono.defer(()->Mono.error(new RoundOffException("Invalid ID Found"))));
    }

    public Mono<List<Integer>> getAllRoundOffCode()
    {
        return roundOffRepository
                .findAll()
                .map(AppUtils::roundOffEntityToDto)
                .map(e->e.getRoundOffValue())
                .collect(Collectors.toList());
    }

//    public Mono<RoundOffDto> updateRoundOffData(Mono<RoundOffDto> roundOffDtoMono, String id)
//    {
//        return roundOffRepository.findById(id)
//                .flatMap(p->roundOffDtoMono.map(AppUtils :: roundOffDtoToEntity).doOnNext(e->e.setRoundingOffRule(id)))
//                .flatMap(roundOffRepository::save)
//                .map(AppUtils::roundOffEntityToDto);
//    }

}

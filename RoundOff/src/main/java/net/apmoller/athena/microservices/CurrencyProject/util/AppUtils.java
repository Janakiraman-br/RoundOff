package net.apmoller.athena.microservices.CurrencyProject.util;

import net.apmoller.athena.microservices.CurrencyProject.dto.RoundOffDto;
import net.apmoller.athena.microservices.CurrencyProject.models.RoundOff;
import org.springframework.beans.BeanUtils;

public class AppUtils
{
    public static RoundOffDto roundOffEntityToDto(RoundOff roundOff)
    {
        RoundOffDto roundOffDto =new RoundOffDto();
        BeanUtils.copyProperties(roundOff,roundOffDto); // Source,Destination ,Copying product components to productDto
        return roundOffDto;
    }
    public static RoundOff roundOffDtoToEntity(RoundOffDto roundOffDto)
    {
        RoundOff roundOff= new RoundOff();
        BeanUtils.copyProperties(roundOffDto,roundOff);
        return roundOff;
    }
}


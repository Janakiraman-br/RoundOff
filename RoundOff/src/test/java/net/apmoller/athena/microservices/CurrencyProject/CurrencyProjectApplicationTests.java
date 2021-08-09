package net.apmoller.athena.microservices.CurrencyProject;

import net.apmoller.athena.microservices.CurrencyProject.controller.RoundOffController;
import net.apmoller.athena.microservices.CurrencyProject.dto.RoundOffDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.RoundOffException;
import net.apmoller.athena.microservices.CurrencyProject.services.RoundOffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RoundOffController.class)
@Import(RoundOffService.class)
class CurrencyProjectApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private RoundOffService service;

	@Test
	public void addProductTest() {
		Mono<RoundOffDto> productDtoMono = Mono.just(new RoundOffDto("1",2,"JKR",null));
		when(service.saveRoundOffData(productDtoMono)).thenReturn(productDtoMono);

		webTestClient.post().uri("/roundoff")
				.body(Mono.just(productDtoMono),RoundOffDto.class)
				.exchange()
				.expectStatus().isOk();//200

	}

	@Test
	public void getProductsTest(){
		Flux<RoundOffDto> productDtoFlux=Flux.just(new RoundOffDto("1",2,"JKR",null));

		when(service.getAllRoundOffDatas()).thenReturn(productDtoFlux);

		Flux<RoundOffDto> responseBody = webTestClient.get().uri("/roundoff")
				.exchange()
				.expectStatus().isOk()
				.returnResult(RoundOffDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new RoundOffDto("1",2,"JKR",null))
				.verifyComplete();

	}
	@Test
	public void getProductTest() throws RoundOffException {
		Mono<RoundOffDto> productDtoMono=Mono.just(new RoundOffDto("1",2,"JKR",null));
		when(service.getDataByRoundOffRule(any())).thenReturn(productDtoMono);

		Flux<RoundOffDto> responseBody = webTestClient.get().uri("/roundoff/JKR")
				.exchange()
				.expectStatus().isOk()
				.returnResult(RoundOffDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getCreatedBy().equals("JKR"))
				.verifyComplete();
	}
}

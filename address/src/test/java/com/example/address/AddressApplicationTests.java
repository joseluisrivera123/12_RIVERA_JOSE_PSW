package com.example.address;

import com.example.address.domain.dto.AddressRequestDto;
import com.example.address.domain.dto.AddressResponseDto;
import com.example.address.web.AddressController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressApplicationTests {


	@Autowired
	private AddressController addressController;

	@Test
	void FindAllTest() {
		Flux<AddressResponseDto> result = addressController.findAllActive();
		System.out.println(result.collectList().block());
		assertNotNull(result.collectList().block());
	}


	@Test
	void FindAllInactiveTest() {
		Flux<AddressResponseDto> result = addressController.findAllInactive();
		System.out.println(result.collectList().block());
		assertNotNull(result.collectList().block());
	}


	@Test
	void FindIdAddress() {
		int id = 1;
		Mono<AddressResponseDto> result = addressController.finById(id);
		AddressResponseDto address = result.block();
		if (address != null){
			assertNotNull(address);
		}else {
			System.out.println("No se encontró ningúna direcion con el ID: " + id);
			fail("El resultado debería ser no nulo.");
		}
	}

//	@Test
//	void CreateAttorneyTest(){
//		AddressRequestDto attorneyRequestDto = new AddressRequestDto("Imperial",1,"A","122345");
//		Mono<AddressResponseDto> result = addressController.create(attorneyRequestDto);
//		AddressResponseDto createdAttorney = result.block();
//		assertNotNull(createdAttorney);
//
//		Mono<AddressResponseDto> findResult = addressController.finById(createdAttorney.getId());
//		AddressResponseDto foundAttorney = findResult.block();
//
//		assertNotNull(foundAttorney);
//		assertEquals(createdAttorney.getId(), foundAttorney.getId());
//	}
}

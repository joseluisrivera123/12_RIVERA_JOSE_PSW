package com.soa.canete.transaccional_allocation_soa_canete.service;

import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.DataTeenFuncionaryTransaccional;
import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.transaccional_allocation_soa_canete.domain.model.TransaccionalAllocation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TransaccionalAllocationService {

    Mono<DataTeenFuncionaryTransaccional> findById(Integer id_funcionaryteend);
    Flux<DataTeenFuncionaryTransaccional> findAll();
    Mono<TransaccionalAllocation> saveNewDataTransaccional(TransaccionalAllocationRequestDto request);

    Mono<TransaccionalAllocationResponseDto> updateDataTransaccional(TransaccionalAllocationRequestDto request, Integer id);
    Flux<DataTeenFuncionaryTransaccional> findAllIdtutor(Integer idTutor);

    Mono<TransaccionalAllocationResponseDto> saveNewAsignamentTutor(TransaccionalAllocationRequestDto request);
}

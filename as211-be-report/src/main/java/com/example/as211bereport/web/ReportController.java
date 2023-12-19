package com.example.as211bereport.web;

import com.example.as211bereport.domain.model.attendanceViewDto;
import com.example.as211bereport.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    final ReportService reportService;

    @GetMapping("/address/{idAdolescente}")
    public ResponseEntity<Resource> exportAddress(@PathVariable Integer idAdolescente, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
        return this.reportService.exportAddress(idAdolescente, token);
    }
    @GetMapping("/funcionary/{idFuncionary}")
    public ResponseEntity<Resource> exportFuncionary(@PathVariable Integer idFuncionary, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
        return this.reportService.exportFuncionary(idFuncionary,token);
    }

    @GetMapping("/attendance/{idActivity}")
    public ResponseEntity<Resource> exportAttendance(@PathVariable Integer idActivity, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
        return this.reportService.exportAttendance(idActivity,token);
    }
}

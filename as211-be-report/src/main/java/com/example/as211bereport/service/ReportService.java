package com.example.as211bereport.service;

import com.example.as211bereport.domain.model.Activities;
import com.example.as211bereport.domain.model.Address;
import com.example.as211bereport.domain.model.Teen;
import com.example.as211bereport.domain.model.attendanceViewDto;
import com.example.as211bereport.domain.model.funcionaryTeen.DataTeenFuncionaryTransaccional;
import com.example.as211bereport.domain.model.funcionaryTeen.reportFuncionary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResponseEntity<Resource> exportAddress(int idAdolescente, String authToken) {

        Mono<Teen> teen = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/api/teenData/"+idAdolescente)
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve()
                .bodyToMono(Teen.class);



        Flux<Address> address = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/v1/address/adolescente/" + idAdolescente)
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve()
                .bodyToFlux(Address.class)
                .map(adr ->{
                    if(adr.getActive().equals("A")){
                        adr.setActive("activo");
                    }else {
                        adr.setActive("Inactivo");
                    }
                    return adr;
                });

        try {

            final HashMap<String, Object> parameters = new HashMap<>();
            teen.subscribe(adoles -> parameters.put("adolescente", adoles.getNombreCompleto()));
            parameters.put("ds", new JRBeanCollectionDataSource(address.collectList().block()));
            return jasperReport("ReportAddress.jasper", "Direcciones", parameters);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResponseEntity<Resource> exportFuncionary(int idFuncionary, String authToken) {

        Flux<reportFuncionary> reportFuncionary= webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/api/transaccionalFuncionary/listIdTutor/"+ idFuncionary)
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve()
                .bodyToFlux(DataTeenFuncionaryTransaccional.class)
                .map(data ->{
                    reportFuncionary reportView = new reportFuncionary();
                    String funcionary = data.getFuncionaryResponseDto().getName() +" "+ data.getFuncionaryResponseDto().getSurnamefather() +" "+data.getFuncionaryResponseDto().getSurnamemother();
                    reportView.setFuncionary(funcionary);
                    reportView.setDNIfunci(data.getFuncionaryResponseDto().getDni());
                    reportView.setPhonenumberfunci(data.getFuncionaryResponseDto().getPhonenumber());
                    reportView.setRange(data.getFuncionaryResponseDto().getRange());
                    reportView.setEmailfunci(data.getFuncionaryResponseDto().getEmail());
                    String adolescent = data.getTeenResponseDto().getName() +" "+data.getTeenResponseDto().getSurnamefather() + " "+data.getTeenResponseDto().getSurnamemother();
                    reportView.setAdolescent(adolescent);
                    reportView.setDNIado(data.getTeenResponseDto().getDni());
                    return reportView;
                });


        try {
            final HashMap<String, Object> parameters = new HashMap<>();

            reportFuncionary.subscribe(funcionary ->{
                parameters.put("funcionary", funcionary.getFuncionary());
                parameters.put("celular", funcionary.getPhonenumberfunci());
                parameters.put("DNIfuncionary", funcionary.getDNIfunci());
                parameters.put("range", funcionary.getRange());
                parameters.put("email", funcionary.getEmailfunci());
            });
            parameters.put("dsFuncionaryTeen", new JRBeanCollectionDataSource(reportFuncionary.collectList().block()));
            return jasperReport("reportFuncionary.jasper", "funcionario", parameters);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public ResponseEntity<Resource> exportAttendance(int idActivity, String authToken) {

        Flux<attendanceViewDto> attendance = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/v1/attendance/view/"+ idActivity)
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve()
                .bodyToFlux(attendanceViewDto.class)
                .map(asistencia ->{
                    if(asistencia.getAsistencia().equals("A")){
                        asistencia.setAsistencia("Asistio");
                    } else if (asistencia.getAsistencia().equals("T")){
                        asistencia.setAsistencia("Tardanza");
                    } else {
                        asistencia.setAsistencia("Falto");
                    }
                    return asistencia;
                });


        Mono<Activities> activity = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/ms-soa/"+ idActivity)
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve()
                .bodyToMono(Activities.class);

        try {
            final HashMap<String, Object> parameters = new HashMap<>();
            activity.subscribe(activities -> parameters.put("actividad", activities.getName()));
            parameters.put("dsAttendance", new JRBeanCollectionDataSource(attendance.collectList().block()));
            return jasperReport("ReportAttendance.jasper", "Asistencia", parameters);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }


    public ResponseEntity<Resource> jasperReport(String reportPath, String outputFileName, HashMap<String, Object> parameters) throws JRException {

        try {
            final File file = ResourceUtils.getFile("classpath:"+reportPath);
            final File imgLogo = ResourceUtils.getFile("classpath:images/LogoSOA.png");
            final JasperReport report = (JasperReport) JRLoader.loadObject(file);
            parameters.put("logo", new FileInputStream(imgLogo));

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);

            StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(stringBuilder.append(outputFileName).toString())
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(contentDisposition);
            return ResponseEntity.ok().contentLength((long) reporte.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .headers(headers).body(new ByteArrayResource(reporte));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

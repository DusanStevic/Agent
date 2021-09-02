package com.agent.report.controller;

import com.agent.report.model.dto.ReportResponseDTO;
import com.agent.report.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "hello")
    public ResponseEntity<?> get() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return new ResponseEntity<>(String.format("Hello from report service with ip address %s!", ip), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportResponseDTO> report() {
        ReportResponseDTO reportResponseDTO = reportService.report();
        return new ResponseEntity<>(reportResponseDTO, HttpStatus.OK);
    }
}

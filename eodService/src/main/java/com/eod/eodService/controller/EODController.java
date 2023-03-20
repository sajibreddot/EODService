package com.eod.eodService.controller;

import com.eod.eodService.data.IndividualCalculatedBalanceData;
import com.eod.eodService.service.MfsLedgerAccountEodService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1" + "/EODService")
public class EODController {
    @Autowired
    MfsLedgerAccountEodService MfsLedgerAccountEodService;

    @GetMapping("/bulkLedgerAccountEodUpdate")
    public String bulkLedgerAccountEodUpdate() {
        MfsLedgerAccountEodService.bulkLedgerAccountEodUpdate();
        return "Service is running";


    }

    @GetMapping("/individualSystemAccountEODReport")
    public List<IndividualCalculatedBalanceData> individualSystemAccountEOD() {
        List<IndividualCalculatedBalanceData> data = MfsLedgerAccountEodService.individualSystemAccountEOD();
        return data;
    }

}

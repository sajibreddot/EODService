package com.eod.eodService.service;

import com.eod.eodService.data.IndividualCalculatedBalanceData;

import java.util.List;

public interface MfsLedgerAccountEodService {
    Boolean bulkLedgerAccountEodUpdate();

    List<IndividualCalculatedBalanceData> individualSystemAccountEOD();
}

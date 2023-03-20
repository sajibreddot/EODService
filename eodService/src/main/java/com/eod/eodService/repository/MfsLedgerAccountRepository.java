package com.eod.eodService.repository;

import com.eod.eodService.data.BulkEodCalculatedBalanceData;
import com.eod.eodService.data.IndividualCalculatedBalanceData;
import com.eod.eodService.data.LedgerAccountData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MfsLedgerAccountRepository {

    List<LedgerAccountData> findAllMfsLedgerAccount();

    BigDecimal getBalance(Long ledgerSystemAccount);

    Boolean insertClosedAccountBalance(BulkEodCalculatedBalanceData eodDataObject);

    List<IndividualCalculatedBalanceData> individualSystemAccoundEod(int accountId, LocalDate eodDate);
}

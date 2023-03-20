package com.eod.eodService.service;

import com.eod.eodService.data.BulkEodCalculatedBalanceData;
import com.eod.eodService.data.IndividualCalculatedBalanceData;
import com.eod.eodService.data.LedgerAccountData;
import com.eod.eodService.exception.CommonException;
import com.eod.eodService.repository.MfsLedgerAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.profiler.Profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class MfsLedgerAccountEodServiceImpl implements MfsLedgerAccountEodService {

    @Autowired
    MfsLedgerAccountRepository mfsLedgerAccountRepository;

    @Value("${system.account.id}")
    private int systemAccountId;

    @Value("${eod.date}")
    private String eodDate;

    @Override
    @Scheduled(cron = "${app.scheduler.eod.process.cron}")
    public Boolean bulkLedgerAccountEodUpdate() {
        try {
            Profiler profiler = new Profiler("ledgerAccountEodUpdate-PROCESS");
            profiler.start("START-FETCH-ACCOUNT-LIST");
            List<LedgerAccountData> accountList = mfsLedgerAccountRepository.findAllMfsLedgerAccount();
            if (accountList == null) {
                throw new CommonException("Sorry, No system account data found", HttpStatus.BAD_REQUEST);
            }
            BulkEodCalculatedBalanceData eodDataObject = new BulkEodCalculatedBalanceData();
            for (LedgerAccountData ledgerAccount : accountList) {

                Long ledgerSystemAccount = ledgerAccount.getAccountId();
                profiler.start("START-FETCH-INDIVIDUAL-SYSTEM-ACCOUNT-BALANCE");
                BigDecimal systemAccountBalance = mfsLedgerAccountRepository.getBalance(ledgerSystemAccount);

                //System.out.println(systemAccountBalance);

                eodDataObject.setBalance(systemAccountBalance);
                eodDataObject.setAccountID(ledgerSystemAccount.intValue());
                eodDataObject.setEodDate(LocalDate.now());

                //System.out.println(eodDataObject.getEodDate());
                profiler.start("START-UPDATE-CLOSED-ACCOUNT-BALANCE-TABLE");
                Boolean isProcessed = mfsLedgerAccountRepository.insertClosedAccountBalance(eodDataObject);
                log.info("Processed system account id {}", ledgerSystemAccount);


            }
            profiler.start("FINISH");
            profiler.stop().print();
            return true;

        } catch (Exception ex) {
            log.info("eodBalanceCalculationAndInsertion:{}", ex.getMessage());
            System.out.println(ex.getMessage());
            throw new CommonException("Something went wrong, please try later.", HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public List<IndividualCalculatedBalanceData> individualSystemAccountEOD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(eodDate, formatter);
        List<IndividualCalculatedBalanceData> data = mfsLedgerAccountRepository.individualSystemAccoundEod(systemAccountId, date);
        return data;
    }
}

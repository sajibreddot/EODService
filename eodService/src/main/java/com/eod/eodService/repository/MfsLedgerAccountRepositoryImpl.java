package com.eod.eodService.repository;

import com.eod.eodService.data.BulkEodCalculatedBalanceData;
import com.eod.eodService.data.IndividualCalculatedBalanceData;
import com.eod.eodService.data.LedgerAccountData;
import com.eod.eodService.mapper.IndividualSystemAccountMapper;
import com.eod.eodService.mapper.LedgerAccountDataMapper;
import com.eod.eodService.utility.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
public class MfsLedgerAccountRepositoryImpl implements MfsLedgerAccountRepository {
    @Autowired
    JdbcTemplate mfsCoreJdbcTemplate;

    //if we need near future
    @Autowired
    JdbcTemplate backOfficeJdbcTemplate;

    //if we need near future for local test
    @Autowired
    JdbcTemplate myLocalJdbcTemplate;

    @Override
    public List<LedgerAccountData> findAllMfsLedgerAccount() {
        try {
            List<LedgerAccountData> data = mfsCoreJdbcTemplate.query(Query.findAllLedgerQuery, new LedgerAccountDataMapper());
            return data;
        } catch (Exception ex) {
            log.info("findAllMfsLedgerAccount:{}", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public BigDecimal getBalance(Long ledgerSystemAccount) {
        try {
            BigDecimal systemAccountBalance = mfsCoreJdbcTemplate.queryForObject(Query.getSystemAccountBalanceQuery,
                    new Object[]{ledgerSystemAccount, ledgerSystemAccount, ledgerSystemAccount, ledgerSystemAccount}, BigDecimal.class);
            System.out.println(systemAccountBalance);
            return systemAccountBalance;
        } catch (Exception ex) {
            log.info("getBalance:{}", ex.getMessage());
            log.info("Balance null for AccountId:{}", ledgerSystemAccount);
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Boolean insertClosedAccountBalance(BulkEodCalculatedBalanceData eodDataObject) {
        try {
            mfsCoreJdbcTemplate.update(Query.insertClosedAccountBalanceQuery, eodDataObject.getAccountID(), eodDataObject.getEodDate(), eodDataObject.getBalance());
            return true;
        } catch (Exception ex) {
            log.info("insertClosedAccountBalance:{}", ex.getMessage());
            log.info("Balance null for AccountId:{}", eodDataObject.getAccountID());
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<IndividualCalculatedBalanceData> individualSystemAccoundEod(int accountId, LocalDate eodDate) {
        try {
            List<IndividualCalculatedBalanceData> data = mfsCoreJdbcTemplate.query(Query.individualSystemClosedAccountBalanceQuery, new Object[]{accountId, eodDate}, new IndividualSystemAccountMapper());
            return data;
        } catch (Exception ex) {
            log.info("individualSystemAccoundEod:{}", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }
}

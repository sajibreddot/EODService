package com.eod.eodService.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IndividualCalculatedBalanceData {
    public Integer account_id;
    public BigDecimal balance;
    public LocalDate date;
}

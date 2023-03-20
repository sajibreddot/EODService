package com.eod.eodService.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BulkEodCalculatedBalanceData {
    public Integer AccountID;
    public BigDecimal Balance;
    public LocalDate EodDate;
}

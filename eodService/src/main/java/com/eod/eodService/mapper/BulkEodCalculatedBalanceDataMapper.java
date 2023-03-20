package com.eod.eodService.mapper;

import com.eod.eodService.data.BulkEodCalculatedBalanceData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BulkEodCalculatedBalanceDataMapper implements RowMapper<BulkEodCalculatedBalanceData> {
    @Override
    public BulkEodCalculatedBalanceData mapRow(ResultSet rs, int rowNum) throws SQLException {
        BulkEodCalculatedBalanceData data = new BulkEodCalculatedBalanceData();
        data.setBalance(rs.getBigDecimal("Balance"));
        data.setEodDate(LocalDate.now());
        return data;

    }
}
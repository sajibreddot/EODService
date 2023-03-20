package com.eod.eodService.mapper;

import com.eod.eodService.data.IndividualCalculatedBalanceData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndividualSystemAccountMapper implements RowMapper<IndividualCalculatedBalanceData> {
    @Override
    public IndividualCalculatedBalanceData mapRow(ResultSet rs, int rowNum) throws SQLException {
        IndividualCalculatedBalanceData eodData = new IndividualCalculatedBalanceData();
        eodData.setAccount_id(rs.getInt("account_id"));
        eodData.setDate(rs.getDate("date").toLocalDate());
        eodData.setBalance(rs.getBigDecimal("balance"));
        return eodData;

    }
}
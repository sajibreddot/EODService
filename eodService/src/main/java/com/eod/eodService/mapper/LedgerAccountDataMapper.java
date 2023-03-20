package com.eod.eodService.mapper;

import com.eod.eodService.data.LedgerAccountData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LedgerAccountDataMapper implements RowMapper<LedgerAccountData> {
    @Override
    public LedgerAccountData mapRow(ResultSet rs, int rowNum) throws SQLException {
        LedgerAccountData data = new LedgerAccountData();
        data.setAccountId(rs.getLong("account_id"));
        return data;
    }
}
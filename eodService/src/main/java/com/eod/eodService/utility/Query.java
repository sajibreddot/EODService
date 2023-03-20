package com.eod.eodService.utility;

public class Query {
    public static final String findAllLedgerQuery = "SELECT account_id  FROM mfs_ledger_accounts mla";
    public static final String getSystemAccountBalanceQuery = "SELECT sum(\n" +
            "    case when t.chargeback_of_id is null then\n" +
            "        case when t.from_account_id = ? then -t.amount else t.amount end\n" +
            "    else\n" +
            "        case when t.to_account_id = ? then t.amount else -t.amount end\n" +
            "    end) AS Balance\n" +
            "FROM transfers t\n" +
            "where (t.from_account_id = ? OR t.to_account_id = ?) AND t.process_date is not null AND t.process_date <=DATE_FORMAT(cast(date(current_timestamp) as datetime),\"%Y-%m-%d 00:00:00\")\n" +
            "\n";
    public static final String insertClosedAccountBalanceQuery = "INSERT INTO closed_account_balances(account_id,date,balance) values(?,?,?)";
    public static final String individualSystemClosedAccountBalanceQuery = "select account_id, balance, date from closed_account_balances cab WHERE cab.account_id  =? AND cab.date =?";

}

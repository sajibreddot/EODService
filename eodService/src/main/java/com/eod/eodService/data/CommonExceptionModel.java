package com.eod.eodService.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonExceptionModel {
    int statusCode;
    String message;
}
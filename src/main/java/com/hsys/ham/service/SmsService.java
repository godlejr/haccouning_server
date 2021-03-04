package com.hsys.ham.service;

import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dto.SmsTransferDto;

public interface SmsService {


	CardHistoryDao preprocessAndSaveAndGetCardHistoryDao(SmsTransferDto smsTransferDto) throws Exception;

}

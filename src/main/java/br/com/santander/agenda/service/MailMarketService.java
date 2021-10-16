package br.com.santander.agenda.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.kafka.support.SendResult;

public interface MailMarketService {
  public SendResult<String, Object> sendMailToTopic(String contactEmail)
    throws InterruptedException, ExecutionException, TimeoutException;
}

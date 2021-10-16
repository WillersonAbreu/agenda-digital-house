package br.com.santander.agenda.service.impl;

import br.com.santander.agenda.service.MailMarketService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class MailMarketServiceImpl implements MailMarketService {
  private KafkaTemplate<String, Object> kafkaTemplate;

  public MailMarketServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public SendResult<String, Object> sendMailToTopic(String contactEmail)
    throws InterruptedException, ExecutionException, TimeoutException {
    String value = "{\"contactEmail\": \"" + contactEmail + "\"}";
    SendResult<String, Object> result =
      this.kafkaTemplate.send("mail_market", value).get(500, TimeUnit.SECONDS);

    return result;
  }
}

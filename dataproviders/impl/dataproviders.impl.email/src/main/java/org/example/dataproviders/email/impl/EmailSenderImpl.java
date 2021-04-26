package org.example.dataproviders.email.impl;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.dataproviders.email.EmailMessage;
import org.example.dataproviders.email.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class EmailSenderImpl implements EmailSender {

  private final JavaMailSender mailSender;
  @Value("${app.email.from}")
  private String fromEmailAddress;

  @Override
  @SneakyThrows
  public CompletableFuture<Void> send(EmailMessage messageData) {
    return CompletableFuture.runAsync(() -> sendSync(messageData));
  }


  @SneakyThrows
  private void sendSync(EmailMessage message) {
    mailSender.send(m -> prepareMessage(m, message));
  }

  @SneakyThrows
  private void prepareMessage(MimeMessage mimeMessage, EmailMessage messageData) {
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setSubject(messageData.getSubject());
    helper.setText(messageData.getText());
    helper.setFrom(fromEmailAddress);
    helper.setTo(messageData.getTo().toArray(new String[0]));
    Map<String, byte[]> attachments = messageData.getAttachments();
    if (attachments != null) {
      for (Entry<String, byte[]> entry : attachments.entrySet()) {
        helper.addAttachment(entry.getKey(), () -> new ByteArrayInputStream(entry.getValue()));
      }
    }
  }
}

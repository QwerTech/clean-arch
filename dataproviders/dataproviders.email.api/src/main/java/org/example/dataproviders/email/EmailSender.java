package org.example.dataproviders.email;

import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;

public interface EmailSender {


  @SneakyThrows
  CompletableFuture<Void> send(EmailMessage messageData);
}

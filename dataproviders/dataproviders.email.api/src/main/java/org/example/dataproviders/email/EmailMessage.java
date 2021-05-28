package org.example.dataproviders.email;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {

  private String subject;
  private String text;
  @NonNull
  private List<String> to;
  private Map<String, byte[]> attachments;
}

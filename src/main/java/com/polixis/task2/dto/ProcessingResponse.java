package com.polixis.task2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingResponse {

  private LocalDateTime processedAt;
  private Integer filesCount;
  private Integer uniqueIPv4Count;
}

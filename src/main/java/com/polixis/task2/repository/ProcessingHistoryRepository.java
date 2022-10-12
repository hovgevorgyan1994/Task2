package com.polixis.task2.repository;

import com.polixis.task2.entity.ProcessingHistory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
public interface ProcessingHistoryRepository extends JpaRepository<ProcessingHistory, UUID> {

  boolean existsByFileName(String fileName);
}

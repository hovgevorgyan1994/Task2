package com.polixis.task2.api.controller;

import com.polixis.task2.api.AddressFilesApi;
import com.polixis.task2.dto.ProcessingResponse;
import com.polixis.task2.service.AddressFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/address-files")
public class AddressFilesController implements AddressFilesApi {

  private final AddressFilesService addressFilesService;

  @Override
  @PostMapping("/count-unique")
  public ResponseEntity<ProcessingResponse> countUniqueAddresses() {
    return ResponseEntity.ok(addressFilesService.countUniqueAddresses());
  }
}

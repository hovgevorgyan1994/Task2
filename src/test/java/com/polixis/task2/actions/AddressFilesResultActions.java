package com.polixis.task2.actions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.polixis.task2.endpoints.AddressFilesControllerUris;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@TestConfiguration
@RequiredArgsConstructor
public class AddressFilesResultActions {

  private final MockMvc mockMvc;
  private final AddressFilesControllerUris addressFilesControllerUris;

  public ResultActions countUniqueAddresses() throws Exception {
    return mockMvc.perform(post(addressFilesControllerUris.getCountUniqueAddressesUri()));
  }
}

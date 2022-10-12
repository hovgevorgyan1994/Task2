package com.polixis.task2.endpoints;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Setter
@TestComponent
@ConfigurationProperties(prefix = "config.uris.address-files")
public class AddressFilesControllerUris {

  private String baseUri;
  private String countUniqueAddressesUri;

  public String getCountUniqueAddressesUri() {
    return baseUri + countUniqueAddressesUri;
  }
}

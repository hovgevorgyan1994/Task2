package com.polixis.task2.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.polixis.task2.actions.AddressFilesResultActions;
import com.polixis.task2.endpoints.AddressFilesControllerUris;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import({AddressFilesResultActions.class, AddressFilesControllerUris.class})
class AddressFilesControllerTest {

  @Autowired private AddressFilesResultActions addressFilesResultActions;

  @Test
  void countUniqueAddressesSuccess() throws Exception {
    addressFilesResultActions
        .countUniqueAddresses()
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.filesCount").value(2));
  }
}

package com.polixis.task2.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.polixis.task2.dto.ProcessingResponse;
import com.polixis.task2.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */

@Tag(
    name = "AddressFiles",
    description = "Endpoints for counting unique IPv4 addresses in given files")
public interface AddressFilesApi {

  @Operation(
      summary = "Count unique IPv4 addresses in new files",
      description = """
                    Possible error codes: 4041
                    """)
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "New files have been processed",
              content = @Content(schema = @Schema(implementation = ProcessingResponse.class),mediaType = APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "404",
              description =
                  "Directory not found",
              content =
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiError.class)))
      })
  ResponseEntity<ProcessingResponse> countUniqueAddresses();
}

/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.11.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package pm.little.api.controllers;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pm.little.api.models.DayBlueprint;
import java.util.UUID;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
@Validated
@Tag(name = "days", description = "APIs for managing day templates and their instances.")
public interface DaysApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /days/{day_blueprint_uuid} : Delete day template (admin only)
     *
     * @param dayBlueprintUuid The UUID of the day blueprint (required)
     * @return No Content (status code 204)
     */
    @Operation(
        operationId = "daysDayBlueprintUuidDelete",
        summary = "Delete day template (admin only)",
        tags = { "days" },
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content")
        },
        security = {
            @SecurityRequirement(name = "admin_jwt")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/days/{day_blueprint_uuid}"
    )
    
    default ResponseEntity<Void> daysDayBlueprintUuidDelete(
        @Parameter(name = "day_blueprint_uuid", description = "The UUID of the day blueprint", required = true, in = ParameterIn.PATH) @PathVariable("day_blueprint_uuid") UUID dayBlueprintUuid
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /days/{day_blueprint_uuid} : Get day template details
     *
     * @param dayBlueprintUuid The UUID of the day blueprint (required)
     * @return Day blueprint (status code 200)
     */
    @Operation(
        operationId = "daysDayBlueprintUuidGet",
        summary = "Get day template details",
        tags = { "days" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Day blueprint", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DayBlueprint.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/days/{day_blueprint_uuid}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<DayBlueprint> daysDayBlueprintUuidGet(
        @Parameter(name = "day_blueprint_uuid", description = "The UUID of the day blueprint", required = true, in = ParameterIn.PATH) @PathVariable("day_blueprint_uuid") UUID dayBlueprintUuid
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"day_blueprint_uuid\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"description\", \"text\" : \"text\", \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /days/{day_blueprint_uuid} : Update day template (admin only)
     *
     * @param dayBlueprintUuid The UUID of the day blueprint (required)
     * @param dayBlueprint  (required)
     * @return Updated day blueprint (status code 200)
     */
    @Operation(
        operationId = "daysDayBlueprintUuidPut",
        summary = "Update day template (admin only)",
        tags = { "days" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Updated day blueprint", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DayBlueprint.class))
            })
        },
        security = {
            @SecurityRequirement(name = "admin_jwt")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/days/{day_blueprint_uuid}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<DayBlueprint> daysDayBlueprintUuidPut(
        @Parameter(name = "day_blueprint_uuid", description = "The UUID of the day blueprint", required = true, in = ParameterIn.PATH) @PathVariable("day_blueprint_uuid") UUID dayBlueprintUuid,
        @Parameter(name = "DayBlueprint", description = "", required = true) @Valid @RequestBody DayBlueprint dayBlueprint
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"day_blueprint_uuid\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"description\", \"text\" : \"text\", \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /days : List day templates
     *
     * @param limit Limit of the list (required)
     * @param offset Offset of the list (required)
     * @return A list of day blueprints (status code 200)
     */
    @Operation(
        operationId = "daysGet",
        summary = "List day templates",
        tags = { "days" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of day blueprints", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DayBlueprint.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/days",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<DayBlueprint>> daysGet(
        @NotNull @Parameter(name = "limit", description = "Limit of the list", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = true) Integer limit,
        @NotNull @Parameter(name = "offset", description = "Offset of the list", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "offset", required = true) Integer offset
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"day_blueprint_uuid\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"description\", \"text\" : \"text\", \"title\" : \"title\" }, { \"day_blueprint_uuid\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"description\", \"text\" : \"text\", \"title\" : \"title\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /days : Create day template (admin only)
     *
     * @param dayBlueprint  (required)
     * @return Created day blueprint (status code 200)
     */
    @Operation(
        operationId = "daysPost",
        summary = "Create day template (admin only)",
        tags = { "days" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Created day blueprint", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DayBlueprint.class))
            })
        },
        security = {
            @SecurityRequirement(name = "admin_jwt")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/days",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<DayBlueprint> daysPost(
        @Parameter(name = "DayBlueprint", description = "", required = true) @Valid @RequestBody DayBlueprint dayBlueprint
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"day_blueprint_uuid\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"description\", \"text\" : \"text\", \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

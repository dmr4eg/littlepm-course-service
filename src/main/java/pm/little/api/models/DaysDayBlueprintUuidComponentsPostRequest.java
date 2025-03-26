package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import pm.little.api.models.enums.TypeEnum;


import java.util.*;


/**
 * DaysDayBlueprintUuidComponentsPostRequest
 */

@JsonTypeName("_days__day_blueprint_uuid__components_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T00:36:51.210059+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class DaysDayBlueprintUuidComponentsPostRequest {

  private @Nullable UUID dayComponentUuid;

  /**
   * Gets or Sets type
   */

  private @Nullable TypeEnum type;

  public DaysDayBlueprintUuidComponentsPostRequest dayComponentUuid(UUID dayComponentUuid) {
    this.dayComponentUuid = dayComponentUuid;
    return this;
  }

  /**
   * Get dayComponentUuid
   * @return dayComponentUuid
   */
  @Valid
  @Schema(name = "day_component_uuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("day_component_uuid")
  public UUID getDayComponentUuid() {
    return dayComponentUuid;
  }

  public void setDayComponentUuid(UUID dayComponentUuid) {
    this.dayComponentUuid = dayComponentUuid;
  }

  public DaysDayBlueprintUuidComponentsPostRequest type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DaysDayBlueprintUuidComponentsPostRequest daysDayBlueprintUuidComponentsPostRequest = (DaysDayBlueprintUuidComponentsPostRequest) o;
    return Objects.equals(this.dayComponentUuid, daysDayBlueprintUuidComponentsPostRequest.dayComponentUuid) &&
        Objects.equals(this.type, daysDayBlueprintUuidComponentsPostRequest.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayComponentUuid, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DaysDayBlueprintUuidComponentsPostRequest {\n");
    sb.append("    dayComponentUuid: ").append(toIndentedString(dayComponentUuid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


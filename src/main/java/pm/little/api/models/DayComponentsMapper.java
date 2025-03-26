package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * DayComponentsMapper
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T00:36:51.210059+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class DayComponentsMapper {

  private @Nullable UUID dayBlueprintUuid;

  private @Nullable UUID dayComponentUuid;

  /**
   * Gets or Sets type
   */


  private @Nullable TypeEnum type;

  public DayComponentsMapper dayBlueprintUuid(UUID dayBlueprintUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
    return this;
  }

  /**
   * Get dayBlueprintUuid
   * @return dayBlueprintUuid
   */
  @Valid
  @Schema(name = "day_blueprint_uuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("day_blueprint_uuid")
  public UUID getDayBlueprintUuid() {
    return dayBlueprintUuid;
  }

  public void setDayBlueprintUuid(UUID dayBlueprintUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
  }

  public DayComponentsMapper dayComponentUuid(UUID dayComponentUuid) {
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

  public DayComponentsMapper type(TypeEnum type) {
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
    DayComponentsMapper dayComponentsMapper = (DayComponentsMapper) o;
    return Objects.equals(this.dayBlueprintUuid, dayComponentsMapper.dayBlueprintUuid) &&
        Objects.equals(this.dayComponentUuid, dayComponentsMapper.dayComponentUuid) &&
        Objects.equals(this.type, dayComponentsMapper.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayBlueprintUuid, dayComponentUuid, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayComponentsMapper {\n");
    sb.append("    dayBlueprintUuid: ").append(toIndentedString(dayBlueprintUuid)).append("\n");
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


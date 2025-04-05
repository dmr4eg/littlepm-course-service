package pm.little.api.models.ids;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Composite ID for DayComponentsMapper
 */

@Embeddable
@Schema(name = "DayComponentsMapperId", description = "Composite ID for DayComponentsMapper")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class DayComponentsMapperId {

  private UUID dayBlueprintUuid;

  private UUID componentUuid;

  public DayComponentsMapperId() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DayComponentsMapperId(UUID dayBlueprintUuid, UUID componentUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
    this.componentUuid = componentUuid;
  }

  public DayComponentsMapperId dayBlueprintUuid(UUID dayBlueprintUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
    return this;
  }

  /**
   * UUID for a Day Blueprint
   * @return dayBlueprintUuid
   */
  @NotNull
  @Valid
  @Schema(name = "day_blueprint_uuid", description = "UUID for a Day Blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("day_blueprint_uuid")
  public UUID getDayBlueprintUuid() {
    return dayBlueprintUuid;
  }

  public void setDayBlueprintUuid(UUID dayBlueprintUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
  }

  public DayComponentsMapperId componentUuid(UUID componentUuid) {
    this.componentUuid = componentUuid;
    return this;
  }

  /**
   * UUID for a Day Component
   * @return componentUuid
   */
  @NotNull @Valid 
  @Schema(name = "day_component_uuid", description = "UUID for a Day Component", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("day_component_uuid")
  public UUID getComponentUuid() {
    return componentUuid;
  }

  public void setComponentUuid(UUID componentUuid) {
    this.componentUuid = componentUuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayComponentsMapperId dayComponentsMapperId = (DayComponentsMapperId) o;
    return Objects.equals(this.dayBlueprintUuid, dayComponentsMapperId.dayBlueprintUuid) &&
        Objects.equals(this.componentUuid, dayComponentsMapperId.componentUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayBlueprintUuid, componentUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayComponentsMapperId {\n");
    sb.append("    dayBlueprintUuid: ").append(toIndentedString(dayBlueprintUuid)).append("\n");
    sb.append("    componentUuid: ").append(toIndentedString(componentUuid)).append("\n");
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


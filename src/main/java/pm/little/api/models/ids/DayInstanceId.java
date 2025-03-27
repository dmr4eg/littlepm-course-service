package pm.little.api.models.ids;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Composite ID for DayInstance
 */

@Embeddable
@Schema(name = "DayInstanceId", description = "Composite ID for DayInstance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class DayInstanceId implements Serializable {

  private UUID dayBlueprintUuid;

  private UUID userUuid;

  public DayInstanceId() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DayInstanceId(UUID dayBlueprintUuid, UUID userUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
    this.userUuid = userUuid;
  }

  public DayInstanceId dayBlueprintUuid(UUID dayBlueprintUuid) {
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

  public DayInstanceId userUuid(UUID userUuid) {
    this.userUuid = userUuid;
    return this;
  }

  /**
   * UUID for a User
   * @return userUuid
   */
  @NotNull @Valid 
  @Schema(name = "user_uuid", description = "UUID for a User", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("user_uuid")
  public UUID getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(UUID userUuid) {
    this.userUuid = userUuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayInstanceId dayInstanceId = (DayInstanceId) o;
    return Objects.equals(this.dayBlueprintUuid, dayInstanceId.dayBlueprintUuid) &&
        Objects.equals(this.userUuid, dayInstanceId.userUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayBlueprintUuid, userUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayInstanceId {\n");
    sb.append("    dayBlueprintUuid: ").append(toIndentedString(dayBlueprintUuid)).append("\n");
    sb.append("    userUuid: ").append(toIndentedString(userUuid)).append("\n");
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


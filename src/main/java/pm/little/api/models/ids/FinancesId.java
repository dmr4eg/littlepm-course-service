package pm.little.api.models.ids;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Composite ID for Finances
 */
@Embeddable
@Schema(name = "FinancesId", description = "Composite ID for Finances")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class FinancesId {

  private UUID projectBlueprintUuid;

  private UUID userUuid;

  public FinancesId() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FinancesId(UUID projectBlueprintUuid, UUID userUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
    this.userUuid = userUuid;
  }

  public FinancesId projectBlueprintUuid(UUID projectBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
    return this;
  }

  /**
   * UUID for a Project Blueprint
   * @return projectBlueprintUuid
   */
  @NotNull @Valid 
  @Schema(name = "project_blueprint_uuid", description = "UUID for a Project Blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("project_blueprint_uuid")
  public UUID getProjectBlueprintUuid() {
    return projectBlueprintUuid;
  }

  public void setProjectBlueprintUuid(UUID projectBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
  }

  public FinancesId userUuid(UUID userUuid) {
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
    FinancesId financesId = (FinancesId) o;
    return Objects.equals(this.projectBlueprintUuid, financesId.projectBlueprintUuid) &&
        Objects.equals(this.userUuid, financesId.userUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprintUuid, userUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FinancesId {\n");
    sb.append("    projectBlueprintUuid: ").append(toIndentedString(projectBlueprintUuid)).append("\n");
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


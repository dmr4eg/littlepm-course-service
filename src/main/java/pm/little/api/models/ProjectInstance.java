package pm.little.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import pm.little.api.models.ids.ProjectInstanceId;

import io.swagger.v3.oas.annotations.media.Schema;
import pm.little.api.models.enums.StatusEnum;


/**
 * Entity for a user&#39;s specific project instance
 */

@Entity
@Schema(name = "ProjectInstance", description = "Entity for a user's specific project instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class ProjectInstance {
  @EmbeddedId
  @NotNull
  private ProjectInstanceId id;

  /**
   * Gets or Sets status
   */

  private StatusEnum status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime endDate;

  private @Nullable String feedback;

  private @Nullable String whatWentWell;

  public ProjectInstance() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectInstance(ProjectInstanceId id, StatusEnum status) {
    this.id = id;
    this.status = status;
  }

  public ProjectInstance id(ProjectInstanceId id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public ProjectInstanceId getId() {
    return id;
  }

  public void setId(ProjectInstanceId id) {
    this.id = id;
  }

  public ProjectInstance status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @NotNull 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ProjectInstance startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   */
  @Valid 
  @Schema(name = "start_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("start_date")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public ProjectInstance endDate(OffsetDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
   */
  @Valid 
  @Schema(name = "end_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("end_date")
  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  public ProjectInstance feedback(String feedback) {
    this.feedback = feedback;
    return this;
  }

  /**
   * Get feedback
   * @return feedback
   */
  
  @Schema(name = "feedback", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feedback")
  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public ProjectInstance whatWentWell(String whatWentWell) {
    this.whatWentWell = whatWentWell;
    return this;
  }

  /**
   * Get whatWentWell
   * @return whatWentWell
   */
  
  @Schema(name = "what_went_well", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("what_went_well")
  public String getWhatWentWell() {
    return whatWentWell;
  }

  public void setWhatWentWell(String whatWentWell) {
    this.whatWentWell = whatWentWell;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectInstance projectInstance = (ProjectInstance) o;
    return Objects.equals(this.id, projectInstance.id) &&
        Objects.equals(this.status, projectInstance.status) &&
        Objects.equals(this.startDate, projectInstance.startDate) &&
        Objects.equals(this.endDate, projectInstance.endDate) &&
        Objects.equals(this.feedback, projectInstance.feedback) &&
        Objects.equals(this.whatWentWell, projectInstance.whatWentWell);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, startDate, endDate, feedback, whatWentWell);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectInstance {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    feedback: ").append(toIndentedString(feedback)).append("\n");
    sb.append("    whatWentWell: ").append(toIndentedString(whatWentWell)).append("\n");
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


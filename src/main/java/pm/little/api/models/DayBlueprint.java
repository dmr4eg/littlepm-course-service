package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * DayBlueprint
 */

@Entity
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class DayBlueprint {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID dayBlueprintUuid;

  private @NotNull String title;

  private @Nullable String description;

  private @NotNull String text;

  public DayBlueprint() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  @PersistenceConstructor
  public DayBlueprint(UUID dayBlueprintUuid, String title) {
    this.dayBlueprintUuid = dayBlueprintUuid;
    this.title = title;
  }

  public DayBlueprint dayBlueprintUuid(UUID dayBlueprintUuid) {
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

  public DayBlueprint title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public DayBlueprint description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DayBlueprint text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
   */
  
  @Schema(name = "text", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayBlueprint dayBlueprint = (DayBlueprint) o;
    return Objects.equals(this.dayBlueprintUuid, dayBlueprint.dayBlueprintUuid) &&
        Objects.equals(this.title, dayBlueprint.title) &&
        Objects.equals(this.description, dayBlueprint.description) &&
        Objects.equals(this.text, dayBlueprint.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayBlueprintUuid, title, description, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayBlueprint {\n");
    sb.append("    dayBlueprintUuid: ").append(toIndentedString(dayBlueprintUuid)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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


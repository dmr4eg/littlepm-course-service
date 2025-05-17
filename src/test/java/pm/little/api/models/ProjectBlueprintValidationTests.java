package pm.little.api.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StyleEnum;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectBlueprintValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateValidProjectBlueprint() {
        // Create a valid project blueprint
        ProjectBlueprint projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .title("Valid Project")
                .description("A valid project description")
                .posterUrl("http://example.com/poster.jpg")
                .welcomeVideoUrl("http://example.com/welcome.mp4")
                .difficulty(DifficultyEnum.MEDIUM)
                .style(StyleEnum.DIY);

        // Validate it
        Set<ConstraintViolation<ProjectBlueprint>> violations = validator.validate(projectBlueprint);
        
        // Should have no violations
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldNotValidateProjectBlueprintWithoutUuid() {
        // Create a project blueprint without UUID
        ProjectBlueprint projectBlueprint = new ProjectBlueprint()
                .title("Project Without UUID")
                .description("A project description")
                .posterUrl("http://example.com/poster.jpg")
                .welcomeVideoUrl("http://example.com/welcome.mp4");

        // Validate it
        Set<ConstraintViolation<ProjectBlueprint>> violations = validator.validate(projectBlueprint);
        
        // Should have violations
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Object::toString))
                .contains("projectBlueprintUuid");
    }

    @Test
    void shouldNotValidateProjectBlueprintWithoutTitle() {
        // Create a project blueprint without title
        ProjectBlueprint projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .description("A project description without title")
                .posterUrl("http://example.com/poster.jpg")
                .welcomeVideoUrl("http://example.com/welcome.mp4");

        // Validate it
        Set<ConstraintViolation<ProjectBlueprint>> violations = validator.validate(projectBlueprint);
        
        // Should have violations
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Object::toString))
                .contains("title");
    }

    @Test
    void shouldNotValidateProjectBlueprintWithoutRequiredFields() {
        // Create a project blueprint with only UUID and title (missing other required fields)
        ProjectBlueprint projectBlueprint = new ProjectBlueprint()
                .projectBlueprintUuid(UUID.randomUUID())
                .title("Minimal Project");

        // Validate it
        Set<ConstraintViolation<ProjectBlueprint>> violations = validator.validate(projectBlueprint);
        
        // Should have violations for the missing required fields
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Object::toString))
                .contains("posterUrl", "welcomeVideoUrl", "description");
    }
} 
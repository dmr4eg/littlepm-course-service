package pm.little.api.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DifficultyEnum {
    EASY("EASY"),

    MEDIUM("MEDIUM"),

    HARD("HARD");

    private String value;

    DifficultyEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static DifficultyEnum fromValue(String value) {
        for (DifficultyEnum b : DifficultyEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
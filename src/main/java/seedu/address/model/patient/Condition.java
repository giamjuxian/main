package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Condition of a inside MedicalHistory of a Patient
 * Guarantees:
 */
public class Condition {

    public static final String MESSAGE_CONDITION_CONSTRAINTS =
            "Condition should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String CONDITION_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String condition;

    /**
     * Constructs a {@code Condition}.
     *
     * @param condition A valid condition.
     */
    public Condition(String condition) {
        requireNonNull(condition);
        checkArgument(isValidCondition(condition), CONDITION_VALIDATION_REGEX);
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    /**
     * Returns true if a given string is a valid condition.
     */
    public static boolean isValidCondition(String test) {
        return test.matches(CONDITION_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return condition;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Condition // instanceof handles nulls
                && condition.toLowerCase().equals(((Condition) other).condition.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }

}

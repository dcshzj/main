package seedu.scheduler.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.scheduler.commons.util.AppUtil.checkArgument;

/**
 * Represents an {@code Interviewee}'s choice of department in the scheduler.
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS =
            "Incorrect department format! Departments can take any values, but should not be blank.\n"
            + "Examples:\n"
            + "Correct: Logistics\n"
            + "Incorrect: ' ' (blank input)";
    public static final String VALIDATION_REGEX = "[^\\s].*"; // " " will be wrong
    public final String department;

    /**
     * Constructs a {@code Department}
     *
     * @param department a valid department.
     */
    public Department(String department) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department), MESSAGE_CONSTRAINTS);
        this.department = department;
    }

    /**
     * Returns true if the given string is a valid department.
     */
    public static boolean isValidDepartment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return department;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Department // instanceof handles nulls
                && department.equalsIgnoreCase(((Department) other).department)); // state check
    }

    @Override
    public int hashCode() {
        return department.hashCode();
    }

}

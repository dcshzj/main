package seedu.scheduler.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.logic.parser.ParserUtil;
import seedu.scheduler.logic.parser.exceptions.ParseException;
import seedu.scheduler.model.person.Department;
import seedu.scheduler.model.person.Email;
import seedu.scheduler.model.person.Emails;
import seedu.scheduler.model.person.Faculty;
import seedu.scheduler.model.person.Interviewee;
import seedu.scheduler.model.person.Person;
import seedu.scheduler.model.person.Slot;

/**
 * A utility class to help with building Interviewee objects from string input.
 */
public class IntervieweeBuilder extends PersonBuilder {

    private static final String DEFAULT_FACULTY = "School of Computing";
    private static final String DEFAULT_YEAR_OF_STUDY = "2019";

    private Faculty faculty;
    private Integer yearOfStudy;
    private List<Department> departmentChoices;
    private List<Slot> allocatedTimeslots;
    private Emails emails;

    /**
     * Partially initializes the IntervieweeBuilder with {@code p}'s data. Faculty will be {@code DEFAULT_FACULTY} and
     * year of study will be {@code DEFAULT_YEAR_OF_STUDY}, with all other fields empty but not null.
     *
     * @param p the person to copy.
     */
    public IntervieweeBuilder(Person p) {
        super(p);
        this.faculty = new Faculty(DEFAULT_FACULTY);
        this.yearOfStudy = Integer.parseInt(DEFAULT_YEAR_OF_STUDY);
        this.departmentChoices = new ArrayList<>();
        this.allocatedTimeslots = new ArrayList<>();
        this.emails = new Emails();
    }

    /**
     * Initializes the IntervieweeBuilder with the data of {@code toCopy}.
     */
    public IntervieweeBuilder(Interviewee i) {
        super(i.getName().fullName,
                i.getPhone().value,
                i.getTags().stream().map(x -> x.tagName).toArray(String[]::new));
        faculty = i.getFaculty();
        yearOfStudy = i.getYearOfStudy();
        departmentChoices = i.getDepartmentChoices();
        allocatedTimeslots = i.getAvailableTimeslots();
        emails = TestUtil.deepCopyEmails(i.getEmails());
    }

    /**
     * Sets the {@code Name} of the {@code Interviewee} that we are building.
     */
    public IntervieweeBuilder withName(String name) {
        super.withName(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Interviewee} that we are building.
     */
    public IntervieweeBuilder withPhone(String phone) {
        super.withPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code Interviewee} that we are building.
     */
    public IntervieweeBuilder withFaculty(String faculty) {
        try {
            this.faculty = ParserUtil.parseFaculty(faculty);
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Sets the stud year of the {@code Interviewee} that we are building.
     * @throws ParseException if input is invalid.
     */
    public IntervieweeBuilder withYearOfStudy(String yearOfStudy) {
        try {
            this.yearOfStudy = ParserUtil.parseYearOfStudy(yearOfStudy);
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Sets the {@code Department}s of the {@code Interviewee} that we are building.
     * @throws ParseException if input is invalid.
     */
    public IntervieweeBuilder withDepartmentChoices(String... departments) {
        try {
            this.departmentChoices = ParserUtil.parseDepartments(Arrays.asList(departments));
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Sets the {@code Slot}s of the {@code Interviewee} that we are building.
     */
    public IntervieweeBuilder withTimeslots(String... timeslots) {
        try {
            this.allocatedTimeslots = ParserUtil.parseSlots(Arrays.asList(timeslots));
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Sets the personal email of the {@code Interviewee} that we are building.
     */
    public IntervieweeBuilder withPersonalEmail(String email) {
        try {
            Email toAdd = ParserUtil.parseEmail(email);
            if (emails != null) {
                this.emails.addPersonalEmail(toAdd);
            } else {
                this.emails = new Emails().addPersonalEmail(toAdd);
            }
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Sets the Nus work email of the {@code Interviewee} that we are building.
     */
    public IntervieweeBuilder withNusWorkEmail(String email) {
        try {
            Email toAdd = ParserUtil.parseEmail(email);
            if (emails != null) {
                this.emails.addNusEmail(toAdd);
            } else {
                this.emails = new Emails().addNusEmail(toAdd);
            }
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Clears all tags from the parent class and replaces it with the supplied tags.
     */
    @Override
    public IntervieweeBuilder withTags(String... tags) {
        try {
            super.getTags().clear();
            super.getTags().addAll(ParserUtil.parseTags(Arrays.asList(tags)));
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR, e);
        }
        return this;
    }

    /**
     * Builds the Interviewee.
     */
    public Interviewee build() {
        return new Interviewee.IntervieweeBuilder(getName(), getPhone(), getTags())
                    .faculty(faculty)
                    .yearOfStudy(yearOfStudy)
                    .departmentChoices(departmentChoices)
                    .availableTimeslots(allocatedTimeslots)
                    .emails(emails)
                    .build();
    }

}

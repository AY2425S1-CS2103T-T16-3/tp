package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * A group of persons which has an identifying name, and can be made and edited by the user.
 * Uses the implementation of UniquePersonList
 *
 * @see UniquePersonList
 */
public class Group extends UniquePersonList {
    private String name;

    /**
     * Creates a group named {@code name}.
     */
    public Group(String name) {
        super();
        this.name = name;
    }

    /**
     * Renames the group to the specified {@code newName}.
     */
    public void rename(String newName) {
        name = newName;
    }

    /**
     * @return Name of the group.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if {@code group} has the same name as this group.
     * @return True if the groups have the same name, false otherwise.
     */
    public boolean sameName(Group group) {
        requireNonNull(group);
        return name.equals(group.name);
    }
}

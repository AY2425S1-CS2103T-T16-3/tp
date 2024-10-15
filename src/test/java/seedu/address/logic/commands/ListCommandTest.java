package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.SortOption;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        // Same ListCommand without sortOption should be equal
        ListCommand listCommand1 = new ListCommand();
        ListCommand listCommand2 = new ListCommand();
        assertEquals(listCommand1, listCommand2);

        // ListCommand with the same sortOption should be equal
        SortOption sortOption1 = new SortOption("name");
        SortOption sortOption2 = new SortOption("name");
        ListCommand listCommandWithSort1 = new ListCommand(sortOption1);
        ListCommand listCommandWithSort2 = new ListCommand(sortOption2);
        assertEquals(listCommandWithSort1, listCommandWithSort2);

        // ListCommand with and without sortOption should not be equal
        assertNotEquals(listCommand1, listCommandWithSort1);
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT);

        // Ensure there are no duplicate sort prefixes
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SORT);

        Optional<String> sortOption = argMultimap.getValue(PREFIX_SORT);

        if (sortOption.isEmpty()) {
            // No sort option
            return new ListCommand();
        }

        String sortOptionValue = sortOption.get().trim();

        // For now, we accept any non-empty sort option
        if (sortOptionValue.isEmpty()) {
            throw new ParseException("Sort option cannot be empty.");
        }
        return new ListCommand(sortOptionValue);
    }
}

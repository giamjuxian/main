package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_allFieldSpecified_success() {
        // multiple patient names - last patient name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY + REMARK_DESC_AMY,
                new RemarkCommand(new Name(VALID_NAME_AMY), new Phone(VALID_PHONE_AMY),
                        new Remark(VALID_REMARK_AMY)));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_BOB + PHONE_DESC_AMY + REMARK_DESC_AMY,
                new RemarkCommand(new Name(VALID_NAME_AMY), new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY)));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + REMARK_DESC_BOB + REMARK_DESC_AMY,
                new RemarkCommand(new Name(VALID_NAME_AMY), new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY)));
    }

    @Test
    public void parse_optionalFieldSpecified_success() {
        // no phone input
        assertParseSuccess(parser, NAME_DESC_AMY + REMARK_DESC_AMY,
                new RemarkCommand(new Name(VALID_NAME_AMY), null, new Remark(VALID_REMARK_AMY)));
    }

    @Test
    public void parse_missingParts_failure() {
        // no name specified
        assertParseFailure(parser, VALID_NAME_AMY + REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_AMY + REMARK_DESC_AMY,
                Name.MESSAGE_NAME_CONSTRAINTS);
    }


}

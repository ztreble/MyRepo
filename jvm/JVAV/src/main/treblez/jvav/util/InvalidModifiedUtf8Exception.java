/*
 * This is free and unencumbered software released into the public domain.
 * For more information, please refer to <http://unlicense.org/>.
 */
package treblez.jvav.util;

import java.nio.charset.MalformedInputException;

/**
 * A sequence of octets is not a valid encoding of the modified UTF-8
 * character-encoding scheme.
 * <p>
 * An instance of this class is an unchecked exception, whereas an instance of
 * {@link MalformedInputException} is a checked exception. An instance of this
 * class should instead be thrown when a sequence of octets is expected to be a
 * valid encoding.
 *
 * @author  Nathan Ryan
 */
public class InvalidModifiedUtf8Exception extends RuntimeException {

    public InvalidModifiedUtf8Exception(final MalformedInputException cause) {
        super(cause);
    }

    // standard override for covariant return
    @Override
    public InvalidModifiedUtf8Exception initCause(final Throwable cause) {
        return (InvalidModifiedUtf8Exception)(super.initCause(cause));
    }

    // standard override for covariant return
    @Override
    public InvalidModifiedUtf8Exception fillInStackTrace() {
        return (InvalidModifiedUtf8Exception)(super.fillInStackTrace());
    }
}

/*
 * This is free and unencumbered software released into the public domain.
 * For more information, please refer to <http://unlicense.org/>.
 */
package trebelz.jvav.util;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;

/**
 * A charset for modified UTF-8, the character-encoding scheme used by the
 * {@code class} file format for the internal representation of strings.
 * <p>
 * This character-encoding scheme is defined in various locations, which include
 * the following:
 * <ul>
 * <li><a href="https://docs.oracle.com/javase/10/docs/api/java/io/DataInput.html#modified-utf-8">
 *         <i>Java Platform,
 *         Standard Edition &amp; Java Development Kit Version 10 API Specification</i>,
 *         interface {@code java.io.DataInput},
 *         Modified UTF-8
 * <li><a href="https://docs.oracle.com/javase/specs/jvms/se10/html/jvms-4.html#jvms-4.4.7">
 *         <i>The Java Virtual Machine Specification,
 *         Java SE 10 Edition</i>,
 *         section 4.4.7: The {@code CONSTANT_Utf8_info} Structure
 *     </a>
 * <li><a href="https://docs.oracle.com/javase/10/docs/specs/serialization/protocol.html#stream-elements">
 *         <i>Java Object Serialization Specification</i>,
 *         section 6.2: Stream Elements
 *     </a>
 * <li><a href="https://docs.oracle.com/javase/10/docs/specs/jni/types.html#modified-utf-8-strings">
 *         <i>Java Native Interface Specification</i>,
 *         section 3: JNI Types and Data Structures,
 *         Modified UTF-8 Strings
 *     </a>
 * <li><a href="https://source.android.com/devices/tech/dalvik/dex-format#mutf-8">
 *         Android Open Source Project,
 *         Android Core Technologies,
 *         ART and Dalvik,
 *         Dex Format,
 *         MUTF-8 (Modified UTF-8) Encoding
 *     </a>
 *     <br>
 *     (The Android-platform definition augments the Java-platform definitions
 *     by using the null octet as an indicator for the end of an encoding,
 *     presumably to allow unknown-length encodings; the Java platform uses only
 *     known-length encodings.)
 * </ul>
 * <p>
 * The various Java-platform definitions of modified UTF-8 are uniform in their
 * assertion that a null octet never appears in an encoding. However, the
 * {@linkplain java.io.DataInputStream#readUTF(java.io.DataInput) reference implementation}
 * decodes a null octet as the null character, as specified by the now-historic
 * definition in <i>The Java Language Specification, Edition 1.0</i>, section
 * 22.1.15. This behavior was subsequently re-affirmed by the resolution of
 * <a href="https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4140874">JDK-4140874</a>.
 * Consequently, a null character can be encoded only as
 * [{@code 0xC0}&nbsp;{@code 0x80}], but it can be decoded both from
 * [{@code 0xC0}&nbsp;{@code 0x80}] and from [{@code 0x00}].
 * <p>
 * In general, asymmetry between an encoding scheme and a corresponding decoding
 * scheme is not unusual. In the case of modified UTF-8, neither is it
 * restricted to the null character: Any character that can be decoded from a
 * one-octet sequence can be decoded from a two-octet sequence, and any
 * character that can be decoded from a two-octet sequence can be decoded from a
 * three-octet sequence.
 * <p>
 * The case of the null character is worth special mention because an
 * implementation may erroneously expect that, since a null octet never appears
 * in a sequence of octets that was encoded by a conformant encoder, a null
 * octet will never appear in a sequence of octets that can be decoded by a
 * conformant decoder. Said another way, a sequence of octets that is considered
 * to be a valid encoding of modified UTF-8 will not contain a null octet if
 * produced by an encoder, but it <em>may</em> contain a null octet if intended
 * for consumption by a decoder. Therefore, <strong>the use of a null octet as a
 * terminator for an encoding of modified UTF-8 is unsafe</strong>, without
 * additional guarantees.
 *
 * @author  Nathan Ryan
 * @see     <a href="https://tools.ietf.org/html/rfc3629">UTF-8</a>
 * @see     <a href="http://www.unicode.org/reports/tr26">CESU-8</a>
 */
public class ModifiedUtf8Charset extends Charset {

    /**
     * The canonical name of this character-encoding scheme.
     * <p>
     * This character-encoding scheme is uniformly but unofficially identified
     * as "modified UTF-8" by the Java platform. It is not listed in the IANA
     * Charset Registry, and it has no corresponding IETF RFC. Consequently,
     * this character-encoding scheme has no official canonical name.
     * <p>
     * The canonical name used here has the prefix "X-", in accordance with the
     * <a href="https://docs.oracle.com/javase/10/docs/api/java/nio/charset/Charset.html#charenc">rules for charset names</a>,
     * followed by "MUTF-8", which has become the de&nbsp;facto community
     * standard.
     * <p>
     * Because this character-encoding scheme is not standard, and because a
     * charset alias should never be removed, this character-encoding scheme
     * defines no aliases in order to avoid possible future conflicts.
     */
    public static final String CANONICAL_NAME = "X-MUTF-8";

    private static final String[] ALIASES = null;

    public ModifiedUtf8Charset() {
        super(CANONICAL_NAME, ALIASES);
    }

    /**
     * @return  {@code true}, if the given charset is an instance of this class,
     *          or if the given charset is contained by
     *          {@linkplain StandardCharsets#UTF_8 UTF-8}; {@code false},
     *          otherwise
     */
    @Override
    public boolean contains(final Charset that) {
        return ((that instanceof ModifiedUtf8Charset) || StandardCharsets.UTF_8.contains(that));
    }

    static class Decoder extends CharsetDecoder {
        // The average-characters-per-byte is actually something less than one.
        // However, a value of 1.0 will allow the decoder to allocate a
        // precisely sized character buffer in the common case of an encoding
        // that encode only 7-bit (non-null) character values.
        Decoder(final ModifiedUtf8Charset charset) {
            super(charset, 1.0F, 1.0F);
        }

        @Override
        protected CoderResult decodeLoop(final ByteBuffer source, final CharBuffer target) {
            // Track the position of the source buffer, so that consumed but
            // unused octets can be "put back". The value of this variable is
            // explicitly incremented each time a character is successfully
            // decoded, in order to avoid having to query the source buffer via
            // an unnecessary method invocation inside the loop.
            int sourcePosition = source.position();
            while (true) {
                try {
                    final byte a = source.get();
                    // The first three bits of the first octet determine the
                    // length of the octet sequence. Simultaneously checking the
                    // fourth bit is a cheap way to avoid an explicit check for
                    // an invalid leading octet of 1111xxxx.
                    //
                    // Shifting the four high bits to the four low bits makes
                    // the switch labels nearly contiguous. This enables the
                    // compiler to use the tableswitch instruction, rather than
                    // the lookupswitch instruction. The distinction could be
                    // significant for a tight loop, though in this case a
                    // modern JIT compiler would probably be able to optimize
                    // away any difference.
                    switch ((a & 0xFF) >> 4) {
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: {
                            // first octet 0xxxxxxx
                            // 000000000aaaaaaa as 0aaaaaaa
                         // final char ch = (char)(a);
                         // target.put(ch);
                            target.put((char)(a));
                            sourcePosition += 1;
                            break;
                        }
                        case 12: case 13: {
                            // first octet 110xxxxx
                            // 00000aaaaabbbbbb as 110aaaaa 10bbbbbb
                            final byte b = source.get();
                            if ((b & 0xC0) != 0x80) {
                                return CoderResult.malformedForLength(2);
                            }
                         // final char ch = (char)(((a & 0x1F) << 6) | (b & 0x3F));
                         // target.put(ch);
                            target.put((char)(((a & 0x1F) << 6) | (b & 0x3F)));
                            sourcePosition += 2;
                            break;
                        }
                        case 14: {
                            // first octet 1110xxxx
                            // aaaabbbbbbcccccc as 1110aaaa 10bbbbbb 10cccccc
                            final byte b = source.get();
                            if ((b & 0xC0) != 0x80) {
                                return CoderResult.malformedForLength(2);
                            }
                            final byte c = source.get();
                            if ((c & 0xC0) != 0x80) {
                                return CoderResult.malformedForLength(3);
                            }
                         // final char ch = (char)(((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F));
                         // target.put(ch);
                            target.put((char)(((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F)));
                            sourcePosition += 3;
                            break;
                        }
                     // case 8: case 9: case 10: case 11:
                            // first octet 10xxxxxx
                     // case 15:
                            // first octet 1111xxxx
                        default: {
                            return CoderResult.malformedForLength(1);
                        }
                    }
                } catch (final BufferUnderflowException e) {
                    // "Put back" unused octets of a partial character.
                    source.position(sourcePosition);
                    return CoderResult.UNDERFLOW;
                } catch (final BufferOverflowException e) {
                    // "Put back" unused octets of a full character.
                    source.position(sourcePosition);
                    return CoderResult.OVERFLOW;
                }
            }
        }
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    static class Encoder extends CharsetEncoder {

        Encoder(final ModifiedUtf8Charset charset) {
            super(charset, 1.1F, 3.0F);
        }

        @Override
        protected CoderResult encodeLoop(final CharBuffer source, final ByteBuffer target) {
            // Track the position of the source buffer, so that a consumed but
            // unused character can be "put back". The value of this variable is
            // explicitly incremented each time a character is successfully
            // encoded, in order to avoid having to query the source buffer via
            // an unnecessary method invocation inside the loop.
            int sourcePosition = source.position();
            while (true) {
                try {
                    final char ch = source.get();
                    if (ch == 0) {
                        // 00000000 00000000 as 11000000 10000000
                        if (target.remaining() < 2) {
                            // Write no octets unless all octets fit.
                            throw new BufferOverflowException();
                        }
                     // final byte a = (byte)(0xC0);
                     // final byte b = (byte)(0x80);
                     // target.put(a).put(b);
                        target.put((byte)(0xC0))
                              .put((byte)(0x80));
                    } else if (ch < (1 << 7)) {
                        // 00000000 0aaaaaaa as 0aaaaaaa
                     // final byte a = (byte)(ch);
                     // target.put(a);
                        target.put((byte)(ch));
                    } else if (ch < (1 << 11)) {
                        // 00000aaa aabbbbbb as 110aaaaa 10bbbbbb
                        if (target.remaining() < 2) {
                            // Write no octets unless all octets fit.
                            throw new BufferOverflowException();
                        }
                     // final byte a = (byte)(0xC0 | (ch >> 6));
                     // final byte b = (byte)(0x80 | (ch & 0x3F));
                     // target.put(a).put(b);
                        target.put((byte)(0xC0 | (ch >> 6)))
                              .put((byte)(0x80 | (ch & 0x3F)));
                    } else {
                        // aaaabbbb bbcccccc as 1110aaaa 10bbbbbb 10cccccc
                        if (target.remaining() < 3) {
                            // Write no octets unless all octets fit.
                            throw new BufferOverflowException();
                        }
                     // final byte a = (byte)(0xE0 | (ch >> 12));
                     // final byte b = (byte)(0x80 | ((ch >> 6) & 0x3F));
                     // final byte c = (byte)(0x80 | (ch & 0x3F));
                     // target.put(a).put(b).put(c);
                        target.put((byte)(0xE0 | (ch >> 12)))
                              .put((byte)(0x80 | ((ch >> 6) & 0x3F)))
                              .put((byte)(0x80 | (ch & 0x3F)));
                    }
                    sourcePosition++;
                } catch (final BufferUnderflowException e) {
                    return CoderResult.UNDERFLOW;
                } catch (final BufferOverflowException e) {
                    // "Put back" an unused character.
                    source.position(sourcePosition);
                    return CoderResult.OVERFLOW;
                }
            }
        }
    }

    @Override
    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }
}

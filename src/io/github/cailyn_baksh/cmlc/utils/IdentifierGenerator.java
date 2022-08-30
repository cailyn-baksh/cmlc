package io.github.cailyn_baksh.cmlc.utils;

/**
 * Generates a valid C identifier. Generated identifiers are unique in both
 * value and appearance, i.e. identifiers generated close to each other are
 * easy for a human to discriminate between.
 */
public class IdentifierGenerator {
    public static final char[] BASE62_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    public static final int FNV_PRIME = 0x01000193;
    public static final int FNV_OFFSET_BASIS = 0x811c9dc5;
    public static final String ID_PREFIX = "_cmlc_";

    private static int seed = 0;

    /**
     * Calculate the 32-bit FNV-1a hash of an integer
     * @param n The integer to hash
     * @return The hash
     */
    private static int fnv1a(int n) {
        int hash = FNV_OFFSET_BASIS;

        for (int i=0; i < 4; ++i) {
            hash ^= (byte)n;  // Casting to byte discards all but LSB
            hash *= FNV_PRIME;
            n >>>= 8;
        }

        return hash;
    }

    /**
     * Return a unique C identifier
     * @return
     */
    public static String getIdentifier() {
        int hashed = fnv1a(seed++);

        StringBuilder sb = new StringBuilder();

        sb.append(ID_PREFIX);

        while (hashed != 0) {
            byte b = (byte)((hashed & (0x3f << 26)) >>> 26);  // high 6 bits

            if ((b & 0b111100) == 0b111100) {  // test if the 4 highest bits are set
                if ((b & 0b10) == 0b10) {
                    // 11111x
                    sb.append(BASE62_CHARS[61]);
                } else {
                    // 11110x
                    sb.append(BASE62_CHARS[60]);
                }

                hashed <<= 5;
            } else {
                sb.append(BASE62_CHARS[b]);
                hashed <<= 6;
            }
        }

        return sb.toString();
    }
}

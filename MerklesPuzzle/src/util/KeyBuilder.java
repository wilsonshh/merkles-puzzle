package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This class is used to build key.
 *
 * @author Wilson Shrestha
 */
public class KeyBuilder {

    /**
     * This builds byte array with key component (16 bits) and zero (48 zero
     * bits) component
     *
     * @param keyComponent Byte array of key
     * @param zeroComponent Byte array of 48 zero bits
     * @return This returns 64 bit key.
     * @throws IOException
     */
    public static byte[] buildKey(byte[] keyComponent, byte[] zeroComponent) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(keyComponent);
        output.write(zeroComponent);
        return output.toByteArray();
    }
}

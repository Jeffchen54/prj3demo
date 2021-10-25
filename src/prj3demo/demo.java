package prj3demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class demo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("sample.bin", "r");

        // Creating input byte array of 1 block size
        byte[] input = new byte[8192];

        // Initializing byte array with data and wrapping with a buffer
        file.read(input);
        ByteBuffer buffer = ByteBuffer.wrap(input);

        // Printing out the first key
        byte[] key = new byte[8];
        buffer.get(key);
        convertToLong(key);
        buffer.rewind();

        // Mark manipulation
        // Set a cursor
        buffer.mark();

        // Move forward
        buffer.get(key);

        // Move back to cursor
        buffer.reset();

        // Reset to 0 remove mark
        buffer.rewind();

        // Reading in the next block from input
        file.read(input);
        key = new byte[8];
        buffer.get(key);
        convertToLong(key);

        // Reading the value now
        buffer.get(key);
        convertToDouble(key);

    }


    public static void convertToLong(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        System.out.println(buffer.getLong());
    }


    public static void convertToDouble(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        System.out.println(buffer.getDouble());
    }
}

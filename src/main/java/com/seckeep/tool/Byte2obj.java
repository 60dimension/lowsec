package com.seckeep.tool;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by 60dimension on 2017/7/20.
 */
public class Byte2obj
{
    public static Object toObject(byte[] bytes) {
        Object object = null;
        if (bytes == null || bytes.length == 0)
            return null;

        ByteArrayInputStream bis = null;
        ObjectInputStream inputStream = null;
        try {
            if (bytes.length < 8) {
                // to avoid EOFException: bytes length for ObjectInputSteam can't less than 8
                return new String(bytes, "utf-8");
            }

            bis = new ByteArrayInputStream(bytes);
            inputStream = new ObjectInputStream(bis);
         //   inputStream = new ObjectInputStream(bis);
            object = inputStream.readObject();
        } catch (java.io.StreamCorruptedException e) {
            //
            try {
                return new String(bytes, "utf-8");
            } catch (Exception ee) {
                throw new RuntimeException(ee);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(bis);
            closeQuietly(inputStream);
        }

        return object;
    }

    private static void closeQuietly(Closeable stream) {
        if (stream == null)
        {
            return;
        }
        try {
            stream.close();
        } catch (IOException ioe) {
            // ignore
        }
    }
}

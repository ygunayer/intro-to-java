package com.emlakjet.intro.caching;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Properties;

public class FileCache<K, V> implements Cache<K, V> {
    private String filename;

    public FileCache(String filename) {
        this.filename = filename;
    }

    private static <T> String encode(T t) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(t);
        so.close();
        byte[] buf = bo.toByteArray();
        bo.close();
        return Base64.getEncoder().encodeToString(buf);
    }

    private static <T> T decode(String s) throws ClassNotFoundException, IOException {
        byte[] data = Base64.getDecoder().decode(s);
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(bis);
        Object o = is.readObject();
        is.close();
        bis.close();
        return (T) o;
    }

    private Properties read() throws IOException {
        Properties props = new Properties();

        File file = new File(filename);

        if (!file.exists()) {
            return props;
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            props.load(fis);
            return props;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    private void write(Properties props) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            props.store(fos, null);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Override
    public boolean contains(K key) {
        try {
            Properties props = read();
            String encodedKey = encode(key);
            return props.containsKey(encodedKey);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public V get(K key) {
        try {
            Properties props = read();
            String encodedKey = encode(key);
            String encodedValue = props.getProperty(encodedKey);

            if (encodedValue == null) {
                return null;
            }

            return decode(encodedValue);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void set(K key, V value) {
        try {
            Properties props = read();
            String encodedKey = encode(key);
            String encodedValue = encode(value);
            props.setProperty(encodedKey, encodedValue);
            write(props);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

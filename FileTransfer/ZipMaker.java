import java.io.*;
import java.util.zip.*;
import java.net.*;
import java.util.*;

/** 
 * A class that can create a zip file from a given folder path.
 * @author Emin Bahadir Tuluce, McDowell@StackOverflow
 * SOURCE: http://stackoverflow.com/a/1399432/7487260
 */

public class ZipMaker {
    
    /** Buffer size for writing/reading bytes */
    public static final int BUFFER = 1024;
    
    /**
     * Compresses the all the files and subfolders in a folder.
     * @param folderPath the path of the folder to zip (C://take/from/here/)
     * @param savePath the path of the new zip file (C://some/directory/saveHere.zip)
     * @throws IOException if one of the paths are not valid
     */
    public static void makeZip( String folderPath, String savePath) throws IOException {
        File folder = new File( folderPath);
        File zipFile = new File( savePath);
        zip( folder, zipFile);
    }

    private static void zip(File directory, File zipfile) throws IOException {
        URI base = directory.toURI();
        Deque<File> queue = new LinkedList<File>();
        queue.push(directory);
        OutputStream out = new FileOutputStream(zipfile);
        Closeable res = out;
        try {
            ZipOutputStream zout = new ZipOutputStream(out);
            res = zout;
            while (!queue.isEmpty()) {
                directory = queue.pop();
                for (File kid : directory.listFiles()) {
                    String name = base.relativize(kid.toURI()).getPath();
                    if (kid.isDirectory()) {
                        queue.push(kid);
                        name = name.endsWith("/") ? name : name + "/";
                        zout.putNextEntry(new ZipEntry(name));
                    } else {
                        zout.putNextEntry(new ZipEntry(name));
                        copy(kid, zout);
                        zout.closeEntry();
                    }
                }
            }
        } finally {
            res.close();
        }
    }
    
    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER];
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }
    
    private static void copy(File file, OutputStream out) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            copy(in, out);
        } finally {
            in.close();
        }
    }
    
    private static void copy(InputStream in, File file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            copy(in, out);
        } finally {
            out.close();
        }
    }
}
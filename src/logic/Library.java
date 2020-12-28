package logic;

import javax.swing.*;
import java.io.*;

public class Library implements Serializable {
    private String path;
    private boolean isDirectory;

    public Library(File file) {
        this.path = file.getAbsolutePath();
        isDirectory = file.isDirectory();
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Library)) {
            return false;
        }
        if (path.equals(((Library) obj).getPath())) {
            return true;
        }
        return false;
    }
}

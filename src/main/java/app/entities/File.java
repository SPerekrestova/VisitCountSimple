package app.entities;

import java.util.Objects;
/*
    Class for managing instance of uploaded files
 */

public class File {
    private String filePath;
    private String fileName;

    // default constructor
    public File() {}

    public File(String filePath, String fileName) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {this.fileName = fileName;}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {this.filePath = filePath;}

    @Override
    public String toString() {
        return "File{" +
                "name='" + fileName + '\'' +
                ", path='" + filePath + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (!Objects.equals(fileName, file.fileName)) return false;
        return Objects.equals(filePath, file.filePath);
    }
    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        return result;
    }
}

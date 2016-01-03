package de.phillme;

import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright: Lirdy UG(haftungsbeschränkt)
 * Author: Phillip Merensky
 * Date: 03.01.16
 * Time: 22:30
 */
public class PhotoFile {
    private Path filePath;
    private Date photoDate;
    private List<String> supportedFileExtensions = new ArrayList<>();

    public PhotoFile(Path filePath, Date photoDate) {
        //TODO make this configurable
        this.supportedFileExtensions.add("arw.xmp");
        this.supportedFileExtensions.add("ARW.XMP");
        this.supportedFileExtensions.add("jpg");
        this.supportedFileExtensions.add("JPG");
        this.supportedFileExtensions.add("ARW");
        this.supportedFileExtensions.add("arw");

        this.filePath = filePath;
        this.photoDate = photoDate;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public List<String> getSupportedFileExtensions() {
        return supportedFileExtensions;
    }

    public void setSupportedFileExtensions(List<String> supportedFileExtensions) {
        this.supportedFileExtensions = supportedFileExtensions;
    }

    @Override
    public String toString() {
        return getISO8601StringForDate(photoDate);
    }

    private static String getISO8601StringForDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
}

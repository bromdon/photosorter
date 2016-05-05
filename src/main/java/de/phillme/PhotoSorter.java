/*
 *     This file is part of photosorter.
 *
 *     photosorter is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     photosorter is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 *     Diese Datei ist Teil von photosorter.
 *
 *     photosorter ist Freie Software: Sie können es unter den Bedingungen
 *     der GNU General Public License, wie von der Free Software Foundation,
 *     Version 3 der Lizenz oder (nach Ihrer Wahl) jeder späteren
 *     veröffentlichten Version, weiterverbreiten und/oder modifizieren.
 *
 *     photosorter wird in der Hoffnung, dass es nützlich sein wird, aber
 *     OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *     Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
 *     Siehe die GNU General Public License für weitere Details.
 *
 *     Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
 *     Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
 */

/*
 *     This file is part of photosorter.
 *
 *     photosorter is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     photosorter is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 *     Diese Datei ist Teil von photosorter.
 *
 *     photosorter ist Freie Software: Sie können es unter den Bedingungen
 *     der GNU General Public License, wie von der Free Software Foundation,
 *     Version 3 der Lizenz oder (nach Ihrer Wahl) jeder späteren
 *     veröffentlichten Version, weiterverbreiten und/oder modifizieren.
 *
 *     photosorter wird in der Hoffnung, dass es nützlich sein wird, aber
 *     OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *     Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
 *     Siehe die GNU General Public License für weitere Details.
 *
 *     Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
 *     Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
 */

/*
 *     This file is part of photosorter.
 *
 *     photosorter is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     photosorter is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 *     Diese Datei ist Teil von photosorter.
 *
 *     photosorter ist Freie Software: Sie können es unter den Bedingungen
 *     der GNU General Public License, wie von der Free Software Foundation,
 *     Version 3 der Lizenz oder (nach Ihrer Wahl) jeder späteren
 *     veröffentlichten Version, weiterverbreiten und/oder modifizieren.
 *
 *     photosorter wird in der Hoffnung, dass es nützlich sein wird, aber
 *     OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *     Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
 *     Siehe die GNU General Public License für weitere Details.
 *
 *     Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
 *     Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
 */


package de.phillme;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class PhotoSorter {


    private boolean moveInsteadCopy = false;
    private String actionName = "copy";
    private boolean noRename = false;
    private TimeZone timeZone;
    private Date dateOld;
    private Date eventStartDate;
    private Date eventEndDate;

    private int hoursBetweenEvents = 36;

    private String eventFileSuffix = "";

    private String dateFormatPhotos = "";
    private String dateFormatFolders = "";
    private int photoNumberInEvent = 1;


    //private String splitBetweenDateAndRest = "";

    private Path photosPath = null;

    private boolean write = false;


    private List<PhotoEvent> eventList = new ArrayList<>();

    //TODO clean up and refactor make this more understandeable and mainteneable at some time
    private final static Logger LOGGER = Logger.getLogger(PhotoSorter.class.getName());
    private final Tika tika = new Tika();

    private PhotoSorter(CommandLine commandLine) {
        initLogging();

        this.hoursBetweenEvents = Integer.parseInt(commandLine.getOptionValue("minhours", "36"));
        this.dateFormatPhotos = commandLine.getOptionValue("dfp", "yyyy-MM-dd'T'HHmm");
        this.dateFormatFolders = commandLine.getOptionValue("dfe", "yyyy-MM-dd");

        //this.splitBetweenDateAndRest = commandLine.getOptionValue("dsplitchar", "_");
        this.photosPath = Paths.get(commandLine.getOptionValue("p", "."));
        this.timeZone = TimeZone.getTimeZone(commandLine.getOptionValue("timezone", Calendar.getInstance().getTimeZone().getID()));

        this.noRename = commandLine.hasOption("n");
        this.moveInsteadCopy = commandLine.hasOption("mv");

        if (this.moveInsteadCopy) {
            this.actionName = "move";
        }

        LOGGER.info("\r\n  _____  _           _        _____            _            \r\n |  __ \\| |         | |      / ____|          | |           \r\n | |__) | |__   ___ | |_ ___| (___   ___  _ __| |_ ___ _ __ \r\n |  ___/| '_ \\ / _ \\| __/ _ \\\\___ \\ / _ \\| '__| __/ _ \\ '__|\r\n | |    | | | | (_) | || (_) |___) | (_) | |  | ||  __/ |   \r\n |_|    |_| |_|\\___/ \\__\\___/_____/ \\___/|_|   \\__\\___|_|   ");

        if (commandLine.hasOption("w")) {
            this.write = true;
            LOGGER.info("'\n-w' specified. Changes are written...");
        } else {
            LOGGER.info("\nPhotoSorter starting in dry-run mode. No changes written. \nIf you want to write changes add '-w' as an option.\n\nUse '-h' for help.");
        }

        LOGGER.info("Using timezone " + timeZone.getID() + ".");
        LOGGER.info("Using \"" + this.dateFormatFolders + "\" as a date format for folders.");

        if (this.noRename) {
            LOGGER.info("No-rename set. Photos will NOT be renamed.");
        } else {
            LOGGER.info("Using \"" + this.dateFormatPhotos + "\" as a date format for photos. Photos will be renamed.");
        }
        if (this.moveInsteadCopy) {
            LOGGER.info("Moving files instead of copying.");
        } else {
            LOGGER.info("Copying files. Add '-mv' to move files.");
        }
        LOGGER.info("");


    }


    private void initLogging() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        LOGGER.setLevel(Level.INFO);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.INFO);
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }

    private Date getDateFromExif(Path photo) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(photo.toFile());

        // obtain the Exif directory
        ExifSubIFDDirectory edirectory
                = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
// query the tag's value
        Date date
                = edirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL, this.timeZone);

        /*for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s\n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }*/

        return date;
    }

    private void parseFile(PhotoFile photoFile, boolean lastFile) throws IOException {

        detectNewEvent(photoFile, this.dateOld, photoFile.getPhotoDate(), lastFile);
        this.dateOld = photoFile.getPhotoDate();

        //LOGGER.finest(photoFile.getFilePath().toString());
    }

   /* private Date parseDateFromFileName(String fileName) throws ParseException {
        String string = "2015-01-05T2254_06470.arw";
        String[] split = fileName.split(this.splitBetweenDateAndRest);

        DateFormat format = new SimpleDateFormat(this.dateFormatPhotos);
        Date date = format.parse(split[0]);
        LOGGER.finest("DATE " + date.toString());

        return date;
    }     */

    private String generateNewFileName(PhotoFile photoFile) {
        if (photoFile.getPhotoDate() != null) {

            SimpleDateFormat sdf = new SimpleDateFormat(this.dateFormatPhotos);
            sdf.setTimeZone(this.timeZone);

            String newFileName = sdf.format(photoFile.getPhotoDate()) + "_" + this.photoNumberInEvent;
            String fileExt = getFileExt(photoFile.getFilePath().getFileName().toString());

            if (fileExt != null) {
                newFileName = newFileName + "." + fileExt;
                return newFileName;
            }
        }

        return null;
    }

    private String getFileBase(String fileName) {
        String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
        //LOGGER.finest(Arrays.toString(tokens));
        if (tokens.length > 0) {
            String fileBase = "";
            for (int i = 0; i < (tokens.length - 1); i++) {
                fileBase += tokens[i];
            }
            return fileBase;

        } else {
            return null;
        }
    }

    private String getFileExt(String fileName) {
        String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
        //LOGGER.info(Arrays.toString(tokens));
        if (tokens.length > 0) {
            String fileExt = "";
            fileExt += tokens[tokens.length - 1];
            LOGGER.finest("File ext is " + fileExt);
            return fileExt;

        } else {
            return null;
        }
    }


    private void moveRelevantFiles(String targetParent, PhotoFile photoFile) throws IOException {
        //TODO is this all safe?
        List<String> list = photoFile.getSupportedMetaDataFileExtensions();
        //Move original file
        String fileName = photoFile.getFilePath().getFileName().toString();


        if (!this.noRename) {
            //use the date provided for the photo files as a new name
            String newFileName = generateNewFileName(photoFile);
            if (newFileName != null) {
                fileName = newFileName;
            }
        }
        Path targetPath = Paths.get(targetParent + File.separator + fileName);

        if (targetPath != null) {
            if (this.write) {
                LOGGER.info(this.actionName + "-ing to " + targetPath);

                if (this.moveInsteadCopy) {
                    Files.move((photoFile.getFilePath()), targetPath);
                } else {
                    Files.copy((photoFile.getFilePath()), targetPath);
                }
            } else {
                LOGGER.info("Would " + this.actionName + " to " + targetPath);
            }

            //Move metadata files
            for (String ext :
                    list) {
                /* This does not work as additional sidecar files usually include the full file name and the sidecar extension (e.g. filename.arw.xmp and not only filename.xmp).
                String tmpFileBase = getFileBase(photoFile.getFilePath().getFileName().toString());
                 */
                String tmpFileBase = photoFile.getFilePath().getFileName().toString();

                if (fileName != null && tmpFileBase != null) {
                    File movableFile = new File(photoFile.getFilePath().getParent().toString() + File.separator + tmpFileBase + "." + ext);
                    if (movableFile.exists()) {
                        targetPath = Paths.get(targetParent + File.separator + fileName + "." + ext);
                        if (this.write) {
                            LOGGER.info(this.actionName + "-ing meta file to " + targetPath);

                            if (this.moveInsteadCopy) {
                                Files.move((movableFile.toPath()), targetPath);
                            } else {
                                Files.copy((movableFile.toPath()), targetPath);
                            }
                        } else {
                            LOGGER.info("Would " + this.actionName + " meta file to " + targetPath);
                        }
                    }
                } else {
                    LOGGER.info("Filebase of " + photoFile.getFilePath().getFileName().toString() + " could not be determined. Skipping...");
                }
            }
        }

    }

    private void movePhotoToEvent(PhotoFile photoFile, Date eventStartDate) throws IOException {
        SimpleDateFormat sdfEurope = new SimpleDateFormat(this.dateFormatFolders);
        sdfEurope.setTimeZone(this.timeZone);
        String sDateinEurope = sdfEurope.format(eventStartDate) + this.eventFileSuffix;

        String eventPath = this.photosPath + File.separator + sDateinEurope;
        File eventFile = new File(eventPath);

        if (!eventFile.exists() && this.write) {
            boolean success = (new File(eventPath)).mkdirs();
        }

        moveRelevantFiles(eventPath, photoFile);


    }

    private void detectNewEvent(PhotoFile photoFile, Date dateOld, Date dateNew, boolean lastFile) throws IOException {
        if (dateOld == null) {
            LOGGER.finest("\nBeginning... Using DateNew for first event");
            this.eventStartDate = dateNew;
            this.dateOld = dateNew;

            movePhotoToEvent(photoFile, this.eventStartDate);
            return;
        }
        long dateDiffInHours = PhotoSorter.getDateDiff(dateOld, dateNew, TimeUnit.HOURS);

        if (dateDiffInHours > hoursBetweenEvents) {
            LOGGER.info("");
            LOGGER.finest("Open event will be closed. HoursbetweenEvents exceeded. Range: \n  " + dateOld + " to \n  " + dateNew);
            this.eventEndDate = dateOld;
            //TODO this handles only the event before the last event. for the last event this has to be repeated and file has to be moved
            this.eventList.add(handleEvent());

            this.eventStartDate = dateNew;

            this.photoNumberInEvent++;

            if (lastFile) {
                //this is the last file. This means the last file's time range triggered an end of another event
                //but should be an event by itself in the event list. This is what we do here
                LOGGER.info("Last file leads to an additional event.");

                this.eventEndDate = dateNew;
                this.eventList.add(handleEvent());
            }
            //after the new startDate for the event is set we can move the file to the new event folder
            movePhotoToEvent(photoFile, this.eventStartDate);
        } else {
            //increase photo number for filenames;
            this.photoNumberInEvent++;
            //move all files to current event
            movePhotoToEvent(photoFile, this.eventStartDate);

            if (lastFile) {
                //if this is the lastfile but has not enough time between, still add it
                LOGGER.finest("Found an event between \n  " + dateOld + " and \n  " + dateNew);
                this.eventEndDate = dateNew;
                this.eventList.add(handleEvent());
            }
        }
    }

    private PhotoEvent handleEvent() {
        SimpleDateFormat sdfEurope = new SimpleDateFormat(this.dateFormatFolders);
        sdfEurope.setTimeZone(this.timeZone);
        String sDateinEurope = sdfEurope.format(this.eventStartDate) + this.eventFileSuffix;
        LOGGER.finest(sDateinEurope + " would be created as an event folder.\n");
        //File eventFile = new File(this.photosPath + File.separator + sDateinEurope);

        //eventFile.createNewFile();

        return new PhotoEvent(this.eventStartDate, this.eventEndDate);
    }

    private void printEventList(List<PhotoEvent> photoEventList) {
        LOGGER.info("\nPrinting list of detected events...");
        int i = 1;
        for (PhotoEvent pEvent :
                photoEventList) {
            LOGGER.info("Event " + i + " " + pEvent.toString());
            i++;
        }
    }

    private List<PhotoFile> listSourceFiles() throws IOException, ImageProcessingException {
        List<PhotoFile> result = new ArrayList<>();
        Date date;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.photosPath, "*.*")) {
            for (Path entry : stream) {
                date = null;
                PhotoFile photoFile;

                String fileType = detectMimeType(entry);
                //String fileExt = getFileExt(entry.getFileName().toString());

                if (fileType != null && fileType.contains("image")) {

                    date = getDateFromExif(entry);
                }
                if (date != null) {
                    photoFile = new PhotoFile(entry, date);

                    result.add(photoFile);
                } else {
                    LOGGER.info("Date of " + entry.getFileName() + " could not be determined. Skipping for image processing...");
                }
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    }

    /*Map<String, String> probeFiletypes() throws IOException, ImageProcessingException, ParseException, TikaException, SAXException {
        Map<String, String> result = new HashMap<>();

        LOGGER.info("Probing for filetypes...");
        Date date = null;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.photosPath, "*")) {
            for (Path entry : stream) {
                PhotoFile photoFile;
                //String fileType = Files.probeContentType(entry);
                String fileType = detectMimeType(entry);
                String fileExt = getFileExt(entry.getFileName().toString());
                if (fileType != null && fileType.contains("image")) {

                    if (result.get(fileType + ":" + fileExt) == null) {
                        result.put(fileType, fileExt);
                        LOGGER.info("Found " + fileType);

                    }

                }
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    } */

    /**
     * Get a diff between two dates
     *
     * @param date1    the oldest date
     * @param date2    the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private List<PhotoFile> sortList(List<PhotoFile> files) {
        Collections.sort(files, new Comparator<PhotoFile>() {
            public int compare(PhotoFile o1, PhotoFile o2) {
                return o1.getPhotoDate().compareTo(o2.getPhotoDate());
            }
        });
        return files;
    }

    private void flagAllEvents(List<PhotoFile> sortedList) throws IOException {
        for (int i = 0; i < sortedList.size(); i++) {
            PhotoFile photoFile = sortedList.get(i);

            if (i == sortedList.size() - 1) {
                parseFile(photoFile, true);
            } else {
                parseFile(photoFile, false);
            }
        }

    }

    private String detectMimeType(Path pathToDetect) throws IOException {
        return tika.detect(pathToDetect);
    }

    private List<PhotoEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<PhotoEvent> eventList) {
        this.eventList = eventList;
    }

    public static void main(String[] args) {
        PhotoConfig photoConfig = new PhotoConfig();


        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(photoConfig.getOptions(), args);

            if (line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("PhotoSorter", photoConfig.getOptions());
                return;
            }

            PhotoSorter photoSorter = new PhotoSorter(line);
            List<PhotoFile> photoFileList;
            List<PhotoFile> sortedList;

            //photoSorter.probeFiletypes();
            photoFileList = photoSorter.listSourceFiles();
            sortedList = photoSorter.sortList(photoFileList);

            LOGGER.finest(sortedList.toString());
            photoSorter.flagAllEvents(sortedList);

            photoSorter.printEventList(photoSorter.getEventList());

            //LOGGER.info(pathList.toString());


        } catch (org.apache.commons.cli.ParseException e) {
            LOGGER.severe(e.getMessage());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        } catch (ImageProcessingException e) {
            LOGGER.severe(e.getMessage());
        }


    }
}

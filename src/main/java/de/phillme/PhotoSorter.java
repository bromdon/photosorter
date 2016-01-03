package de.phillme;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoSorter {


    private Date dateOld;
    private Date eventStartDate;
    private Date eventEndDate;

    private int hoursBetweenEvents = 36;

    private String eventFileSuffix = "-NEW_EVENT";

    private String dateFormatPhotos = "";
    private String dateFormatFolders = "";


    private String splitBetweenDateAndRest = "";

    private Path photosPath = null;

    private boolean write = false;


    private List<PhotoEvent> eventList = new ArrayList<PhotoEvent>();


    // assumes the current class is called logger
    private final static Logger LOGGER = Logger.getLogger(PhotoSorter.class.getName());
    private boolean parseFromEXIF = true;

    public PhotoSorter(CommandLine commandLine) {
        LOGGER.setLevel(Level.INFO);
        this.hoursBetweenEvents = Integer.parseInt(commandLine.getOptionValue("minhours", "36"));
        this.dateFormatPhotos = commandLine.getOptionValue("dfp", "yyyy-MM-dd'T'HHmm");
        this.dateFormatFolders = commandLine.getOptionValue("dfe", "yyyy-MM-dd");
        this.splitBetweenDateAndRest = commandLine.getOptionValue("dsplitchar", "_");
        this.photosPath = Paths.get(commandLine.getOptionValue("path", "/home/caspar/filearea/bilder/2015/neu/alt"));
        System.out.println("\r\n  _____  _           _        _____            _            \r\n |  __ \\| |         | |      / ____|          | |           \r\n | |__) | |__   ___ | |_ ___| (___   ___  _ __| |_ ___ _ __ \r\n |  ___/| '_ \\ / _ \\| __/ _ \\\\___ \\ / _ \\| '__| __/ _ \\ '__|\r\n | |    | | | | (_) | || (_) |___) | (_) | |  | ||  __/ |   \r\n |_|    |_| |_|\\___/ \\__\\___/_____/ \\___/|_|   \\__\\___|_|   ");

        if (commandLine.hasOption("w")) {
            this.write = true;
            System.out.println("'-w' specified. Changes are written...");
        } else {
            System.out.println("\nPhotoSorter starting in dry-run mode. No changes written. \nIf you want to write changes add '-w' as an option.\nUse '-h' for help.\n");
        }

    }

    Date getDateFromExif(Path photo) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(photo.toFile());

        // obtain the Exif directory
        ExifSubIFDDirectory directory
                = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

// query the tag's value
        Date date
                = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL, TimeZone.getTimeZone("Europe/Berlin"));

        SimpleDateFormat sdfEurope = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdfEurope.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        String sDateinEurope = sdfEurope.format(date);
        return date;
        //System.out.println(sDateinEurope);
    }

    private void parseFile(PhotoFile photoFile, boolean lastFile) throws ImageProcessingException, IOException, ParseException {

        detectNewEvent(photoFile, this.dateOld, photoFile.getPhotoDate(), lastFile);
        this.dateOld = photoFile.getPhotoDate();

        LOGGER.finest(photoFile.getFilePath().toString());
    }

    private Date parseDateFromFileName(String fileName) throws ParseException {
        String string = "2015-01-05T2254_06470.arw";
        String[] split = fileName.split(this.splitBetweenDateAndRest);

        DateFormat format = new SimpleDateFormat(this.dateFormatPhotos);
        Date date = format.parse(split[0]);
        LOGGER.finest("DATE " + date.toString());

        return date;
    }

    private String getFileBase(String fileName) {
        String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
        //LOGGER.info(Arrays.toString(tokens));
        if (tokens.length > 0) {
            String fileBase = "";
            for (int i = 0; i < (tokens.length - 1); i++) {
                fileBase += tokens[i];
            }
            return fileBase;

        } else {
            //TODO better error handling here.
            return null;
        }
    }

    private boolean moveRelevantFiles(String targetParent, PhotoFile photoFile) throws IOException {
        //TODO is this all safe?
        List<String> list = photoFile.getSupportedFileExtensions();

        for (String ext :
                list) {
            String tmpFileBase = getFileBase(photoFile.getFilePath().getFileName().toString());

            if (tmpFileBase != null) {
                File movableFile = new File(photoFile.getFilePath().getParent().toString() + File.separator + tmpFileBase + "." + ext);
                if (movableFile.exists()) {
                    Path targetPath = Paths.get(targetParent + File.separator + movableFile.getName());
                    if (this.write) {
                        System.out.println("Moving " + targetPath);
                        Files.move((movableFile.toPath()), targetPath);
                    } else {
                        System.out.println("Would move " + targetPath);
                    }
                }
            }
        }

        return true;
    }

    public void movePhotoToEvent(PhotoFile photoFile, Date eventStartDate) throws IOException {
        SimpleDateFormat sdfEurope = new SimpleDateFormat(this.dateFormatFolders);
        sdfEurope.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
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
            LOGGER.finest("Beginning... Using DateNew for first event");
            this.eventStartDate = dateNew;
            this.dateOld = dateNew;

            movePhotoToEvent(photoFile, this.eventStartDate);
            return;
        }
        long dateDiffInHours = PhotoSorter.getDateDiff(dateOld, dateNew, TimeUnit.HOURS);

        if (dateDiffInHours > hoursBetweenEvents) {
            LOGGER.finest("Found an event between \n  " + dateOld + " and \n  " + dateNew);
            this.eventEndDate = dateOld;

            this.eventList.add(handleEvent(dateOld, dateNew));

            this.eventStartDate = dateNew;
            //after the new startDate for the event is set we can move the file to the new event folder
            movePhotoToEvent(photoFile, this.eventStartDate);
        } else {
            //move all files to current event
            movePhotoToEvent(photoFile, this.eventStartDate);

            if (lastFile) {
                //if this is the lastfile but has not enough time between, still add it
                LOGGER.finest("Found an event between \n  " + dateOld + " and \n  " + dateNew);
                this.eventEndDate = dateNew;
                this.eventList.add(handleEvent(dateOld, dateNew));
            }
        }
    }

    private PhotoEvent handleEvent(Date dateOld, Date dateNew) throws IOException {
        SimpleDateFormat sdfEurope = new SimpleDateFormat(this.dateFormatFolders);
        sdfEurope.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        String sDateinEurope = sdfEurope.format(this.eventStartDate) + this.eventFileSuffix;
        System.out.println(sDateinEurope + " would be created as an event folder.");
        File eventFile = new File(this.photosPath + File.separator + sDateinEurope);

        //eventFile.createNewFile();

        return new PhotoEvent(this.eventStartDate, this.eventEndDate);
    }

    void printEventList(List<PhotoEvent> photoEventList) {
        System.out.println("\nPrinting list of detected events...");
        int i = 1;
        for (PhotoEvent pEvent :
                photoEventList) {
            System.out.println("Event " + i + " " + pEvent.toString());
            i++;
        }
    }

    List<PhotoFile> listSourceFiles() throws IOException, ImageProcessingException, ParseException {
        List<PhotoFile> result = new ArrayList<>();
        Date date = null;
        //TODO add other raw file extensions and make them configurable
        //png,PNG,jpg,JPG,xmp,XMP
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.photosPath, "*.{arw,ARW}")) {
            for (Path entry : stream) {
                PhotoFile photoFile;
                if (this.parseFromEXIF) {

                    date = getDateFromExif(entry);
                    //TODO error handling if date is null
                    photoFile = new PhotoFile(entry, date);
                } else {
                    date = parseDateFromFileName(entry.getFileName().toString());
                    photoFile = new PhotoFile(entry, date);
                }

                result.add(photoFile);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    }

    /**
     * Get a diff between two dates
     *
     * @param date1    the oldest date
     * @param date2    the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    List<PhotoFile> sortList(List<PhotoFile> files) {
        Collections.sort(files, new Comparator<PhotoFile>() {
            public int compare(PhotoFile o1, PhotoFile o2) {
                return o1.getPhotoDate().compareTo(o2.getPhotoDate());
            }
        });
        return files;
    }

    void flagAllEvents(List<PhotoFile> sortedList) throws ImageProcessingException, IOException, ParseException {
        for (int i = 0; i < sortedList.size(); i++) {
            if (i == sortedList.size() - 1) {
                parseFile(sortedList.get(i), true);
            } else {
                parseFile(sortedList.get(i), false);
            }
        }

    }

    public List<PhotoEvent> getEventList() {
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

            photoFileList = photoSorter.listSourceFiles();
            sortedList = photoSorter.sortList(photoFileList);

            LOGGER.finest(sortedList.toString());
            photoSorter.flagAllEvents(sortedList);

            photoSorter.printEventList(photoSorter.getEventList());

            //System.out.println(pathList.toString());


        } catch (org.apache.commons.cli.ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}

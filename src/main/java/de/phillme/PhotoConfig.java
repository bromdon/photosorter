package de.phillme;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * Copyright: Lirdy UG(haftungsbeschränkt)
 * Author: Phillip Merensky
 * Date: 30.12.15
 * Time: 14:43
 */
public class PhotoConfig {

    Option help = new Option("h", "help", false, "Print this message.");
    //Option version = new Option("version", "print the version information and exit");
    //Option quiet = new Option("quiet", "be extra quiet");
    //Option verbose = new Option("verbose", "be extra verbose");
    Option write = new Option("w", "write-changes", false, "Write changes. Otherwise no changes are written! ");
    //Option parseFromExif = new Option("E", "parse-from-exif", false, "Default is true. Date is parsed from the exif data of the photo files.");

    Option minHoursBetweenEvents = new Option("minhours", true, "A new event is assumed when more than these hours have passed.");
    Option timezone = new Option("timezone", true, "The timezone of the photos and events. 'Europe/Berlin' for instance. If not specified the system timezone is assumed.");

    Option dateFormatEvents = new Option("dfe", true, "Java date format for event folders.");

    Option splitDateChar = new Option("dsplitchar", true, "This character splits the date string from the rest of the filename.");

    Option photoPath = new Option("p", "path", true, "Path to photos. If not specified the current working directory is used.");


    Options options = new Options();

    public PhotoConfig() {
        options.addOption(help);
        //options.addOption(version);
        //options.addOption(quiet);
        //options.addOption(verbose);
        options.addOption(minHoursBetweenEvents);
        options.addOption(timezone);
        options.addOption(dateFormatEvents);
        options.addOption(splitDateChar);
        options.addOption(photoPath);
        options.addOption(write);
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}

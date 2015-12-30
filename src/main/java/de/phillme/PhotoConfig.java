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

    Option help = new Option("h", "help", false, "print this message");
    //Option version = new Option("version", "print the version information and exit");
    //Option quiet = new Option("quiet", "be extra quiet");
    //Option verbose = new Option("verbose", "be extra verbose");
    Option write = new Option("w", "write-changes", false, "Write changes. Otherwise no changes are written! ");

    Option minHoursBetweenEvents = OptionBuilder.withArgName("hours")
            .hasArg()
            .withDescription("A new event is assumed when more than these hours have passed")
            .create("minhours");

    Option dateFormatPhotos = OptionBuilder.withArgName("dateformat")
            .hasArg()
            .withDescription("java date format for photo files to extract date from file name")
            .create("dfp");

    Option dateFormatEvents = OptionBuilder.withArgName("dateformat")
            .hasArg()
            .withDescription("java date format for event folders")
            .create("dfe");

    Option splitDateChar = OptionBuilder.withArgName("datesplitcharacter")
            .hasArg()
            .withDescription("this character splits the date string from the rest of the filename.")
            .create("dsplitchar");

    Option photoPath = OptionBuilder.withArgName("Absolute Path")
            .hasArg()
            .withDescription("Absolute path to photos.")
            .create("path");

    Options options = new Options();

    public PhotoConfig() {
        options.addOption(help);
        //options.addOption(version);
        //options.addOption(quiet);
        //options.addOption(verbose);
        options.addOption(minHoursBetweenEvents);
        options.addOption(dateFormatPhotos);
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

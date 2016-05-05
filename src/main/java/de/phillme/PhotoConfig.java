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

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

class PhotoConfig {

    private Option help = new Option("h", "help", false, "Print this message.");
    //Option version = new Option("version", "print the version information and exit");
    //Option quiet = new Option("quiet", "be extra quiet");
    //Option verbose = new Option("verbose", "be extra verbose");
    private Option write = new Option("w", "write-changes", false, "Write changes. Otherwise no changes are written! ");
    //Option parseFromExif = new Option("E", "parse-from-exif", false, "Default is true. Date is parsed from the exif data of the photo files.");

    private Option minHoursBetweenEvents = new Option("minhours", true, "A new event is assumed when more than these hours have passed.");
    private Option timezone = new Option("timezone", true, "The timezone of the photos and events. 'Europe/Berlin' for instance. If not specified the system timezone is assumed.");

    private Option dateFormatEvents = new Option("dfe", true, "Java date format for event folders.");

    //Option splitDateChar = new Option("dsplitchar", true, "This character splits the date string from the rest of the filename.");

    private Option photoPath = new Option("p", "path", true, "Path to photos. If not specified the current working directory is used.");


    private Options options = new Options();

    public PhotoConfig() {
        options.addOption(help);
        //options.addOption(version);
        //options.addOption(quiet);
        //options.addOption(verbose);
        options.addOption(minHoursBetweenEvents);
        options.addOption(timezone);
        options.addOption(dateFormatEvents);
        //options.addOption(splitDateChar);
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

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

import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class PhotoFile {
    private Path filePath;
    private Date photoDate;
    private List<String> supportedMetaDataFileExtensions = new ArrayList<>();

    public PhotoFile(Path filePath, Date photoDate) {
        //this.supportedMetaDataFileExtensions.add("arw.xmp");
        //this.supportedMetaDataFileExtensions.add("ARW.XMP");
        this.supportedMetaDataFileExtensions.add("xmp");
        this.supportedMetaDataFileExtensions.add("XMP");

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

    public List<String> getSupportedMetaDataFileExtensions() {
        return supportedMetaDataFileExtensions;
    }

    public void setSupportedMetaDataFileExtensions(List<String> supportedMetaDataFileExtensions) {
        this.supportedMetaDataFileExtensions = supportedMetaDataFileExtensions;
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

package de.phillme;

/**
 * Copyright: Lirdy UG(haftungsbeschränkt)
 * Author: Phillip Merensky
 * Date: 05.01.16
 * Time: 19:01
 */
public class PhotoExtension {

    public String extensionWithoutDot;
    public String mimeType;

    public PhotoExtension(String extensionWithoutDot, String mimeType) {
        this.extensionWithoutDot = extensionWithoutDot;
        this.mimeType = mimeType;
    }
}

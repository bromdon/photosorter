package de.phillme;

import java.util.Date;

/**
 * Copyright: Lirdy UG(haftungsbeschränkt)
 * Author: Phillip Merensky
 * Date: 29.12.15
 * Time: 21:48
 */
public class PhotoEvent {

    public PhotoEvent(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }



    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private Date startDate;

    private Date endDate;
    private String eventName = "";

    @Override
    public String toString() {
        return "" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                "";
    }
}

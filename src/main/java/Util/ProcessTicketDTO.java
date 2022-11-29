package Util;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProcessTicketDTO {
@JsonAlias (value = {"ticketID", })
    private int serialID;
@JsonAlias (value = {"ticketStauts", })
    private String status;

    public int getSerialID() {
        return serialID;
    }

    public void setSerialID(int serialID) {
        this.serialID = serialID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

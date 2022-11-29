package Model;

public class TicketModel {
    private String reimbursementID;
    private int serialID;

    private String requester;

    private String description;
    private String status;
    private double amount;


    public TicketModel() {

    }
    public TicketModel(String reimbursementID, int serialID, String requester,String description, String status, double amount) {
        this.reimbursementID = reimbursementID;
        this.requester = requester;
        this.description = description;
        this.status = "Pending";
        this.amount = amount;
        this.serialID =serialID;

    }
    public String getReimbursementID() {
        return reimbursementID;
    }

    public void setReimbursementID(String reimbursementID) {
        this.reimbursementID = reimbursementID;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSerialID() {
        return serialID;
    }

    public void setSerialID(int serialID) {
        this.serialID = serialID;
    }

    public String toString() {
        return "Ticket {" + "reimbursementID is: " + reimbursementID + "SerialID is: " + serialID + "description is: " + description + " amount is: " + amount + " the requestor is: " + requester + "Status is: " + status +  "}";
    }
}

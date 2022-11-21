package Model;

public class TicketModel {
    private String reimbursementID;
    private String serialID;
    private String description;
    private String status;
    private int amount;


    public TicketModel() {

    }
    public TicketModel(String reimbursementID, String serialID, String description, String status, int amount) {
        this.reimbursementID = reimbursementID;
        this.serialID = serialID;
        this.description = description;
        this.status = status;
        this.amount = amount;

    }
    public String getReimbursementID() {
        return reimbursementID;
    }

    public void setReimbursementID(String reimbursementID) {
        this.reimbursementID = reimbursementID;
    }

    public String getSerialID() {
        return serialID;
    }

    public void setSerialID(String serialID) {
        this.serialID = serialID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

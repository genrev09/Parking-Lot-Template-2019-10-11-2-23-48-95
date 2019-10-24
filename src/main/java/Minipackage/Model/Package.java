package Minipackage.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Package {
    @Id
    private int packageNumber;
    private String receiver;
    private int phoneNumber;
    private String status;
    private Date bookingTime;

    public void setPackageNum(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public int getPackageNum() {
        return packageNumber;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setPhone(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhone() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }
}

package ResortArenaPalace;

import java.time.LocalDate;
import java.util.Date;

public class GuestReservation {
    private String name;
    private String lName;
    private String noPeople;
    private String noRooms;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String roomType;
    private String password;
    private String email;
    private String chosenPackage;
    private GuestReservation roomInfo;
    private String chkOut;
    private String chkIn;
    private int num_p;
    private int num_r;

    public String getChkOut() {
        return chkOut;
    }

    public void setChkOut(String chkOut) {
        this.chkOut = chkOut;
    }

    public String getChkIn() {
        return chkIn;
    }

    public void setChkIn(String chkIn) {
        this.chkIn = chkIn;
    }

    public int getNum_p() {
        return num_p;
    }

    public void setNum_p(int num_p) {
        this.num_p = num_p;
    }

    public int getNum_r() {
        return num_r;
    }

    public void setNum_r(int num_r) {
        this.num_r = num_r;
    }

    public GuestReservation getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(GuestReservation roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNoPeople() {
        return noPeople;
    }

    public void setNoPeople(String noPeople) {
        this.noPeople = noPeople;
    }

    public String getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(String noRooms) {
        this.noRooms = noRooms;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    GuestReservation(String numP, String numR, LocalDate chkIn, LocalDate chkOut){
        noPeople = numP;
        noRooms = numR;
        checkIn = chkIn;
        checkOut = chkOut;
    }
    GuestReservation(GuestReservation roomInfo, String pckg){
        noPeople = roomInfo.getNoPeople();
        noRooms = roomInfo.getNoRooms();
        checkIn = roomInfo.getCheckIn();
        checkOut = roomInfo.getCheckOut();
        this.roomInfo = roomInfo;
        roomType = pckg;
    }
    GuestReservation(String email, String fName, String lName, int num_P, int num_R, String ckIn, String ckOut,
                     String rmT, String pW){
        password = pW;
        roomType = rmT;
        chkIn = ckIn;
        chkOut = ckOut;
        num_p = num_P;
        num_r = num_R;
      //  this.noPeople = toString(num_P);
        this.email = email;
        name = fName;
        this.lName = lName;
    }
public String toString(){
return noPeople + " " + noRooms + " "  + checkIn + " "  + checkOut;
}
//    public String toString(){
//        return noPeople + " " + noRooms + " "  + checkIn + " "  + checkOut + " " + roomType;
//    }

}

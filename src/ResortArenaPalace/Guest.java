package ResortArenaPalace;

public class Guest {

  private String email;
  private String name;
  private String lastName;
  private int noPeople;
  private int noRooms;
  private String checkIn;
  private String checkOut;
  private String roomType;
  private String password;

  // Getters and Setters methods for guest attributes

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getNoPeople() {
    return noPeople;
  }

  public void setNoPeople(int noPeople) {
    this.noPeople = noPeople;
  }

  public int getNoRooms() {
    return noRooms;
  }

  public void setNoRooms(int noRooms) {
    this.noRooms = noRooms;
  }

  public String getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(String checkIn) {
    this.checkIn = checkIn;
  }

  public String getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(String checkOut) {
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

  /***
   *  Constructor
   *
   * @param email   String that represents the email address of the guest
   * @param name    Refers to the fist name of the guest
   * @param lastName  Refers to the family name of the guest
   * @param noPeople  Number that represents how many people will reside in the hotel
   * @param noRooms   Corresponds to the umber of rooms that will be booked during the guest's stay
   * @param checkIn   Date when the guest will come in
   * @param checkOut  Date when the guest will go
   * @param roomType  Refers to the style of the room chosen during the reservation
   * @param password  A secret phrase chosen by the user; it is used for the login
   */

  public Guest(String email, String name, String lastName, int noPeople, int noRooms,
      String checkIn, String checkOut, String roomType, String password) {
    this.email = email;
    this.name = name;
    this.lastName = lastName;
    this.noPeople = noPeople;
    this.noRooms = noRooms;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.roomType = roomType;
    this.password = password;
  }

}

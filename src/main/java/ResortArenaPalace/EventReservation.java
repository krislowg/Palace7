package ResortArenaPalace;

/**
 * Object class to hold Guest information for EventReservations.
 *
 * @file EventReservation.java
 * @author Shane Broxson
 */
public class EventReservation {

  private String fName;
  private String email;
  private String event;
  private String eventDate;
  private int noPeople;
  private String venue;
  private Boolean catering;
  private Boolean dj;
  private Boolean partyPlanner;

  // Getters and Setters methods for the event reservation attributes
  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public int getNoPeople() {
    return noPeople;
  }

  public void setNoPeople(int noPeople) {
    this.noPeople = noPeople;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public Boolean getCatering() {
    return catering;
  }

  public void setCatering(Boolean catering) {
    this.catering = catering;
  }

  public Boolean getDj() {
    return dj;
  }

  public void setDj(Boolean dj) {
    this.dj = dj;
  }

  public Boolean getPartyPlanner() {
    return partyPlanner;
  }

  public void setPartyPlanner(Boolean partyPlanner) {
    this.partyPlanner = partyPlanner;
  }

  /**
   * * Constructor
   *
   * @param email String that represents the email address of the guest
   * @param fName Refers to the fist name of the guest
   * @param event Type of ceremony the guest wants to have
   * @param eventDate Time when the ceremony will have place
   * @param noPeople Number of people that will attend the ceremony
   * @param venue Place where the event will have place
   * @param catering Provision of food and drinks needed for the event
   * @param dj Music type
   * @param partyPlanner Planning of the ceremony
   * @param password A secret phrase chosen by the user; it is used for the login
   */
  EventReservation(
      String email,
      String fName,
      String event,
      String password,
      String eventDate,
      int noPeople,
      String venue,
      Boolean catering,
      Boolean dj,
      Boolean partyPlanner) {
    // super(email, password);
    this.email = email;
    this.fName = fName;
    this.event = event;
    this.eventDate = eventDate;
    this.noPeople = noPeople;
    this.venue = venue;
    this.catering = catering;
    this.dj = dj;
    this.partyPlanner = partyPlanner;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

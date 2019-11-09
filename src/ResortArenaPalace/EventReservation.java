package ResortArenaPalace;

import java.util.Date;

public class EventReservation {

  private String email;
  private String fName;
  private String event;
  private String eventDate;
  private int noPeople;
  private String venue; //apply enum?
  private String catering;
  private String dj;
  private String partyPlanner;
  private String password;

  // Getters and Setters methods for the event reservation attributes

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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

  public String getCatering() {
    return catering;
  }

  public void setCatering(String catering) {
    this.catering = catering;
  }

  public String getDj() {
    return dj;
  }

  public void setDj(String dj) {
    this.dj = dj;
  }

  public String getPartyPlanner() {
    return partyPlanner;
  }

  public void setPartyPlanner(String partyPlanner) {
    this.partyPlanner = partyPlanner;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public EventReservation(String email, String fName, String event, String eventDate, int noPeople,
      String venue, String catering, String dj, String partyPlanner, String password) {
    this.email = email;
    this.fName = fName;
    this.event = event;
    this.eventDate = eventDate;
    this.noPeople = noPeople;
    this.venue = venue;
    this.catering = catering;
    this.dj = dj;
    this.partyPlanner = partyPlanner;
    this.password = password;
  }

}

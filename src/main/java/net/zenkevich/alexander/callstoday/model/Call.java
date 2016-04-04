package net.zenkevich.alexander.callstoday.model;

import net.zenkevich.alexander.callstoday.annotation.Brackets;
import net.zenkevich.alexander.callstoday.annotation.Phone;
import net.zenkevich.alexander.callstoday.annotation.PhoneRange;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Call object
 * first name - max. length 30 characters. Optional. Any character.
 * last name - max. length 30 characters. Mandatory. Any character.
 * time - time of call creation
 * telephone number - mandatory attribute.
 */
public class Call {

  @Size(max = 30)
  private String firstName;

  @NotBlank
  @Size(max = 30)
  private String lastName;

  private Date time;


  @Phone
  @NotBlank
  @Brackets
  @PhoneRange
  private String telephone;

  public Call() {
    this.time = new Date();
  }

  public Call(String firstName, String lastName, String telephone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.time = new Date();
    this.telephone = telephone;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getTime() {
    return time;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
}

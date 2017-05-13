package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Johnson on 2017/5/13.
 */

public class StaffCert implements Serializable {

  @SerializedName("Staff")
  private Staff staff;
  @SerializedName("Type")
  private String type;
  @SerializedName("Specialty")
  private String specialty;
  @SerializedName("OrganizationName")
  private String organizationName;
  @SerializedName("CertID")
  private String certId;
  @SerializedName("SealID")
  private String sealId;
  @SerializedName("RegisterDate")
  private String registerDate;
  @SerializedName("ExpireDate")
  private String expireDate;

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getCertId() {
    return certId;
  }

  public void setCertId(String certId) {
    this.certId = certId;
  }

  public String getSealId() {
    return sealId;
  }

  public void setSealId(String sealId) {
    this.sealId = sealId;
  }

  public String getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(String registerDate) {
    this.registerDate = registerDate;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }
}

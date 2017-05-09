package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Johnson on 2017/5/2.
 */

public class Staff implements Serializable {
  @SerializedName("ID")
  private long id;
  @SerializedName("CompanyId")
  private long companyId;
  @SerializedName("Name")
  private String name;
  @SerializedName("Nation")
  private String nation;
  @SerializedName("Gender")
  private String gender;
  @SerializedName("EduBackground")
  private String eduBackground;
  @SerializedName("Degree")
  private String degree;
  @SerializedName("IdentityID")
  private String identityId;
  @SerializedName("RegisterType")
  private String registerType;
  @SerializedName("Level")
  private String level;
  @SerializedName("Specialty")
  private String specialty;
  @SerializedName("SealID")
  private String sealId;
  @SerializedName("RegisterDate")
  private String registerDate;
  @SerializedName("ExpireDate")
  private String expireDate;
  @SerializedName("SafeProductingCertID")
  private String safeProductingCertId;
  @SerializedName("Status")
  private String status;
  @SerializedName("Link")
  private String link;
  @SerializedName("Company")
  private Company company;

  public Company getCompany() {
    return company;
  }

  public long getId() {
    return id;
  }

  public long getCompanyId() {
    return companyId;
  }

  public String getName() {
    return name;
  }

  public String getNation() {
    return nation;
  }

  public String getGender() {
    return gender;
  }

  public String getEduBackground() {
    return eduBackground;
  }

  public String getDegree() {
    return degree;
  }

  public String getIdentityId() {
    return identityId;
  }

  public String getRegisterType() {
    return registerType;
  }

  public String getLevel() {
    return level;
  }

  public String getSpecialty() {
    return specialty;
  }

  public String getSealId() {
    return sealId;
  }

  public String getRegisterDate() {
    return registerDate;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public String getSafeProductingCertId() {
    return safeProductingCertId;
  }

  public String getStatus() {
    return status;
  }

  public String getLink() {
    return link;
  }
}

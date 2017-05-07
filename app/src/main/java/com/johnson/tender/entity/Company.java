package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Johnson on 2017/4/30.
 */

public class Company implements Serializable {
  @SerializedName("ID")
  private long id;
  @SerializedName("CompanyName")
  private String companyName;
  @SerializedName("CompanyCode")
  private String companyCode;
  private String businessLicenseId;
  private String registerLocation;
  private String operatingLocation;
  @SerializedName("LegalRepresentative")
  private String legalRepresentative;
  private String companyType;
  private String registeredCapital;
  private String certId;
  private String securityCertId;
  private String certExpire;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public String getBusinessLicenseId() {
    return businessLicenseId;
  }

  public void setBusinessLicenseId(String businessLicenseId) {
    this.businessLicenseId = businessLicenseId;
  }

  public String getRegisterLocation() {
    return registerLocation;
  }

  public void setRegisterLocation(String registerLocation) {
    this.registerLocation = registerLocation;
  }

  public String getOperatingLocation() {
    return operatingLocation;
  }

  public void setOperatingLocation(String operatingLocation) {
    this.operatingLocation = operatingLocation;
  }

  public String getLegalRepresentative() {
    return legalRepresentative;
  }

  public void setLegalRepresentative(String legalRepresentative) {
    this.legalRepresentative = legalRepresentative;
  }

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String getRegisteredCapital() {
    return registeredCapital;
  }

  public void setRegisteredCapital(String registeredCapital) {
    this.registeredCapital = registeredCapital;
  }

  public String getCertId() {
    return certId;
  }

  public void setCertId(String certId) {
    this.certId = certId;
  }

  public String getSecurityCertId() {
    return securityCertId;
  }

  public void setSecurityCertId(String securityCertId) {
    this.securityCertId = securityCertId;
  }

  public String getCertExpire() {
    return certExpire;
  }

  public void setCertExpire(String certExpire) {
    this.certExpire = certExpire;
  }
}

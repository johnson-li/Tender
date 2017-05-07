package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Johnson on 2017/5/2.
 */

public class Project implements Serializable {
  @SerializedName("ID")
  long id;
  @SerializedName("CompanyID")
  String companyId;
  @SerializedName("Name")
  String name;
  @SerializedName("Scale")
  String scale;
  @SerializedName("CustomerOrganization")
  String customerOrganization;
  @SerializedName("Location")
  String location;
  @SerializedName("SectionName")
  String sectionName;
  @SerializedName("ConstructionOrganization")
  String constructionOrganization;
  @SerializedName("BiddingRecord")
  String biddingRecord;
  @SerializedName("ContractRecord")
  String contractRecord;
  @SerializedName("SubContract")
  String subContract;
  @SerializedName("ProjectNumber")
  String projectNumber;
  @SerializedName("ProjectType")
  String projectType;

  public String getProjectType() {
    return projectType;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public String getCustomerOrganization() {
    return customerOrganization;
  }

  public void setCustomerOrganization(String customerOrganization) {
    this.customerOrganization = customerOrganization;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public String getConstructionOrganization() {
    return constructionOrganization;
  }

  public void setConstructionOrganization(String constructionOrganization) {
    this.constructionOrganization = constructionOrganization;
  }

  public String getBiddingRecord() {
    return biddingRecord;
  }

  public void setBiddingRecord(String biddingRecord) {
    this.biddingRecord = biddingRecord;
  }

  public String getContractRecord() {
    return contractRecord;
  }

  public void setContractRecord(String contractRecord) {
    this.contractRecord = contractRecord;
  }

  public String getSubContract() {
    return subContract;
  }

  public void setSubContract(String subContract) {
    this.subContract = subContract;
  }

  public String getProjectNumber() {
    return projectNumber;
  }

  public void setProjectNumber(String projectNumber) {
    this.projectNumber = projectNumber;
  }
}

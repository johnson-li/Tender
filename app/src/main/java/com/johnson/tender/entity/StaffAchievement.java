package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Johnson on 2017/5/13.
 */

public class StaffAchievement implements Serializable {
  @SerializedName("Staff")
  private Staff staff;
  @SerializedName("Role")
  private String role;
  @SerializedName("ProjectName")
  private String projectName;
  @SerializedName("CustomerInstitute")
  private String customerInstitute;
  @SerializedName("Status")
  private String status;
  @SerializedName("Category")
  private String category;

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getCustomerInstitute() {
    return customerInstitute;
  }

  public void setCustomerInstitute(String customerInstitute) {
    this.customerInstitute = customerInstitute;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}

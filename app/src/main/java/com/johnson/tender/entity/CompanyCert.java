package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Johnson on 2017/5/12.
 */

public class CompanyCert implements Serializable {

  @SerializedName("ID")
  private long id;
  @SerializedName("CompanyID")
  private long companyId;
  @SerializedName("Type")
  private String type;
  @SerializedName("CertID")
  private String certId;
  @SerializedName("Name")
  private String name;
  @SerializedName("IssueDate")
  private String issueDate;
  @SerializedName("ExpireDate")
  private String expireDate;
  @SerializedName("Issuer")
  private String issuer;

  public long getId() {
    return id;
  }

  public long getCompanyId() {
    return companyId;
  }

  public String getType() {
    return type;
  }

  public String getCertId() {
    return certId;
  }

  public String getName() {
    return name;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public String getIssuer() {
    return issuer;
  }
}

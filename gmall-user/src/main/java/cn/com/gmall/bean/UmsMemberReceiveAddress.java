package cn.com.gmall.bean;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UmsMemberReceiveAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long memberId;
  private String name;
  private String phoneNumber;
  private Long defaultStatus;
  private String postCode;
  private String province;
  private String city;
  private String region;
  private String detailAddress;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Long getDefaultStatus() {
    return defaultStatus;
  }

  public void setDefaultStatus(Long defaultStatus) {
    this.defaultStatus = defaultStatus;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getDetailAddress() {
    return detailAddress;
  }

  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }

  @Override
  public String toString() {
    return "UmsMemberReceiveAddress{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", defaultStatus=" + defaultStatus +
            ", postCode='" + postCode + '\'' +
            ", province='" + province + '\'' +
            ", city='" + city + '\'' +
            ", region='" + region + '\'' +
            ", detailAddress='" + detailAddress + '\'' +
            '}';
  }
}

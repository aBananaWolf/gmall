package cn.com.gmall.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class OmsOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long memberId;
    @Column
    private Long couponId;
    @Column
    private String orderSn;
    @Column
    private java.sql.Timestamp createTime;
    @Column
    private String memberUsername;
    @Column
    private java.math.BigDecimal totalAmount;
    @Column
    private java.math.BigDecimal payAmount;
    @Column
    private java.math.BigDecimal freightAmount;
    @Column
    private java.math.BigDecimal promotionAmount;
    @Column
    private java.math.BigDecimal integrationAmount;
    @Column
    private java.math.BigDecimal couponAmount;
    @Column
    private java.math.BigDecimal discountAmount;
    @Column
    private Long payType;
    @Column
    private Long sourceType;
    @Column
    private Long status;
    @Column
    private Long orderType;
    @Column
    private String deliveryCompany;
    @Column
    private String deliverySn;
    @Column
    private Long autoConfirmDay;
    @Column
    private Long integration;
    @Column
    private Long growth;
    @Column
    private String promotionInfo;
    @Column
    private Long billType;
    @Column
    private String billHeader;
    @Column
    private String billContent;
    @Column
    private String billReceiverPhone;
    @Column
    private String billReceiverEmail;
    @Column
    private String receiverName;
    @Column
    private String receiverPhone;
    @Column
    private String receiverPostCode;
    @Column
    private String receiverProvince;
    @Column
    private String receiverCity;
    @Column
    private String receiverRegion;
    @Column
    private String receiverDetailAddress;
    @Column
    private String note;
    @Column
    private Long confirmStatus;
    @Column
    private Long deleteStatus;
    @Column
    private Long useIntegration;
    @Column
    private java.sql.Timestamp paymentTime;
    @Column
    private java.sql.Timestamp deliveryTime;
    @Column
    private java.sql.Timestamp receiveTime;
    @Column
    private java.sql.Timestamp commentTime;
    @Column
    private java.sql.Timestamp modifyTime;


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


    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }


    public java.math.BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(java.math.BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    public java.math.BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(java.math.BigDecimal payAmount) {
        this.payAmount = payAmount;
    }


    public java.math.BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(java.math.BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }


    public java.math.BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(java.math.BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }


    public java.math.BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(java.math.BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }


    public java.math.BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(java.math.BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }


    public java.math.BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(java.math.BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }


    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }


    public Long getSourceType() {
        return sourceType;
    }

    public void setSourceType(Long sourceType) {
        this.sourceType = sourceType;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }


    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }


    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }


    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }


    public Long getAutoConfirmDay() {
        return autoConfirmDay;
    }

    public void setAutoConfirmDay(Long autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }


    public Long getIntegration() {
        return integration;
    }

    public void setIntegration(Long integration) {
        this.integration = integration;
    }


    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }


    public String getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }


    public Long getBillType() {
        return billType;
    }

    public void setBillType(Long billType) {
        this.billType = billType;
    }


    public String getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
    }


    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }


    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }


    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }


    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }


    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }


    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }


    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }


    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }


    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }


    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Long getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Long confirmStatus) {
        this.confirmStatus = confirmStatus;
    }


    public Long getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Long deleteStatus) {
        this.deleteStatus = deleteStatus;
    }


    public Long getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Long useIntegration) {
        this.useIntegration = useIntegration;
    }


    public java.sql.Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(java.sql.Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }


    public java.sql.Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(java.sql.Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }


    public java.sql.Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(java.sql.Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }


    public java.sql.Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(java.sql.Timestamp commentTime) {
        this.commentTime = commentTime;
    }


    public java.sql.Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(java.sql.Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

}

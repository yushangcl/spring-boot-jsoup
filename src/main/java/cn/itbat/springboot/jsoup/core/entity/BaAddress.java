package cn.itbat.springboot.jsoup.core.entity;

import java.io.Serializable;
import java.util.Date;

public class BaAddress implements Serializable {
    /**
     * 主键
     */
    private Long addressUkid;

    /**
     * 地址编码
     */
    private String addressCode;

    /**
     * 地址名称
     */
    private String addressName;

    /**
     * 父级编码
     */
    private String parentCode;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return address_ukid 主键
     */
    public Long getAddressUkid() {
        return addressUkid;
    }

    /**
     * 主键
     * @param addressUkid 主键
     */
    public void setAddressUkid(Long addressUkid) {
        this.addressUkid = addressUkid;
    }

    /**
     * 地址编码
     * @return address_code 地址编码
     */
    public String getAddressCode() {
        return addressCode;
    }

    /**
     * 地址编码
     * @param addressCode 地址编码
     */
    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
    }

    /**
     * 地址名称
     * @return address_name 地址名称
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 地址名称
     * @param addressName 地址名称
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    /**
     * 父级编码
     * @return parent_code 父级编码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 父级编码
     * @param parentCode 父级编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 等级
     * @return level 等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 等级
     * @param level 等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", addressUkid=").append(addressUkid);
        sb.append(", addressCode=").append(addressCode);
        sb.append(", addressName=").append(addressName);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", level=").append(level);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaAddress other = (BaAddress) that;
        return (this.getAddressUkid() == null ? other.getAddressUkid() == null : this.getAddressUkid().equals(other.getAddressUkid()))
            && (this.getAddressCode() == null ? other.getAddressCode() == null : this.getAddressCode().equals(other.getAddressCode()))
            && (this.getAddressName() == null ? other.getAddressName() == null : this.getAddressName().equals(other.getAddressName()))
            && (this.getParentCode() == null ? other.getParentCode() == null : this.getParentCode().equals(other.getParentCode()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAddressUkid() == null) ? 0 : getAddressUkid().hashCode());
        result = prime * result + ((getAddressCode() == null) ? 0 : getAddressCode().hashCode());
        result = prime * result + ((getAddressName() == null) ? 0 : getAddressName().hashCode());
        result = prime * result + ((getParentCode() == null) ? 0 : getParentCode().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
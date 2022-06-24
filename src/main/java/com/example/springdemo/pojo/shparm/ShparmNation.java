package com.example.springdemo.pojo.shparm;

import java.io.Serializable;

public class ShparmNation implements Serializable {
    private Integer id;

    private String nationCode;

    private String nationName;

    private String status;

    private Integer orderby;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode == null ? null : nationCode.trim();
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName == null ? null : nationName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
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
        ShparmNation other = (ShparmNation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNationCode() == null ? other.getNationCode() == null : this.getNationCode().equals(other.getNationCode()))
            && (this.getNationName() == null ? other.getNationName() == null : this.getNationName().equals(other.getNationName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOrderby() == null ? other.getOrderby() == null : this.getOrderby().equals(other.getOrderby()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNationCode() == null) ? 0 : getNationCode().hashCode());
        result = prime * result + ((getNationName() == null) ? 0 : getNationName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOrderby() == null) ? 0 : getOrderby().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nationCode=").append(nationCode);
        sb.append(", nationName=").append(nationName);
        sb.append(", status=").append(status);
        sb.append(", orderby=").append(orderby);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
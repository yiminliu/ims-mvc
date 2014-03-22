package com.bedrosians.bedlogic.domain.item;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bedrosians.bedlogic.domain.item.enums.Body;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.Icon;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude
@Entity
@Table(name = "ims_new_feature", schema = "public")
public class ImsNewFeature implements java.io.Serializable {

	private String itemcd;
	private Item item;
	private Grade grade;
	private Status status;
	private MpsCode mpsCode;
	private DesignLook designLook;
	private DesignStyle designStyle;
	private Body body;
	private Edge edge;
	private Icon icon;
	private SurfaceApplication surfaceApplication;
	private SurfaceType surfaceType;
	private SurfaceFinish surfaceFinish;
	private Integer warranty;
	private String recommendedGroutJoint;
	private Date createdDate;
	private Date launchedDate;
	private Date lastModifiedDate;
	
	public ImsNewFeature() {
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "item"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "itemcd", unique = true, nullable = false, length = 15)
	public String getItemcd() {
		return itemcd;
	}

	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@JoinColumn(name = "edge")
	@Enumerated(EnumType.STRING)
	public Edge getEdge() {
		return this.edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	@Column(name="mps_code")
	@Enumerated(EnumType.STRING)
	public MpsCode getMpsCode() {
		//return (mpsCode != null? mpsCode : ImsResultUtil.convertInactivecdToMpsCode(item.getInactivecd()));
		return mpsCode;
	}

	public void setMpsCode(MpsCode mpsCode) {
		this.mpsCode = mpsCode;
	}

	@Column(name = "body")
	@Enumerated(EnumType.STRING)
	public Body getBody() {
		return this.body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Column(name = "icon")
	@Enumerated(EnumType.STRING)
	public Icon getIcon() {
		return this.icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	@Column(name = "grade")
	@Enumerated(EnumType.STRING)
	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Column(name="design_look")
	@Enumerated(EnumType.STRING)
	public DesignLook getDesignLook() {
		return this.designLook;
	}

	public void setDesignLook(DesignLook designLook) {
		this.designLook = designLook;
	}

	@Column(name="design_style")
	@Enumerated(EnumType.STRING)
	public DesignStyle getDesignStyle() {
		return this.designStyle;
	}

	public void setDesignStyle(DesignStyle designStyle) {
		this.designStyle = designStyle;
	}
		
	@Column(name = "surface_application")
	@Enumerated(EnumType.STRING)
	public SurfaceApplication getSurfaceApplication() {
		return this.surfaceApplication;
	}

	public void setSurfaceApplication(SurfaceApplication surfaceApplication) {
		this.surfaceApplication = surfaceApplication;
	}

	@Column(name = "surface_type")
	@Enumerated(EnumType.STRING)
	public SurfaceType getSurfaceType() {
		return this.surfaceType;
	}

	public void setSurfaceType(SurfaceType surfaceType) {
		this.surfaceType = surfaceType;
	}

	@Column(name = "surface_finish")
	@Enumerated(EnumType.STRING)
	public SurfaceFinish getSurfaceFinish() {
		return this.surfaceFinish;
	}

	public void setSurfaceFinish(SurfaceFinish surfaceFinish) {
		this.surfaceFinish = surfaceFinish;
	}
	
    @Column(name="recommended_grout_joint")
	public String getRecommendedGroutJoint() {
		return recommendedGroutJoint;
	}

	public void setRecommendedGroutJoint(String recommendedGroutJoint) {
		this.recommendedGroutJoint = recommendedGroutJoint;
	}
    
	@Column(name="warranty")
	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 13)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "launched_date", length = 13)
	public Date getLaunchedDate() {
		return this.launchedDate;
	}

	public void setLaunchedDate(Date launchedDate) {
		this.launchedDate = launchedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_modified_date", updatable=false, insertable=false)
	@Generated(GenerationTime.ALWAYS)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Transient
	public boolean isEmpty(){
		return (grade == null && status == null && mpsCode == null && designLook == null && designStyle == null && body == null && 
				edge == null && icon == null && surfaceApplication == null && surfaceType == null && surfaceFinish == null);
		
	}
	
	@Transient
	static public List<String> allFields(){
		return Arrays.asList("grade", "status", "mpsCode", "designLook", "designStyle", "body", "edge", "icon", "surfaceApplication", 
				               "surfaceType" , "surfaceFinish", "recommendedGroutJoint", "createdDate", "launchedDate", "lastModifiedDate");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemcd == null) ? 0 : itemcd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImsNewFeature other = (ImsNewFeature) obj;
		if (itemcd == null) {
			if (other.itemcd != null)
				return false;
		} else if (!itemcd.equals(other.itemcd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImsNewFeature ["
				+ "itemcd=" + itemcd 
				+ ", imsEdge=" + edge
				+ ", mpsCode =" + mpsCode
				+ ", body=" + body 
				+ ", imsIcon=" + icon
				+ ", grade=" + grade 
				+ ", status=" + status
				+ ", imsSurfaceApplication=" + surfaceApplication
				+ ", imsDesignLook=" + designLook 
				+ ", imsDesignStyle=" + designStyle 
				+ ", imsSurfaceType=" + surfaceType
				+ ", imsSurfaceFinish=" + surfaceFinish 
				+ ", createdDate=" + createdDate 
				+ ", launchedDate=" + launchedDate
				+ ", lastModifiedDate=" + lastModifiedDate 
				+ "]";
	}
	
}
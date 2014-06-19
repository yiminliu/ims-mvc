
package com.bedrosians.bedlogic.domain.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.domain.item.embeddable.Description;
import com.bedrosians.bedlogic.domain.item.embeddable.Dimensions;
import com.bedrosians.bedlogic.domain.item.embeddable.Material;
import com.bedrosians.bedlogic.domain.item.embeddable.Notes;
import com.bedrosians.bedlogic.domain.item.embeddable.PackagingInfo;
import com.bedrosians.bedlogic.domain.item.embeddable.Series;
import com.bedrosians.bedlogic.domain.item.embeddable.Applications;
import com.bedrosians.bedlogic.domain.item.embeddable.Cost;
import com.bedrosians.bedlogic.domain.item.embeddable.Price;
import com.bedrosians.bedlogic.domain.item.embeddable.PriorVendor;
import com.bedrosians.bedlogic.domain.item.embeddable.Purchasers;
import com.bedrosians.bedlogic.domain.item.embeddable.SimilarItemCode;
import com.bedrosians.bedlogic.domain.item.embeddable.TestSpecification;
import com.bedrosians.bedlogic.domain.item.embeddable.Units;
import com.bedrosians.bedlogic.domain.item.embeddable.VendorInfo;
import com.bedrosians.bedlogic.util.ImsDataUtil;


@JsonRootName(value = "item")
@Component
@Entity
@Table(name = "ims", schema = "public")
@DynamicUpdate
@DynamicInsert
@Indexed
@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Item implements java.io.Serializable {

	private static final long serialVersionUID = -3213582221787L;
	
    //------Basic Info------//   		
	private String itemcode;
	private String itemcategory;
	private String countryorigin;
	private String inactivecode;
	private String shadevariation;
	private String colorcategory;
	private List<String> colorhues =  new ArrayList<>();
	private Character showonwebsite;
  	private String iconsystem;
	private Character itemtypecd;
	private String abccd;
	private String itemcd2;
	private String inventoryitemcd;
	private Character showonalysedwards;
	private Character offshade;
	private Character printlabel;
	private Character taxclass;
	private String lottype;
	private String updatecd;
	private Character directship;
	private Date dropdate;
	private String productline;
	private Integer itemgroupnbr;
	private Date priorlastupdated;	
	
	//----- Embedded Components ---------//
	private Description itemdesc;
	private Series series;
	private Material material;
	private Dimensions dimensions;
	//this default aims to meet ims_check5
	private Price price = new Price();
	private TestSpecification testSpecification;
 	private SimilarItemCode relateditemcodes;
	private Purchasers purchasers;
	private PackagingInfo packaginginfo = new PackagingInfo();
	private Notes notes;
	private Applications applications;
	private List<String> usage;
  	private Units units;
  	private Cost cost;
  	private VendorInfo vendors = new VendorInfo();
  	//private PriorVendor priorVendor;
  	
	//------- Associations --------//
  	private List<ItemVendor> newVendorSystem = new ArrayList<>();
  	private ItemNewFeature itemNewFeature;
    private IconCollection iconDescription;
	//private List<ColorHue> colorhues =  new ArrayList<>();
  	//private List<Note> newNoteSystem = new ArrayList<>();
     	
  	public Item() {
	}

	public Item(String itemcode) {
		this.itemcode = itemcode;
	}
	
	@Id
	@DocumentId
	@Column(name = "itemcd", unique = true, nullable = false, length = 18)
	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	@Embedded
	@IndexedEmbedded
	public Description getItemdesc() {
		return itemdesc;
	}

	public void setItemdesc(Description itemdesc) {
		this.itemdesc = itemdesc;
	}

	@Embedded
    @IndexedEmbedded
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
	
	@Embedded
	@IndexedEmbedded
	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}
	
	@Embedded
	@IndexedEmbedded
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@Embedded
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	
	@Embedded
	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	@Embedded	
	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	@Embedded
	public TestSpecification getTestSpecification() {
		return testSpecification;
	}

	public void setTestSpecification(TestSpecification testSpecification) {
		this.testSpecification = testSpecification;
	}

	@Embedded
	public Applications getApplications() {
		return applications;
	}

	public void setApplications(Applications applications) {
		this.applications = applications;
	}
    
	@Embedded
	public Purchasers getPurchasers() {
		return purchasers;
	}

	public void setPurchasers(Purchasers purchasers) {
		this.purchasers = purchasers;
	}
	
	@Embedded
	@IndexedEmbedded
	public VendorInfo getVendors() {
		return vendors;
	}

	public void setVendors(VendorInfo vendors) {
		this.vendors = vendors;
	}
	
	@Embedded
	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	@Embedded
	public SimilarItemCode getRelateditemcodes() {
		return relateditemcodes;
	}

	public void setRelateditemcodes(SimilarItemCode relateditemcodes) {
		this.relateditemcodes = relateditemcodes;
	}
	
	@Transient
	public PackagingInfo getPackaginginfo() {
		return packaginginfo;
	}

	public void setPackaginginfo(PackagingInfo packaginginfo) {
		this.packaginginfo = packaginginfo;
	}
	
	@Transient
	public List<String> getUsage() {
			return this.usage;
	}
	 
	public void setUsage(List<String> usage) {
		this.usage = usage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "priorlastupdated", length = 13)
	public Date getPriorlastupdated() {
		return this.priorlastupdated;
	}

	public void setPriorlastupdated(Date priorlastupdated) {
		this.priorlastupdated = priorlastupdated;
	}

	@Column(name = "abccd", length = 4)
    public String getAbccd() {
		return this.abccd;
	}

	public void setAbccd(String abccd) {
		this.abccd = abccd;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column(name = "category", length = 8)
	@Boost(1.5f)
	public String getItemcategory() {
		return this.itemcategory;
	}

	public void setItemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}

	@Column(name = "directship", length = 1)
	public Character getDirectship() {
		return this.directship;
	}

	public void setDirectship(Character directship) {
		this.directship = directship;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dropdate", length = 13)
	public Date getDropdate() {
		return this.dropdate;
	}

	public void setDropdate(Date dropdate) {
		this.dropdate = dropdate;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "inactivecd", length = 3)
	public String getInactivecode() {
		return this.inactivecode;
	}

	public void setInactivecode(String inactivecode) {
		this.inactivecode = inactivecode;
	}

	@Column(name = "itemgroupnbr", precision = 2, scale = 0)
	public Integer getItemgroupnbr() {
		return this.itemgroupnbr;
	}

	public void setItemgroupnbr(Integer itemgroupnbr) {
		this.itemgroupnbr = itemgroupnbr;
	}

	@Column(name = "itemtypecd", length = 1)
	public Character getItemtypecd() {
		return this.itemtypecd;
	}
	
	public void setItemtypecd(Character itemtypecd) {
		this.itemtypecd = itemtypecd;
	}

	@Column(name = "lottype", length = 1)
	public String getLottype() {
		return this.lottype;
	}

	public void setLottype(String lottype) {
		this.lottype = lottype;
	}

	@Column(name = "printlabel", length = 1)
	public Character getPrintlabel() {
		return this.printlabel;
	}

	public void setPrintlabel(Character printlabel) {
		this.printlabel = printlabel;
	}

	@Column(name = "productline", length = 8)
	public String getProductline() {
		return this.productline;
	}

	public void setProductline(String productline) {
		this.productline = productline;
	}

	@Column(name = "updatecd", length = 10)
	public String getUpdatecd() {
		return this.updatecd;
	}

	public void setUpdatecd(String updatecd) {
		this.updatecd = updatecd;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(1.8f)
	@Column(name = "origin", length = 18)
	public String getCountryorigin() {
		return this.countryorigin;
	}

	public void setCountryorigin(String countryorigin) {
		this.countryorigin = countryorigin;
	}

	@Column(name = "shadevariation", length = 2)
	public String getShadevariation() {
		return this.shadevariation;
	}

	public void setShadevariation(String shadevariation) {
		this.shadevariation = shadevariation;
	}

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "showonwebsite", length = 1)
	public Character getShowonwebsite() {
		return this.showonwebsite;
	}

	public void setShowonwebsite(Character showonwebsite) {
		this.showonwebsite = showonwebsite;
	}

	//@JsonIgnore
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Boost(2.0f)
    @Column(name = "colorcategory", length = 30)
	public String getColorcategory() {
		return this.colorcategory;
	}

	public void setColorcategory(String colorcategory) {
		this.colorcategory = colorcategory;
	}
	
	@Transient
	public List<String> getColorhues() {
		if(colorhues == null || colorhues.isEmpty())
		   colorhues = ImsDataUtil.convertColorCategoryToStringList(colorcategory);
		return colorhues;
	}
		
	public void setColorhues(List<String> colorhues) {
		this.colorhues = colorhues;
	}
	
	@Column(name = "showonalysedwards", length = 1)
	public Character getShowonalysedwards() {
		return this.showonalysedwards;
	}

	public void setShowonalysedwards(Character showonalysedwards) {
		this.showonalysedwards = showonalysedwards;
	}

	@Column(name = "offshade", length = 1)
	public Character getOffshade() {
		return this.offshade;
	}

	public void setOffshade(Character offshade) {
		this.offshade = offshade;
	}

	@Column(name = "itemcd2", length = 18)
	public String getItemcd2() {
		return this.itemcd2;
	}

	public void setItemcd2(String itemcd2) {
		this.itemcd2 = itemcd2;
	}

	@Column(name = "icons", length = 20)
	public String getIconsystem() {
		return this.iconsystem;
	}

	public void setIconsystem(String iconsystem) {
		this.iconsystem = iconsystem;
	}

	@Column(name = "inventory_itemcd", length = 18)
	public String getInventoryitemcd() {
		return this.inventoryitemcd;
	}

	public void setInventoryitemcd(String inventoryitemcd) {
		this.inventoryitemcd = inventoryitemcd;
	}

	@Column(name = "itemtaxclass")
	public Character getTaxclass() {
		return this.taxclass;
	}

	public void setTaxclass(Character taxclass) {
		this.taxclass = taxclass;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@IndexedEmbedded
	public ItemNewFeature getImsNewFeature() {	
	    return this.itemNewFeature;
	}

	public void setImsNewFeature(ItemNewFeature itemNewFeature) {
		this.itemNewFeature = itemNewFeature;
	}
	
	public void addImsNewFeature(ItemNewFeature itemNewFeature ){
		if(this.getImsNewFeature() != null)
			setImsNewFeature(null);
		itemNewFeature.setItem(this);
		this.itemNewFeature = itemNewFeature;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public IconCollection getIconDescription() {	
	    return iconDescription;// != null//? iconCollection : ImsDataUtil.parseIcons(getIconsystem());
	}

	public void setIconDescription(IconCollection iconDescription) {
		this.iconDescription = iconDescription;
	}
	
	public void addIconDescription(IconCollection iconDescription){
		iconDescription.setItem(this);
		this.iconDescription = iconDescription;
	}

	//@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@IndexedEmbedded
	public List<ItemVendor> getNewVendorSystem() {
		return this.newVendorSystem;
	}

	public void setNewVendorSystem(List<ItemVendor> newVendorSystem) {
		this.newVendorSystem = newVendorSystem;
	}

	public void addNewVendorSystem(ItemVendor vendor){
		if(vendor.getItemVendorId().getItemCode() == null)
		   vendor.getItemVendorId().setItemCode(this.getItemcode());	
		vendor.setItem(this);
		if(getNewVendorSystem() == null)
		   setNewVendorSystem(new ArrayList<ItemVendor>());	
		getNewVendorSystem().add(vendor);
	}
	
	public void initVendors(int numberOfVendors){
		for(int i = 0; i < numberOfVendors; i++) {
			addNewVendorSystem(new ItemVendor());
		}
	}

	/*	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
	//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	//@Fetch(FetchMode.SUBSELECT)
	public List<ColorHue> getColorhues() {
		return this.colorhues;
	}

	public void setColorhues(List<ColorHue> colorhues) {
		this.colorhues = colorhues;
	}
   
	public void addColorhue(ColorHue colorhue){
		colorhue.setItem(this);
		getColorhues().add(colorhue);
	}

	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
	public List<Note> getNewNoteSystem() {
		return this.newNoteSystem;
	}

	public void setNewNoteSystem(List<Note> newNoteSystem) {
		this.newNoteSystem = newNoteSystem;
	}

	public void addNote(Note note){
		if(getNewNoteSystem() != null && getNewNoteSystem().size() < 5)
			initNotes(5);
		note.setItem(this);
		String noteType = note.getNoteType();
		switch(noteType){
		   case "po": case "po_note":
			   getNewNoteSystem().set(0, note);
			   break;
		   case "buyer": case "buyer_note":
	    	   getNewNoteSystem().set(1, note);
			   break;	
		   case "invoice": case "invoice_note":
			   getNewNoteSystem().set(2, note);
			   break;
		   case "internal": case "internal_note":
			   getNewNoteSystem().set(4, note);
			   break;	
		   case "additional": case "additional_note":
			   getNewNoteSystem().set(3, note);
			   break;	   
		}
	}

	public void initNotes(int numberOfNotes){
		newNoteSystem = Arrays.asList(new Note[numberOfNotes]);
		for(int i = 0; i < numberOfNotes; i++) {
			Note note = new Note();
			note.setItem(this);
			newNoteSystem.set(i, note);
		}
	}	
	*/
    @JsonIgnore
    @Transient
	public String getStandardSellUnit(){
    	return ImsDataUtil.getStandardSellUnit(this);
	}
    
	@JsonIgnore
	@Transient
	public String getStandardOrderUnit(){
		return ImsDataUtil.getStandardPurchaseUnit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemcode == null) ? 0 : itemcode.trim().hashCode());
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
		Item other = (Item) obj;
		if (itemcode == null) {
			if (other.itemcode != null)
				return false;
		} else if (!itemcode.trim().equalsIgnoreCase(other.itemcode.trim()))
			return false;
		return true;
	}

	public Item(String itemcode, String itemcategory, String countryorigin,
			String inactivecode, String shadevariation, String colorcategory,
			Character showonwebsite, String iconsystem, String type,
			Character itemtypecd, String abccd, String itemcd2,
			String inventoryitemcd, Character showonalysedwards,
			Character offshade, Character printlabel, Character taxclass,
			String lottype, String updatecd, String subtype,
			Character directship, Date dropdate, String productline,
			Integer itemgroupnbr, Date priorlastupdated, Description itemdesc,
			Series series, Material material, Dimensions dimensions,
			Price price, VendorInfo vendors, List<String> usage,
			TestSpecification testSpecification,
			SimilarItemCode relateditemcodes, Purchasers purchasers,
			PackagingInfo packaginginfo, Notes notes,
			Applications applications, Units units, Cost cost,
			PriorVendor priorVendor, ItemNewFeature itemNewFeature,
			IconCollection iconDescription){
			//, List<ColorHue> colorhues){
			//Set<ItemVendor> newVendorSystem, List<Note> newNoteSystem) {
		super();
		this.itemcode = itemcode;
		this.itemcategory = itemcategory;
		this.countryorigin = countryorigin;
		this.inactivecode = inactivecode;
		this.shadevariation = shadevariation;
		this.colorcategory = colorcategory;
		this.showonwebsite = showonwebsite;
		this.iconsystem = iconsystem;
		//this.type = type;
		this.itemtypecd = itemtypecd;
		this.abccd = abccd;
		this.itemcd2 = itemcd2;
		this.inventoryitemcd = inventoryitemcd;
		this.showonalysedwards = showonalysedwards;
		this.offshade = offshade;
		this.printlabel = printlabel;
		this.taxclass = taxclass;
		this.lottype = lottype;
		this.updatecd = updatecd;
		//this.subtype = subtype;
		this.directship = directship;
		this.dropdate = dropdate;
		this.productline = productline;
		this.itemgroupnbr = itemgroupnbr;
		this.priorlastupdated = priorlastupdated;
		this.itemdesc = itemdesc;
		this.series = series;
		this.material = material;
		this.dimensions = dimensions;
		this.price = price;
		this.vendors = vendors;
		this.usage = usage;
		this.testSpecification = testSpecification;
		this.relateditemcodes = relateditemcodes;
		this.purchasers = purchasers;
		this.packaginginfo = packaginginfo;
		this.notes = notes;
		this.applications = applications;
		this.units = units;
		this.cost = cost;
		//this.priorVendor = priorVendor;
		this.itemNewFeature = itemNewFeature;
		this.iconDescription = iconDescription;
		this.newVendorSystem = newVendorSystem;
		//this.colorhues = colorhues;
		//this.newNoteSystem = newNoteSystem;
	}

	@Override
	public String toString() {
		return "Item "
				+ "[itemcd=" + itemcode 
				+ ", itemNewFeature=" + itemNewFeature 
				+ ", abccd=" + abccd 
				+ ", category=" + itemcategory 
				+ ", inactivecd=" + inactivecode
				+ ", itemgroupnbr=" + itemgroupnbr 
				+ ", itemtypecd=" + itemtypecd 
				+ ", printlabel=" + printlabel 
				+ ", productline=" + productline 
				+ ", updatecd=" + updatecd 
				+ ", origin=" + countryorigin
				+ ", shadevariation=" + shadevariation 
				+ ", showonwebsite=" + showonwebsite
				+ ", colorcategory=" + colorcategory 
				+ ", showonalysedwards=" + showonalysedwards 
				+ ", offshade=" + offshade 
				+ ", itemcd2=" + itemcd2 
				+ ", icons=" + iconsystem 
				+ ", inventoryItemcd=" + inventoryitemcd 
				+ "]";
	}

	//----- not used fields -----//
		/*
        //private String type;
        //private String subtype;
	    //private String application;
		//private String specialhandlecd1;
		//private String specialhandlecd2;
		//private String specialhandlecd3;
		//private String typealf;
		
		//private Integer samplenbr; = select max(samplrnbr) from ims
		//private String waterAbsorptionSign;
		//private String scofWetSign;
		//private String scofDrySign;
	    //private String qualitychoice;

		private Character price;
		private BigDecimal calcsellprice;
		private BigDecimal calclistprice;
		private BigDecimal listprice1;
		private BigDecimal listprice2;
		private BigDecimal listprice3;
		private BigDecimal listprice4;
		private BigDecimal listprice5;
		private BigDecimal sellprice1;
		private BigDecimal sellprice2;
		private BigDecimal sellprice3;
		private BigDecimal sellprice4;
		private BigDecimal sellprice5;
		private BigDecimal priorlistprice1;
		private BigDecimal priorlistprice2;
		private BigDecimal priorlistprice3;
		private BigDecimal priorlistprice4;
		private BigDecimal priorlistprice5;
		private BigDecimal priorsellprice1;
		private BigDecimal priorsellprice2;
		private BigDecimal priorsellprice3;
		private BigDecimal priorsellprice4;
		private BigDecimal priorsellprice5;
		private BigDecimal futurelist;
		private BigDecimal futurelist1;
		private BigDecimal futuresell1;
		private Float priorvendordiscpct2;
		private Float priorvendordiscpct3;
		private String knownAliases1;
		private String knownAliases2;
		private String knownAliases3;
		private String knownAliases4;
		private String knownAliases5;
		private String knownAliases6;
		private String knownAliases7;
		*/

	    //@Column(name = "color", length = 30)
		//public String getColor() {
		//	return this.color);
		//}

		//public void setColor(String color) {
		//	this.color = color;
		//}

	//@Column(name = "application", length = 20)
    //public String getApplication() {
	//	return this.application);
	//}

	//@Column(name = "type", length = 16)
	//public String getType() {
	//	return this.type;
	//}

	//public void setType(String type) {
	//	this.type = type;
	//}

	//@Column(name = "subtype", length = 32)
	//public String getSubtype() {
	//	return this.subtype;
	//}
	
	 //public void setSubtype(String subtype) {
		//	this.subtype = subtype;
		//}

	//public void setApplication(String application) {
	//	this.application = application;
	//}
	
	//@Column(name = "samplenbr", precision = 8, scale = 0)
	//public Integer getSamplenbr() {
	//	return this.samplenbr;
	//}

	//public void setSamplenbr(Integer samplenbr) {
	//	this.samplenbr = samplenbr;
	//}

	//@Column(name = "water_absorption_sign", length = 2)
	//public String getWaterAbsorptionSign() {
	//	return this.waterAbsorptionSign;
	//}

	//public void setWaterAbsorptionSign(String waterAbsorptionSign) {
	//	this.waterAbsorptionSign = waterAbsorptionSign;
	//}

	//@Column(name = "scof_wet_sign", length = 2)
	//public String getScofWetSign() {
	//	return this.scofWetSign;
	//}

	//public void setScofWetSign(String scofWetSign) {
	//	this.scofWetSign = scofWetSign;
	//}

	//@Column(name = "scof_dry_sign", length = 2)
	//public String getScofDrySign() {
	//	return this.scofDrySign;
	//}

	//public void setScofDrySign(String scofDrySign) {
	//	this.scofDrySign = scofDrySign;
	//}

	//@Column(name = "itemtaxclass", length = 1)
		//public Character getItemtaxclass() {
		//	return this.itemtaxclass;
		//}

		//public void setItemtaxclass(Character itemtaxclass) {
		//	this.itemtaxclass = itemtaxclass;
		//}
	//@Column(name = "specialhandlecd1", length = 10)
		//public String getSpecialhandlecd1() {
		//	return this.specialhandlecd1;
		//}

		//public void setSpecialhandlecd1(String specialhandlecd1) {
		//	this.specialhandlecd1 = specialhandlecd1;
		//}

		//@Column(name = "specialhandlecd2", length = 10)
		//public String getSpecialhandlecd2() {
		//	return this.specialhandlecd2;
		//}

		//public void setSpecialhandlecd2(String specialhandlecd2) {
		//	this.specialhandlecd2 = specialhandlecd2;
		//}

		//@Column(name = "specialhandlecd3", length = 10)
	    //public String getSpecialhandlecd3() {
		//	return this.specialhandlecd3;
		//}

		//public void setSpecialhandlecd3(String specialhandlecd3) {
		//	this.specialhandlecd3 = specialhandlecd3;
		//}

		
		//@Column(name = "typealf", length = 8)
		//public String getTypealf() {
		//	return this.typealf;
		//}

		//public void setTypealf(String typealf) {
		//	this.typealf = typealf;
		//}
	
	//@Column(name = "price", length = 1)
		//public Character getPrice() {
		//	return this.price;
		//}

		//public void setPrice(Character price) {
		//	this.price = price;
		//}

		//@Column(name = "qualitychoice", length = 12)
		//public String getQualitychoice() {
		//	return this.qualitychoice;
		//}

		//public void setQualitychoice(String qualitychoice) {
		//	this.qualitychoice = qualitychoice;
		//}
	
	//@Column(name = "calcsellprice", precision = 9, scale = 4)
		//public BigDecimal getCalcsellprice() {
		//	return this.calcsellprice;
		//}
	//@Column(name = "listprice1", precision = 9, scale = 4)
		//public BigDecimal getListprice1() {
		//	return this.listprice1;
		//}

		//public void setListprice1(BigDecimal listprice1) {
		//	this.listprice1 = listprice1;
		//}

		//@Column(name = "listprice2", precision = 9, scale = 4)
		//public BigDecimal getListprice2() {
		//	return this.listprice2;
		//}

		//public void setListprice2(BigDecimal listprice2) {
		//	this.listprice2 = listprice2;
		//}

		//@Column(name = "listprice3", precision = 9, scale = 4)
		//public BigDecimal getListprice3() {
		//	return this.listprice3;
		//}

		//public void setListprice3(BigDecimal listprice3) {
		//	this.listprice3 = listprice3;
		//}

		//@Column(name = "listprice4", precision = 9, scale = 4)
		//public BigDecimal getListprice4() {
		//	return this.listprice4;
		//}

		//public void setListprice4(BigDecimal listprice4) {
		//	this.listprice4 = listprice4;
		//}

		//@Column(name = "listprice5", precision = 9, scale = 4)
		//public BigDecimal getListprice5() {
		//	return this.listprice5;
		//}

		//public void setListprice5(BigDecimal listprice5) {
		//	this.listprice5 = listprice5;
		//}

		//@Column(name = "sellprice1", precision = 9, scale = 4)
		//public BigDecimal getSellprice1() {
		//	return this.sellprice1;
		//}

		//public void setSellprice1(BigDecimal sellprice1) {
		//	this.sellprice1 = sellprice1;
		//}

		//@Column(name = "sellprice2", precision = 9, scale = 4)
		//public BigDecimal getSellprice2() {
		//	return this.sellprice2;
		//}

		//public void setSellprice2(BigDecimal sellprice2) {
		//	this.sellprice2 = sellprice2;
		//}

		//@Column(name = "sellprice3", precision = 9, scale = 4)
		//public BigDecimal getSellprice3() {
		//	return this.sellprice3;
		//}

		//public void setSellprice3(BigDecimal sellprice3) {
		//	this.sellprice3 = sellprice3;
		//}

		//@Column(name = "sellprice4", precision = 9, scale = 4)
		//public BigDecimal getSellprice4() {
		//	return this.sellprice4;
		//}

		//public void setSellprice4(BigDecimal sellprice4) {
		//	this.sellprice4 = sellprice4;
		//}

		///@Column(name = "sellprice5", precision = 9, scale = 4)
		//public BigDecimal getSellprice5() {
		//	return this.sellprice5;
		//}

		//public void setSellprice5(BigDecimal sellprice5) {
		//	this.sellprice5 = sellprice5;
		//}

		//@Column(name = "priorlistprice1", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice1() {
		//	return this.priorlistprice1;
		//}

		//public void setPriorlistprice1(BigDecimal priorlistprice1) {
//			this.priorlistprice1 = priorlistprice1;
		//}

		//@Column(name = "priorlistprice2", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice2() {
		//	return this.priorlistprice2;
		//}

		//public void setPriorlistprice2(BigDecimal priorlistprice2) {
		//	this.priorlistprice2 = priorlistprice2;
		//}

		//@Column(name = "priorlistprice3", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice3() {
		//	return this.priorlistprice3;
		//}

		//public void setPriorlistprice3(BigDecimal priorlistprice3) {
		//	this.priorlistprice3 = priorlistprice3;
		//}

		//@Column(name = "priorlistprice4", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice4() {
		//	return this.priorlistprice4;
		//}

		//public void setPriorlistprice4(BigDecimal priorlistprice4) {
		//	this.priorlistprice4 = priorlistprice4;
		//}

		//@Column(name = "priorlistprice5", precision = 9, scale = 4)
		//public BigDecimal getPriorlistprice5() {
		//	return this.priorlistprice5;
		//}

		//public void setPriorlistprice5(BigDecimal priorlistprice5) {
		//	this.priorlistprice5 = priorlistprice5;
		//}

		//@Column(name = "priorsellprice1", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice1() {
		//	return this.priorsellprice1;
		//}

		//public void setPriorsellprice1(BigDecimal priorsellprice1) {
		//	this.priorsellprice1 = priorsellprice1;
		//}

		//@Column(name = "priorsellprice2", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice2() {
		//	return this.priorsellprice2;
		//}

		//public void setPriorsellprice2(BigDecimal priorsellprice2) {
		//	this.priorsellprice2 = priorsellprice2;
		//}

		//@Column(name = "priorsellprice3", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice3() {
		//	return this.priorsellprice3;
		//}

		//public void setPriorsellprice3(BigDecimal priorsellprice3) {
		//	this.priorsellprice3 = priorsellprice3;
		//}

		//@Column(name = "priorsellprice4", precision = 9, scale = 4)
		//public BigDecimal getPriorsellprice4() {
		//	return this.priorsellprice4;
		//}

		//public void setPriorsellprice4(BigDecimal priorsellprice4) {
		//	this.priorsellprice4 = priorsellprice4;
		//}

		//@Column(name = "priorsellprice5", precision = 9, scale = 4)
		///public BigDecimal getPriorsellprice5() {
		//	return this.priorsellprice5;
		//}

		//public void setPriorsellprice5(BigDecimal priorsellprice5) {
		//	this.priorsellprice5 = priorsellprice5;
		//}

		//@Column(name = "futurelist", precision = 9, scale = 4)
		//public BigDecimal getFuturelist() {
		//	return this.futurelist;
		//}

		//public void setFuturelist(BigDecimal futurelist) {
//			this.futurelist = futurelist;
		//}

		//@Column(name = "futurelist1", precision = 9, scale = 4)
		//public BigDecimal getFuturelist1() {
		//	return this.futurelist1;
		//}

		//public void setFuturelist1(BigDecimal futurelist1) {
		//	this.futurelist1 = futurelist1;
		//}

		//@Column(name = "futuresell1", precision = 9, scale = 4)
		//public BigDecimal getFuturesell1() {
		//	return this.futuresell1;
		//}

		//public void setFuturesell1(BigDecimal futuresell1) {
		//	this.futuresell1 = futuresell1;
		//}
		//public void setCalcsellprice(BigDecimal calcsellprice) {
		//	this.calcsellprice = calcsellprice;
		//}

		//@Column(name = "calclistprice", precision = 9, scale = 4)
		//public BigDecimal getCalclistprice() {
		//	return this.calclistprice;
		//}

		//public void setCalclistprice(BigDecimal calclistprice) {
		//	this.calclistprice = calclistprice;
		//}

		
	//@Column(name = "known_aliases1", length = 30)
		//public String getKnownAliases1() {
		//	return this.knownAliases1;
		//}

		//public void setKnownAliases1(String knownAliases1) {
		//	this.knownAliases1 = knownAliases1;
		//}

		//@Column(name = "known_aliases2", length = 30)
		//public String getKnownAliases2() {
		//	return this.knownAliases2;
		//}

		//public void setKnownAliases2(String knownAliases2) {
		//	this.knownAliases2 = knownAliases2;
		//}

		//@Column(name = "known_aliases3", length = 30)
		//public String getKnownAliases3() {
		//	return this.knownAliases3;
		//}

		//public void setKnownAliases3(String knownAliases3) {
		//	this.knownAliases3 = knownAliases3;
		//}

		//@Column(name = "known_aliases4", length = 30)
		//public String getKnownAliases4() {
		//	return this.knownAliases4;
		//}

		//public void setKnownAliases4(String knownAliases4) {
		//	this.knownAliases4 = knownAliases4;
		//}

		//@Column(name = "known_aliases5", length = 30)
		//public String getKnownAliases5() {
		//	return this.knownAliases5;
		//}

		//public void setKnownAliases5(String knownAliases5) {
		//	this.knownAliases5 = knownAliases5;
		//}

		//@Column(name = "known_aliases6", length = 30)
		//public String getKnownAliases6() {
		//	return this.knownAliases6;
		//}

		//public void setKnownAliases6(String knownAliases6) {
		//	this.knownAliases6 = knownAliases6;
		//}

		//@Column(name = "known_aliases7", length = 30)
		//public String getKnownAliases7() {
		//	return this.knownAliases7;
		//}

		//public void setKnownAliases7(String knownAliases7) {
		//	this.knownAliases7 = knownAliases7;
		//}
			
	    
}

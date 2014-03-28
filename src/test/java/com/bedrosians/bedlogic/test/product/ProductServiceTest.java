package com.bedrosians.bedlogic.test.product;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import net.minidev.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.ProductService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/bedlogic-context.xml", "/bedlogic-persistence.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	ProductService productService;
	
	
	private static String testItemId = null;
	private static String testDescription = null;
	private static String testDescription2 = null;
	private static String testFullDescription = null;
	private static String testColor = null;
    private static String testSeriesName = null;
    private static String testUpdateCode = null;
    private static String testItemTypeCode = null;
    private static String testCategory = null;
	
    static private String testUserType = "guest";
    static private String testUserCode = "";
    static private String itemcode = "AECBUB217NR";
    static private String testSeriesname = "builder";
 
	
	@Before
	public void setup(){
		testItemId = /*"TRVMDBGSLAB2FH";*/ "TCRBRE33B"; 
		testDescription = "13X13 Breccia Beige";
		testDescription2 = "Field Tile";
	    testFullDescription = "Field Tile 13x13 Breccia Beige";
	    testColor = "Beige";
	    testSeriesName = "Breccia";
	    testUpdateCode = "CERA-TCR";
	    testItemTypeCode = "#";
	    testCategory = "BRECCIA";
	}
	
	
	@Test
	public void testGetItemById(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		Item item = productService.getProductById("TEST7");
		System.out.println("Item = " + item);
		assertNotNull("should not be null", item);
		String name = item.getClass().getName();
		assertEquals("Item id should be " + testItemId, testItemId, item.getItemcd());
		//assertEquals("Item name for Item id = 26818 is STONE AGE TILE", "STONE AGE TILE", Item.getItemName());
	}
	
	@Test
	public void testGetItemByIdPatternMatch(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.put("itemcd", Arrays.asList(new String[]{"TCRM"}));
        //params.put("exactMatch", Arrays.asList(new String[]{"false"}));
        List<Item> Items = productService.getByQueryParameters(params);
	       
        System.out.println("number of Items retrieved: "+Items.size());
        for(Item prod : Items){
        	 System.out.println("Item id  = " + prod.getItemcd());
        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
        }
       
       // assertNotNull(id);
        //System.out.println("Test Result = " + result.toJSONString());
		//assertEquals("Item name for Item id = 26818 is STONE AGE TILE", "STONE AGE TILE", Item.getItemName());
	}
	

	 @Test
	 @Transactional
	    public void testGetItemByMultivaluedMap() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("ItemId", Arrays.asList(new String[]{"Test7"}));
	       
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			//params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			//params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			//params.put("ItemManager", Arrays.asList(new String[]{"TestManager"}));
			//params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
  	        //params.put("length", Arrays.asList(new String[]{"4"}));
		    //params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("origin", Arrays.asList(new String[]{"China"}));
		//		params.put("grade", Arrays.asList(new String[]{"First"}));
				
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> Items = productService.getByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+Items.size());
	        for(Item prod : Items){
	        	 System.out.println("item code = " + prod.getItemcd());
	        	 System.out.println("color = " + prod.getColor());
	        	 System.out.println("category = " + prod.getCategory());
	        	 System.out.println("origin = " + prod.getOrigin());
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	        }
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	
	 @Test
	 @Transactional
	    public void testGetItemBySize() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put("ItemId", Arrays.asList(new String[]{"Test7"}));
	       
	       // params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("size", Arrays.asList(new String[]{"13X13", "10X10"}));
		    List<Item> Items = productService.getByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+Items.size());
	        for(Item prod : Items){
	        	 System.out.println("length X width = " + prod.getNmLength() + " X " + prod.getNmWidth());
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	        }
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 @Test
	 @Transactional
	    public void testGetItemByLengthAndWidth() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put("ItemId", Arrays.asList(new String[]{"Test7"}));
	       
	       // params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("length", Arrays.asList(new String[]{"13"}));
	        params.put("width", Arrays.asList(new String[]{"13"}));
		    List<Item> Items = productService.getByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+Items.size());
	        for(Item prod : Items){
	        	 System.out.println("length X width = " + prod.getLength() + " X " + prod.getWidth());
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	        }
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 @Test
	 @Transactional
	    public void testGetItemByMinLength() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmin", Arrays.asList(new String[]{"50"}));
		
	        List<Item> Items = productService.getByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+Items.size());
	        for(Item prod : Items){
	        	 System.out.println("length X width = " + prod.getNmLength() + " X " + prod.getNmWidth());
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	        }
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	
	 
	 @Test
	 @Transactional
	    public void testGetItemByMultivaluedMapWithOneMultiValues() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{"TEST2"}));
	       
	       // params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        //params.put("color", Arrays.asList(new String[]{testColor, "red"}));
			//params.put("category", Arrays.asList(new String[]{testCategory}));
			//params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			//params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			//params.put("ItemManager", Arrays.asList(new String[]{"TestManager"}));
			//params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
		//		params.put("length", Arrays.asList(new String[]{"4"}));
		//		params.put("width", Arrays.asList(new String[]{"4"}));
			//	params.put("origin", Arrays.asList(new String[]{"China"}));
		//		params.put("grade", Arrays.asList(new String[]{"First"}));
				
					
		
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+items.size());
	        for(Item prod : items){
	        	 System.out.println("item = " + prod);
	        //	 System.out.println("item new feature = " + prod.getImsNewFeature());
	        }
	       
            //Map<String, List<Item>> map = new HashMap<String, List<Item>>();
            //map.put("items", items);
            //JSONObject  jsonObject = new JSONObject(map);

            //Products result = new Products(jsonObject);

            ObjectMapper mapper = new ObjectMapper();
            Writer strWriter = new StringWriter();
            mapper.writeValue(strWriter, items);
            String jsonStr = strWriter.toString();
            
            
            // Return json reponse
            //String     jsonStr = result.toJSONString();
	        
	        System.out.println("number of Items retrieved: "+items.size());
	        System.out.println("Output json string = " + jsonStr);
	        
	        //for(Item prod : items){
	        //	System.out.println("item new feature = " + prod.getImsNewFeature());
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	       //}
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	
	 @Test
	 @Transactional
	    public void testGetItemByNewFeature() throws Exception {
	        System.out.println("testGetItemBynewFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       	params.put("grade", Arrays.asList(new String[]{"First"}));
	    	params.put("status", Arrays.asList(new String[]{"Good"}));
				
					
		
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+items.size());
	        for(Item prod : items){
	        	 System.out.println("item = " + prod);
	        	 System.out.println("item new feature = " + prod.getImsNewFeature());
	        }
	       
            ObjectMapper mapper = new ObjectMapper();
            Writer strWriter = new StringWriter();
            mapper.writeValue(strWriter, items);
            String jsonStr = strWriter.toString();
            
            
            // Return json reponse
            //String     jsonStr = result.toJSONString();
	        
	        System.out.println("number of Items retrieved: "+items.size());
	        System.out.println("Output json string = " + jsonStr);
	        
	      	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	   
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	
	 
	
	 @Test
		//@Transactional
		public void testCreateItem(){
			System.out.println("test create Item ...");
			Item item = new Item();
			item.setItemcd("TEST1");
			item.setDescription("test2");
	        productService.createProduct(item);
			//System.out.println("Retrieved the upated Item");
			//System.out.println("Item = "+ item.toString());
			//assertNotEquals("CA", Item.getState());
			
		}
	 
	
	 @Test
	 @Transactional
	 public void testCreateItemByMultivaluedMap() throws Exception {
	        System.out.println("testCreateItem: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{"TEST8"}));
	        params.put("description", Arrays.asList(new String[]{"This is a test"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			params.put("seriesname", Arrays.asList(new String[]{"test"}));
			params.put("type", Arrays.asList(new String[]{"test"}));
			params.put("itemtypecd", Arrays.asList(new String[]{"F"}));
		    params.put("origin", Arrays.asList(new String[]{"China"}));
			params.put("inactivecd", Arrays.asList(new String[]{"N"}));
			params.put("showonwebsite", Arrays.asList(new String[]{"Y"}));
			
			
			params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			params.put("productManager", Arrays.asList(new String[]{"Manager"}));
			params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
			
			params.put("grade", Arrays.asList(new String[]{"First"}));//Grade.FIRST.getDescription()}));
			params.put("status", Arrays.asList(new String[]{"Good"}));	
			params.put("mpsCode", Arrays.asList(new String[]{"Drop"}));//Grade.FIRST.getDescription()}));
			params.put("designStyle", Arrays.asList(new String[]{"Modern"}));
			params.put("designLook", Arrays.asList(new String[]{"WOOD"}));
			params.put("body", Arrays.asList(new String[]{"Red Body"}));
			params.put("edge", Arrays.asList(new String[]{"Tumbled"}));
			params.put("icon", Arrays.asList(new String[]{"ADA_Accessibility"}));
			params.put("surfaceApplication", Arrays.asList(new String[]{"Silk"}));
			params.put("surfaceType", Arrays.asList(new String[]{"Cross Cut"}));
			params.put("surfaceFinish", Arrays.asList(new String[]{"Antiquated"}));
			
			//----- dimension ------//
			params.put("length", Arrays.asList(new String[]{"14"}));
			params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("thickness", Arrays.asList(new String[]{"2 1/2"}));
			params.put("nmLength", Arrays.asList(new String[]{"14.0"}));
			params.put("nmWidth", Arrays.asList(new String[]{"4.0"}));
			params.put("nmThickness", Arrays.asList(new String[]{"2.5"}));
			params.put("sizeunits", Arrays.asList(new String[]{"E"}));
			params.put("thicknessunit", Arrays.asList(new String[]{"E"}));
			
			//----- material info -------//
			params.put("mattype", Arrays.asList(new String[]{"test"}));
			params.put("matcategory", Arrays.asList(new String[]{"test"}));
			params.put("matstyle", Arrays.asList(new String[]{"test"}));
			params.put("mfeature", Arrays.asList(new String[]{"test"}));
			params.put("materialclassCd", Arrays.asList(new String[]{"test"}));
			
			
			//------- price info --------//	
			params.put("price", Arrays.asList(new String[]{"5.5"}));
			params.put("futurePrice", Arrays.asList(new String[]{"4.0"}));
			params.put("priorPrice", Arrays.asList(new String[]{"5.5"}));
			params.put("tempPrice", Arrays.asList(new String[]{"4.0"}));
			params.put("tempdatefrom", Arrays.asList(new String[]{new Date().toString()}));
			params.put("tempdatefrom", Arrays.asList(new String[]{(new Date()).toString()}));
			params.put("listpricemarginpct", Arrays.asList(new String[]{"0.3"}));
			params.put("pricemarginpct", Arrays.asList(new String[]{"0.435"}));
			params.put("priceroundaccuracy", Arrays.asList(new String[]{"2"}));
			
			//------- test info -------//
			params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			params.put("scratchResistance", Arrays.asList(new String[]{"0.05"}));
			params.put("frostResistance", Arrays.asList(new String[]{"Y"}));
			params.put("chemicalResistance", Arrays.asList(new String[]{"Y"}));
			params.put("peiAbrasion", Arrays.asList(new String[]{"0.05"}));
			params.put("scofWet", Arrays.asList(new String[]{"0.05"}));
			params.put("breakingStrength", Arrays.asList(new String[]{"5"}));
			params.put("scofDry", Arrays.asList(new String[]{"0.05"}));
	
			//------ vendors ------//
			params.put("vendornbr1", Arrays.asList(new String[]{"800004"}));
			params.put("vendorxrefcd", Arrays.asList(new String[]{"43535ff"}));
			params.put("vendorpriceunit", Arrays.asList(new String[]{"PCS"}));
			params.put("vendorfob", Arrays.asList(new String[]{"test"}));
			params.put("vendorlistprice", Arrays.asList(new String[]{"45"}));
			
			params.put("vendorlistprice", Arrays.asList(new String[]{"0.3"}));
			params.put("vendordiscpct1", Arrays.asList(new String[]{"0.435"}));
			params.put("vendorroundaccuracy", Arrays.asList(new String[]{"2"}));
			params.put("vendornetprice", Arrays.asList(new String[]{"45"}));
			
			
			params.put("vendormarkuppct", Arrays.asList(new String[]{"2"}));
			params.put("vendorfreightratecwt", Arrays.asList(new String[]{"45"}));
			
			params.put("vendorlandedbasecost", Arrays.asList(new String[]{"0.3"}));
			params.put("dutypct", Arrays.asList(new String[]{"0.435"}));
			params.put("leadtime", Arrays.asList(new String[]{"2"}));
			
			params.put("v2_vendorId", Arrays.asList(new String[]{"800005"}));
			params.put("v2_vendorxrefcd", Arrays.asList(new String[]{"43535ff"}));
			params.put("v2_vendorpriceunit", Arrays.asList(new String[]{"PCS"}));
			params.put("v2_vendorfob", Arrays.asList(new String[]{"test"}));
			params.put("v2_vendorlistprice", Arrays.asList(new String[]{"45"}));
			
			params.put("v2_vendorlistprice", Arrays.asList(new String[]{"0.3"}));
			params.put("v2_vendordiscpct1", Arrays.asList(new String[]{"0.435"}));
			params.put("v2_vendorroundaccuracy", Arrays.asList(new String[]{"2"}));
			params.put("v2_vendornetprice", Arrays.asList(new String[]{"45"}));
			
			
			params.put("v2_vendormarkuppct", Arrays.asList(new String[]{"2"}));
			params.put("v2_vendorfreightratecwt", Arrays.asList(new String[]{"45"}));
			
			params.put("v2_vendorlandedbasecost", Arrays.asList(new String[]{"0.3"}));
			params.put("v2_dutypct", Arrays.asList(new String[]{"0.435"}));
			params.put("v2_leadtime", Arrays.asList(new String[]{"2"}));
		
			//--------- units ----------//
			params.put("stdunit", Arrays.asList(new String[]{"PCS"}));
			params.put("stdratio", Arrays.asList(new String[]{"1.0"}));
			params.put("ordratio", Arrays.asList(new String[]{"1.0"}));
			
			params.put("baseunit", Arrays.asList(new String[]{"PCS"}));
			params.put("baseisstdsell", Arrays.asList(new String[]{"Y"}));
			params.put("baseisstdord", Arrays.asList(new String[]{"N"}));
			params.put("baseisfractqty", Arrays.asList(new String[]{"N"}));
			params.put("baseispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("basevolperunit", Arrays.asList(new String[]{"765"}));
			params.put("basewgtperunit", Arrays.asList(new String[]{"115"}));
			params.put("baseupc", Arrays.asList(new String[]{"878775"}));
	/*		
			params.put("unit1unit", Arrays.asList(new String[]{"CTN"}));
			params.put("unit1ratio", Arrays.asList(new String[]{"10"}));
			params.put("unit1isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit1isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit1isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit1ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit1upc", Arrays.asList(new String[]{"8787750"}));
			params.put("unit1wgtperunit", Arrays.asList(new String[]{"1150"}));
			
			params.put("unit2unit", Arrays.asList(new String[]{"CTN"}));
			params.put("unit2ratio", Arrays.asList(new String[]{"100"}));
			params.put("unit2isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit2isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit2isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit2ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit2upc", Arrays.asList(new String[]{"878775"}));
			params.put("unit2wgtperunit", Arrays.asList(new String[]{"115"}));
			
			params.put("unit3unit", Arrays.asList(new String[]{"CTN"}));
			params.put("unit3ratio", Arrays.asList(new String[]{"100"}));
			params.put("unit3isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit3isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit3isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit3ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit3upc", Arrays.asList(new String[]{"878775"}));
			params.put("unit3wgtperunit", Arrays.asList(new String[]{"115"}));
			
			params.put("unit4unit", Arrays.asList(new String[]{"CTN"}));
			params.put("unit4ratio", Arrays.asList(new String[]{"100"}));
			params.put("unit4isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit4isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit4isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit4ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit4upc", Arrays.asList(new String[]{"878775"}));
			params.put("unit4wgtperunit", Arrays.asList(new String[]{"115"}));
			
		*/	
			
			//params.put("designLook", Arrays.asList(new String[]{"Wood"}));
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        String id = productService.createProduct(params);
	       
	        //assertNotNull(id);
	       // System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	   
	 
	 @Test
		@Transactional
		public void testUpdateItem(){
			System.out.println("test update Item ...");
			Item item = (Item)productService.getProductById(testItemId);
			System.out.println("An existing Item retrieved");
			System.out.println("Item = "+ item.toString());
			//assertEquals("CA", Item.getState());
			//System.out.printf("Now, set the state to %s, %s", testState, " And save it to DB...");
	        //item.setItemcd("Test");
			item.setDescription("update-test3");
			
	        productService.updateProduct(item);
			//item = (Item)productService.getProductById(item.getItemcd());
			System.out.println("Retrieved the upated Item");
			System.out.println("Item = "+ item.toString());
			//assertNotEquals("CA", Item.getState());
			
		}
		
	 
	 @Test
	 @Transactional
	    public void testUpdateItemByMultivaluedMap() throws Exception {
	        System.out.println("testUpdateItem: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{"TEST8"}));
	        params.put("description", Arrays.asList(new String[]{"test desc"}));
	        params.put("price", Arrays.asList(new String[]{"20.2"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			params.put("productManager", Arrays.asList(new String[]{"Manager"}));
			params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
			params.put("length", Arrays.asList(new String[]{"4"}));
			params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("origin", Arrays.asList(new String[]{"China"}));
			params.put("grade", Arrays.asList(new String[]{"Third"}));
			params.put("status", Arrays.asList(new String[]{"Best"}));
				
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        productService.updateProduct(params);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	   
	
	
}
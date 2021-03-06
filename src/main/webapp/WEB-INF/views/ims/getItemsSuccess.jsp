<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
  <title>Item Management System -- Get Item Success</title>
</head>
<body>
   <%@ include file="/WEB-INF/includes/header.jsp"%>
   <div class="home-container">
      <c:choose>
         <c:when test="${empty itemList}">
             <div class="page_title">No Item Found!</div>
         </c:when>
         <c:otherwise>
           <div class="page_title_no_top">Search Result(${fn:length(itemList)} items)</div>
          </c:otherwise>
      </c:choose>
      <div>
                  <c:if test="${!empty itemList}">
                    <table class="datatable">
                       <tr style="background-color: Ivory;">
                         <th>Item Code</th>
                         <th>Item Description</th>
                         <th>Category</th>
                         <th>Seriess Name</th>
                         <!--<th>Series Color</th>-->
                         <th>Color Hues</th>
                         <th>Active Status</th>
                         <th>Origin</th>
                         <th>MSRP</th>
                         <th>Price Unit</th>
                         <!--<th>Shade Variation</th>-->
                         <th>MPS</th>
                         <th>Grade</th>
                         <th>Status</th>
                         <th>Prod Manager</th>
                         <th>Buyer</th>
                         <!--<th>Inventory Item Code</th>-->
                         <th>Edit</th>
                         <th>Clone</th>
                       </tr>
                       <c:forEach var="item" items="${itemList}" varStatus="loopStatus">  
                       <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
                          <td style="color : red"><a id="itemDetail" href="<spring:url value="/ims/getItemDetail/${item.itemcode}" />">${item.itemcode}</a></td>
                          <td>${item.itemdesc.itemdesc1}</td>
                          <td>${item.itemcategory}</td>
                          <td>${item.series.seriesname}</td>
                          <!--<td>${item.series.seriescolor}</td>-->
                          <td>${item.colorHueString}</td>
                          <c:choose>
                             <c:when test="${item.inactivecode == 'N'}">
                                <td><c:out value="Active"/></td>
                             </c:when>
                             <c:otherwise>
                                <td><c:out value="Inative"/></td>
                             </c:otherwise>
                         </c:choose>
                         <td>${item.countryorigin}</td>
                         <td>${item.price.sellprice}</td>
                         <td>${item.units.stdunit}</td>
                         <!--<td>${item.shadevariation}</td>-->
                         <td>${item.newFeature.mpsCode}</td>
                         <td>${item.newFeature.grade}</td>
                         <td>${item.newFeature.status}</td>
                         <td>${item.purchasers.purchaser}</td>
                         <td>${item.purchasers.purchaser2}</td>
                          <!--<td>${item.inventoryitemcode}</td>-->
                          <td><a id="modifyItem" href="<spring:url value="/ims/updateItem_begin/${item.itemcode}" />"><span>Edit</span></a></td>
                          <td><a id="cloneItem" href="<spring:url value="/ims/cloneItem/${item.itemcode}" />" ><span>Clone</span></a></td>
         
                          <!-- <td><a id="deleteItem" href="<spring:url value="/ims/deleteItem/${item.itemcode}" />" class="button-m"><span>Delete</span></a></td>-->
                       </tr>
                     </c:forEach> 
                   </table> 
                </c:if>
             </div>
               
             <table  class="center" style="margin: 0 auto; cellspacing: 30px; cellpadding: 30px; border-spacing: 50px;">
                <tr>
                   <td><a id="imsSearch" href="<spring:url value="/ims/getItem" />" class="button-m"><span style="cellspacing: 30px; cellpadding: 30px;">Item Search Page</span></a></td>
                   <td><a id="imsHome" href="<spring:url value="/ims/index" />" class="button-m"><span style="cellspacing: 30px; cellpadding: 30px;">Ims Home</span></a></td>
                </tr>
             </table>
        <%@ include file="/WEB-INF/includes/footer.jsp"%>
   </div> <!-- Close container -->
</body>
</html>
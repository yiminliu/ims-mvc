<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<%@ include file="/WEB-INF/includes/popups.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Show Item</title>
 <style type="text/css"> 
     table.category { 
        border-bottom: dotted 1px blue;
     }
     .container {
        color:#0076BF;
        margin: 30px auto; 
        padding: 30px;
        border-spacing: 10px;
        empty-cells:show;
        width:90%;
        text-align:left;
      } 
    </style>
</head>
<body>

<div class="container">
<div id="main-content">
<div class="span-18 colborder">
<h3 style="margin-bottom: 10px; padding-bottom: 0px; color: GREEN">Enter Item Search Criteria</h3>

<spring:url var="action" value="/ims/getItems" />
<form:form method="POST" action="${action}" modelAttribute="item">

   <table>
    <tr>
       <td>Item Code: <form:input path="itemcode"></form:input></td>
        <!--<td><form:errors path="itemcode" cssStyle="color: #ff0000;"/></td>-->
    </tr>
    <tr>
        <td>Item Description: <form:input path="itemdesc.itemdesc1"></form:input></td>
    </tr>
    <tr>
       <td>Category: <form:input path="itemcategory"></form:input></td>
    </tr>
    <tr>
        <td>Series Name: <form:input path="series.seriesname"></form:input></td>
    </tr>
    <tr>  
         <td><label for="materialType">Material Type:</label>
              <c:forEach var="materialType" items="${materialTypeList}" varStatus="status">
                 <form:checkbox path="material.materialtype" value="${materialType}"/>${materialType.getDescription()}
              </c:forEach>
         </td> 
       </tr>
       <tr>    
         <td><label for="materialCategory">Material Category:</label>
              <c:forEach var="materialCategory" items="${materialCategoryList}" varStatus="status">
                 <form:checkbox path="material.materialcategory" value="${materialCategory}"/>${materialCategory.getDescription()}
              </c:forEach>
         </td>   
       </tr>
       <tr>  
         <td><label for="materialClass">Material Class:</label>
              <c:forEach var="materialClass" items="${materialClassList}" varStatus="status">
                 <form:checkbox path="material.materialclass" value="${materialClass}"/>${materialClass.getDescription()}
              </c:forEach>
         </td>
       </tr>
       <tr>   
         <td><label for="materialStyle">Material Style:</label>
              <c:forEach var="materialStyle" items="${materialStyleList}" varStatus="status">
                 <form:checkbox path="material.materialstyle" value="${materialStyle}"/>${materialStyle.getDescription()}
              </c:forEach>
         </td> 
       </tr>
       <tr>   
        <td><label for="MPSOptions">MPS: </label>
              <c:forEach var="mps" items="${mpsList}" varStatus="status">
                 <form:checkbox path="newFeature.mpsCode"  value="${mps}"/>${mps}
              </c:forEach>
        </td>
      </tr>
      <tr> 
        <td><label for="itemStatusOptions">Status: </label>
              <c:forEach var="itemStatus" items="${statusList}" varStatus="status">
                 <form:checkbox path="newFeature.status" value="${itemStatus}"/>${itemStatus}
              </c:forEach>
        </td>
      </tr>
      <tr>
         <td><label for="gradeOptions">Grade: </label>
              <c:forEach var="grade" items="${gradeList}" varStatus="status">
                 <form:checkbox path="newFeature.grade" value="${grade}"/>${grade}
              </c:forEach>
         </td> 
      </tr>
      <tr>  
       <td><label for="colorOptions">Colors Category: </label>
           <span style="padding-left: 5px;padding-bottom:3px; font-size: 12px;">
            <c:forEach var="colorHue" items="${colorList}" varStatus="status">
                 <form:checkbox path="colorhues" value="${colorHue}" />${colorHue}
            </c:forEach>
            </span>                  
        </td> 
     </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>
package com.bedrosians.bedlogic.bedDataAccessDAO;

import javax.ws.rs.core.MultivaluedMap;
import net.minidev.json.JSONObject;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.ProductPromos;

public class ProductPromosDAO
{
    public ProductPromosDAO()
    {
    }
    
    public ProductPromos readProductPromosByQueryParams(String userType, String userCode, MultivaluedMap<String,String> queryParams)
        throws BedDAOException
    {        
        JSONRPCDAO  rpcDAO = JSONRPCDAO.Create();
        
        rpcDAO.setMethodName("readProductPromos");
        rpcDAO.setMethodResultType("productpromos");
        rpcDAO.addStringParameter(userType);
        rpcDAO.addStringParameter(userCode);
        rpcDAO.addQueryParameters(queryParams);
        
        JSONObject  result = rpcDAO.call();
        
        return new ProductPromos(result);
    }
}

package com.vail.foodcourt.dto;

import java.util.HashMap;


public class GrubMeta {
    
    private HashMap<String, GrubPropertiesBean> grubPropertiesBeanMap;

    
    public HashMap<String, GrubPropertiesBean> getGrubPropertiesBeanMap() {
        return grubPropertiesBeanMap;
    }

    
    public void setGrubPropertiesBeanMap(HashMap<String, GrubPropertiesBean> grubPropertiesBeanMap) {
        this.grubPropertiesBeanMap = grubPropertiesBeanMap;
    }

}

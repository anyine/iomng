package cn.huijin.vms.util;

import java.util.ArrayList;
import java.util.List;

import sylarlove.advance.model.main.Organization;

public  class OrgUtils {
	public static List<Long> getAllIds(Organization org){
		List<Long> ids=new ArrayList<Long>();
		ids.add(org.getId());
		List<Organization> children=org.getChildren();
		if(children!=null&&!children.isEmpty()){
			for(Organization o:children){
				ids.addAll(getAllIds(o));
			}
		}
		return ids;
	}
}

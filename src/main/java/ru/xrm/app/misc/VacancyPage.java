package ru.xrm.app.misc;

import java.lang.reflect.Field;

public class VacancyPage {

	protected String href;
	
	public VacancyPage(){
		
	}
	
	public VacancyPage(String href){
		this.href=href;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public boolean equals(Object obj) {

		if (this==obj){
			return true;
		}
		
		if (obj==null || !(this.getClass() != obj.getClass())){
			return false;
		}
		VacancyPage vp=(VacancyPage)obj;
		return this.href.equals(vp.getHref());
	}
	
}

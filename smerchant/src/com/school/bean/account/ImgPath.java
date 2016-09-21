package com.school.bean.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.school.common.IDEntity;

@Entity
@Table(name="t_img")
public class ImgPath extends IDEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldName;
	private String newName;
	private String pathName;
	private String ext;
	
	public ImgPath(String oldName, String pathName, String ext,String newName) {
		super();
		this.oldName = oldName;
		this.pathName = pathName;
		this.ext = ext;
		this.newName = newName;
		setCreateTime(new Date());
		setUpdateTime(new Date());
	}
	public ImgPath() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

package com.kingwag.tinyworld.view.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺信息
 */
public class Store
implements Serializable{
	protected String Id;
	protected String name;
	protected boolean isChoosed;
	private boolean isEdtor;

	public boolean isEdtor() {
		return isEdtor;
	}

	public void setEdtor(boolean edtor) {
		isEdtor = edtor;
	}

	//后加的子项集合
	private List<Goods> goodses;


	public Store(String id, String name) {
		Id = id;
		this.name = name;
	}

	public Store(List<Goods> goodses, String id, String name) {
		this.goodses = goodses;
		Id = id;
		this.name = name;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}

	//后加的子项集合get,set方法


	public List<Goods> getGoodses() {
		return goodses;
	}

	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}
}

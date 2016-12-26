package com.kingwag.tinyworld.view.bean;

import java.util.List;

/**
 * 店铺信息
 */
public class StoreInfo
{
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
	private List<GoodsInfo> goodsInfos;


	public StoreInfo(String id, String name) {
		Id = id;
		this.name = name;
	}

	public StoreInfo(List<GoodsInfo> goodsInfos, String id, String name) {
		this.goodsInfos = goodsInfos;
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


	public List<GoodsInfo> getGoodsInfos() {
		return goodsInfos;
	}

	public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
		this.goodsInfos = goodsInfos;
	}
}

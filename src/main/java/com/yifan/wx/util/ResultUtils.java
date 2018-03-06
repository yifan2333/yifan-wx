package com.yifan.wx.util;

import com.yifan.wx.enums.HttpStatus;
import org.springframework.ui.ModelMap;

public class ResultUtils {

	/** 返回状态码 */
	private static final String CODE = "resultCode";
	/** 返回消息 */
	private static final String MESSAGE = "message";
	/** 返回数据 */
	private static final String DATA = "data";

	/**
	 * {"resultCode":200, "message":"提示消息..."}
	 * {"resultCode":200, "message":"提示消息...", "data":{"infoKey":"..."}}
	 */
	
	
	/**
	 * 返回消息
	 * @param status
	 * @return
	 */
	public static ModelMap makeModel(HttpStatus status) {
		return makeModel(status, "", null);
	}

	/**
	 * 消息及状态码
	 * @param status
	 * @param msg
	 * @return
	 */
	public static ModelMap makeModel(HttpStatus status, String msg) {
		return makeModel(status, msg, null);
	}

	/**
	 * 状态码及数据
	 * @param status
	 * @param data
	 * @return
	 */
	public static ModelMap makeModel(HttpStatus status, Object data) {
		return makeModel(status, "", data);
	}

	/**
	 * 返回完整数据 包含数据及状态码、消息
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	public static ModelMap makeModel(HttpStatus status, String msg, Object data) {
		ModelMap model = new ModelMap();
		
		model.addAttribute(CODE, status.getCode());
		model.addAttribute(MESSAGE, msg);
		if (data != null) {
			model.addAttribute(DATA, data);
		}

		return model;
	}

	/**
	 *
	 * @param status status
	 * @param msg msg
	 * @return org.springframework.ui.ModelMap
	 * @author wuyifan
	 * @since 2017年12月13日
	 */
	public static ModelMap makeModel(int status, String msg) {
		return makeModel(status, msg, null);
	}

	/**
	 *
	 * @param status status
	 * @param msg msg
	 * @param data data
	 * @return org.springframework.ui.ModelMap
	 * @author wuyifan
	 * @since 2017年12月13日
	 */
	public static ModelMap makeModel(int status, String msg, Object data) {
		ModelMap model = new ModelMap();

		model.addAttribute(CODE, status);
		model.addAttribute(MESSAGE, msg);
		if (data != null) {
			model.addAttribute(DATA, data);
		}
		return model;
	}
}

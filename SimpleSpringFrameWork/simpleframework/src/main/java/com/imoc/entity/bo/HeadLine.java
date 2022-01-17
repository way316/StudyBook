package com.imoc.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class HeadLine {
	private Long lineId;
	private String lineName;
	private String lineLink;
	private String lineImg;
	private Integer priority;
	private Integer enableStatus;
	private Date createTime;
	private Date lastEditTime;
}

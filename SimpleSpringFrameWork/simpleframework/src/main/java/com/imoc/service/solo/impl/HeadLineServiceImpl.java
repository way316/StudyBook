package com.imoc.service.solo.impl;

import com.imoc.entity.bo.HeadLine;
import com.imoc.entity.dto.Result;
import com.imoc.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Service;

import java.util.List;
@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Override
	public Result<Boolean> addHeadLine(HeadLine headLine) {
		return null;
	}

	@Override
	public Result<Boolean> removeHeadLine(int headLineId) {
		return null;
	}

	@Override
	public Result<Boolean> modifyHeadLine(HeadLine headLine) {
		return null;
	}

	@Override
	public Result<HeadLine> queryHeadLineById(int headLineId) {
		return null;
	}

	@Override
	public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
		return null;
	}
}

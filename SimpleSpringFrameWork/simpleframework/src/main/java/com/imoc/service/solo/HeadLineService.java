package com.imoc.service.solo;

import com.imoc.entity.bo.HeadLine;
import com.imoc.entity.dto.Result;

import java.util.List;

public interface HeadLineService {
	Result<Boolean> addHeadLine(HeadLine headLine);
	Result<Boolean> removeHeadLine(int headLineId);
	Result<Boolean> modifyHeadLine(HeadLine headLine);
	Result<HeadLine> queryHeadLineById(int headLineId);
	Result<List<HeadLine>>queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}

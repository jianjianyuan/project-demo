package cn.project.demo.module.log.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.project.demo.framework.web.log.dto.OperateLogCreateReqDTO;
import cn.project.demo.module.log.dao.SystemOperateLogMapper;
import cn.project.demo.module.log.entity.SystemOperateLog;
import cn.project.demo.module.log.service.SystemOperateLogService;
import cn.project.demo.module.util.converter.log.SystemOperateLogConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@Slf4j
@Service
public class SystemOperateLogServiceImpl implements SystemOperateLogService {
	@Resource
	private SystemOperateLogMapper systemOperateLogMapper;

	@Async
	@Override
	public Future<Boolean> createOperateLogAsync(OperateLogCreateReqDTO reqVO) {
		boolean success = false;
		try {
			SystemOperateLog systemOperateLog = SystemOperateLogConvert.INSTANCE.convert(reqVO);
			systemOperateLog.setJavaMethodArgs(StrUtil.maxLength(systemOperateLog.getJavaMethodArgs(), 8000)); //超过8000用...代替
			systemOperateLog.setResultData(StrUtil.maxLength(systemOperateLog.getResultData(), 8000));
			success = systemOperateLogMapper.insert(systemOperateLog) == 1;
		} catch (Throwable throwable) {
			// 仅仅打印日志，不对外抛出。原因是，还是要保留现场数据。
			log.error("[createOperateLogAsync][记录操作日志异常，日志为 ({})]", reqVO, throwable);
		}
		return new AsyncResult<>(success);
	}
}
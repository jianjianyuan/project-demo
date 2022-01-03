package cn.project.demo.module.user.controller;

import cn.project.demo.framework.common.pojo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试")
@RestController
@RequestMapping("/")
@Validated
@Slf4j
public class EchoController {

	@ApiOperation("回声测试")
	@GetMapping("/echo/{payload}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "payload", value = "消息", dataTypeClass = String.class, paramType = "path")
	})
	public CommonResult<String> echoTest(@PathVariable(value = "payload", required = false) String payload) {
		return CommonResult.success(payload);
	}

	@ApiOperation("操作日志测试")
	@PostMapping("/operate-log-test")
	public Object operateLogTest(@RequestBody Object postParams) {
		return CommonResult.success(postParams);
	}
}
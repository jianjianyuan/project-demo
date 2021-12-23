package cn.project.demo.module.user.controller;

import cn.project.demo.framework.common.pojo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping("/")
@Validated
@Slf4j
public class EchoController {

    @ApiOperation("回声测试")
    @GetMapping("/echo/{payload}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payload", value = "消息", dataTypeClass = String.class)
    })
    public CommonResult<String> socialAuthRedirect(@PathVariable(value = "payload", required = false) String payload) {
        return CommonResult.success(payload);
    }
}
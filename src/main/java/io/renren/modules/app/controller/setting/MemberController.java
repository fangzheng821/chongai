package io.renren.modules.app.controller.setting;


import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.entity.setting.Member;
import io.renren.modules.app.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * APP测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/app/member")
@Api(tags = "用户信息接口")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Login
    @GetMapping("/Info")
    @ApiOperation("获取用户信息")
    public R memberInfo(@RequestParam("memberId") Long memberId){
        Member member = memberService.selectById(memberId);
        return R.ok().put("member", member);
    }

    @Login
    @GetMapping("/userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }

}

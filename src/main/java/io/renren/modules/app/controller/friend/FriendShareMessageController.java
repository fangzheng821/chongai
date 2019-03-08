package io.renren.modules.app.controller.friend;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.dto.WXSession;
import io.renren.modules.app.entity.friend.FriendShareMessageEntity;
import io.renren.modules.app.form.FriendShareMessageForm;
import io.renren.modules.app.service.FriendShareMessageService;
import io.renren.modules.app.service.impl.WXRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * @author xukaijun
 * @email 383635738@qq.com
 * @date 2019-03-02 09:38:43
 */
@RestController
@RequestMapping("app/friendShareMessage")
@Api(tags="朋友圈分享内容")
public class FriendShareMessageController {
    private final static Logger logger = LoggerFactory.getLogger(FriendShareMessageController.class);

    @Autowired
    private FriendShareMessageService friendShareMessageService;


    /**
     * 列表
     */
    @PostMapping("/owner/list")
    @ApiOperation("获取发布内容列表")
    @ApiImplicitParam(name="params",value = "分页page从1开始，sidx和order为排序字段非必填",example = "{\"page\":1,\"limit\":10,\"sidx\":\"id\",\"order\":\"asc\"}")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendShareMessageService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation("获取发布内容详细内容")
    public R info(Long id){
        FriendShareMessageEntity friendShareMessage = friendShareMessageService.selectById(id);
        return R.ok().put("data", friendShareMessage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存发布内容")
    public R save(@RequestBody FriendShareMessageForm friendShareMessage){
        FriendShareMessageEntity friendShareMessageEntity = new FriendShareMessageEntity();
        BeanUtils.copyProperties(friendShareMessage,friendShareMessageEntity);
        friendShareMessageEntity.setCreateTime(System.currentTimeMillis());
        friendShareMessageService.insertMsgAndTimeline(friendShareMessageEntity);
        return R.ok();
    }


    /**
     * 删除
     */
    @GetMapping("/delete")
    @ApiOperation("删除朋友圈内容")
    public R delete(Long id){
        friendShareMessageService.deleteMsgAndTimeline(id);
        return R.ok();
    }

}

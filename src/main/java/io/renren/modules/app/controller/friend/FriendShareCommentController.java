package io.renren.modules.app.controller.friend;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.app.entity.friend.FriendShareCommentEntity;
import io.renren.modules.app.service.FriendShareCommentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 评论
 * @author xukaijun
 * @email 383635738@qq.com
 * @date 2019-03-02 09:38:43
 */
@RestController
@RequestMapping("app/comment")
public class FriendShareCommentController {
    @Autowired
    private FriendShareCommentService friendShareCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendShareCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("app:friendsharecomment:info")
    public R info(@PathVariable("id") Long id){
			FriendShareCommentEntity friendShareComment = friendShareCommentService.selectById(id);

        return R.ok().put("friendShareComment", friendShareComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("app:friendsharecomment:save")
    public R save(@RequestBody FriendShareCommentEntity friendShareComment){
			friendShareCommentService.insert(friendShareComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:friendsharecomment:update")
    public R update(@RequestBody FriendShareCommentEntity friendShareComment){
			friendShareCommentService.updateById(friendShareComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:friendsharecomment:delete")
    public R delete(@RequestBody Long[] ids){
			friendShareCommentService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}

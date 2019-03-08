package io.renren.modules.app.service.impl;

import io.renren.modules.app.entity.friend.FriendShareCommentEntity;
import io.renren.modules.app.entity.friend.FriendShareTimelineEntity;
import io.renren.modules.app.entity.friend.SubscribeEntity;
import io.renren.modules.app.entity.setting.MemberFriend;
import io.renren.modules.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.friend.FriendShareMessageDao;
import io.renren.modules.app.entity.friend.FriendShareMessageEntity;


@Service("friendShareMessageService")
public class FriendShareMessageServiceImpl extends ServiceImpl<FriendShareMessageDao, FriendShareMessageEntity> implements FriendShareMessageService {

    @Autowired
    private FriendShareTimelineService friendShareTimelineService;

    @Autowired
    private FriendShareCommentService friendShareCommentService;

    @Autowired
    private SubscribeService subscribeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FriendShareMessageEntity> page = this.selectPage(
                new Query<FriendShareMessageEntity>(params).getPage(),
                new EntityWrapper<FriendShareMessageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void insertMsgAndTimeline(FriendShareMessageEntity friendShareMessageEntity) {
        insert(friendShareMessageEntity);
        //加入时间轴
        FriendShareTimelineEntity timeLine = new FriendShareTimelineEntity();
        timeLine.setFsmId(friendShareMessageEntity.getId());
        timeLine.setIsOwn(1);
        timeLine.setMemberId(friendShareMessageEntity.getMemberId());
        timeLine.setCreateTime(System.currentTimeMillis());
        friendShareTimelineService.insert(timeLine);

        //查询当前用户的好友,并将msg存入好友的时间轴里
        List<SubscribeEntity> subList = subscribeService.selectList(new EntityWrapper<SubscribeEntity>().eq("member_id", friendShareMessageEntity.getMemberId()));
        for(SubscribeEntity sub : subList){
            FriendShareTimelineEntity ftimeline = new FriendShareTimelineEntity();
            ftimeline.setMemberId(sub.getSubscriberId());
            ftimeline.setIsOwn(0);
            ftimeline.setFsmId(friendShareMessageEntity.getId());
            ftimeline.setCreateTime(System.currentTimeMillis());
            friendShareTimelineService.insert(ftimeline);
        }
    }

    @Override
    public void deleteMsgAndTimeline(Long id) {
        deleteById(id);
        //从时间轴上删除动态
        friendShareTimelineService.delete(new EntityWrapper<FriendShareTimelineEntity>().eq("fsm_id",id));
        //删除动态的评论
        friendShareCommentService.delete(new EntityWrapper<FriendShareCommentEntity>().eq("fsm_id",id));
    }

}

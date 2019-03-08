package io.renren.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.friend.FriendShareTimelineDao;
import io.renren.modules.app.entity.friend.FriendShareTimelineEntity;
import io.renren.modules.app.service.FriendShareTimelineService;


@Service("friendShareTimelineService")
public class FriendShareTimelineServiceImpl extends ServiceImpl<FriendShareTimelineDao, FriendShareTimelineEntity> implements FriendShareTimelineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FriendShareTimelineEntity> page = this.selectPage(
                new Query<FriendShareTimelineEntity>(params).getPage(),
                new EntityWrapper<FriendShareTimelineEntity>()
        );

        return new PageUtils(page);
    }

}

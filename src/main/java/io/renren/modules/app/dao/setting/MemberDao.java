package io.renren.modules.app.dao.setting;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.app.entity.setting.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * @author xukaijun
 */
@Mapper
public interface MemberDao extends BaseMapper<Member> {

}

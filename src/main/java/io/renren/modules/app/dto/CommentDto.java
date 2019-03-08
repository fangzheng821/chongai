package io.renren.modules.app.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 评论返回实例
 * @author xukaijun
 * @email 383635738@qq.com
 * @date 2019-03-02 09:38:43
 */
public class CommentDto implements Serializable {

	private Long id;
	/**
	 * 关联member表
	 */
	private Long memberId;

	private String memberName;

	private String memberAvatar;
	/**
	 * 内容实体 utf8mb4格式能存储emjoy表情
	 */
	private String content;
	/**
	 * 
	 */
	private Long createTime;
	/**
	 * 点赞数
	 */
	private Integer likeCount;
	/**
	 * 阅读量
	 */
	private Integer readCount;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：点赞数
	 */
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	/**
	 * 获取：点赞数
	 */
	public Integer getLikeCount() {
		return likeCount;
	}
	/**
	 * 设置：阅读量
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	/**
	 * 获取：阅读量
	 */
	public Integer getReadCount() {
		return readCount;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAvatar() {
		return memberAvatar;
	}

	public void setMemberAvatar(String memberAvatar) {
		this.memberAvatar = memberAvatar;
	}
}

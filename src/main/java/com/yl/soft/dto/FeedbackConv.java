package com.yl.soft.dto;

import java.time.LocalDateTime;

import com.yl.soft.po.Feedback;


/**
 * <p>
 * 反馈转换器
 * </p>
 *
 * @author Chanhs
 * @since 2020-09-09
 */
public interface FeedbackConv {

	public static Feedback newDo(Feedback feedback, Integer uid) {
		if (feedback == null) {
			feedback = new Feedback();
		}
		feedback.setCreateat(LocalDateTime.now());
		feedback.setCreateby(uid);
		feedback.setDeleted(Boolean.FALSE);
		feedback.setUpdateat(LocalDateTime.now());
		feedback.setUpdateby(uid);
		return feedback;
	}

}

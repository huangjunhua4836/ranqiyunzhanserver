package com.yl.soft.controller.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbOpportunityDto;
import com.yl.soft.dto.FeedbackConv;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.enums.FeedbackEnum;
import com.yl.soft.po.EhbOpportunity;
import com.yl.soft.po.Feedback;
import com.yl.soft.po.Problem;
import com.yl.soft.service.FeedbackService;
import com.yl.soft.service.ProblemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * 反馈 前端控制器
 * </p>
 *
 * @author Chanhs
 * @since 2020-09-09
 */
@Api(tags = "C端模块-反馈模块接口")
@RestController
@RequestMapping("/cms/feedback")
@Validated
public class FeedbackController extends BaseController {
	@Autowired
	private SessionState sessionState;
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private ProblemService problemService;

	@ApiOperation(value = "提交反馈", notes = "提交反馈信息" , tags = {"C端模块-H5详情"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "content", value = "反馈内容", required = true, paramType = "query"),
			@ApiImplicitParam(name = "media", value = "图片地址", required = false, paramType = "query"),
			@ApiImplicitParam(name = "concat", value = "联系方式", required = true, paramType = "query"),
			@ApiImplicitParam(name = "token", value = "登陆标识", required = false, paramType = "query") })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入反馈内容"),
			@ApiResponse(code = -102, message = "请输入5到512字的反馈内容"), @ApiResponse(code = -103, message = "过长的联系方式"),
			@ApiResponse(code = 0, message = "反馈成功"), @ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PostMapping("/feedAdd")
	public BaseResult<Integer> add(
			@NotBlank(message = "-101-请输入反馈内容") @Size(min = 5, max = 512, message = "-102-请输入5到512字的反馈内容") String content,
			@Size(max = 2048, message = "-104-过长的图片地址") String media,
			@Size(max = 128, message = "-103-过长的联系方式") String concat, String token) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		Feedback feedback = FeedbackConv.newDo(null, sessionUser.getId());
		feedback.setUserId(sessionUser.getId());
		feedback.setFeedbackat(LocalDateTime.now());
		feedback.setReaded(FeedbackEnum.Readed.未处理.getValue());
		feedback.setConcat(concat);
		feedback.setContent(content);
		feedback.setMedia(media);
		if (!feedbackService.save(feedback)) {
			return error();
		}
		return ok(feedback.getId());
	}
	
	@ApiOperation(value = "帮助问题列表", notes = "帮助问题列表" , tags = {"C端模块-H5详情"})
	@PostMapping("/getProblem")
	public ResultItem<List<Problem>> getProblem(Integer page,Integer size) {
		PageHelper.startPage(page, size, "sort DESC");
		PageInfo<Problem> pageInfo = new PageInfo<>(problemService.list());
		return ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}
	
	
	
}

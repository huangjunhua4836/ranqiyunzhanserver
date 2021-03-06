package com.yl.soft.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.PinyinUtil;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.*;
import com.yl.soft.dto.app.ArticleDto;
import com.yl.soft.dto.app.ExhibitorDto;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.*;
import com.yl.soft.service.*;

import cn.hutool.db.handler.StringHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Api(tags = { "C端模块-个人中心" })
@RestController
@RequestMapping("/")
public class PersonalCenterController extends BaseController {

	@Autowired
	private EhbAudienceService ehbAudienceService;

	@Autowired
	private EhbExhibitorService ehbExhibitorService;

	@Autowired
	private EhbUseractionService ehbUseractionService;

	@Autowired
	private EhbOpportunityService ehbOpportunityService;

	@Autowired
	private EhbAboutusService ehbAboutusService;

	@Autowired
	private EhbLabelService ehbLabelService;

	@Autowired
	private EhbDataUploadService ehbDataUploadService;

	@Autowired
	private EhbVisitorRegistrationService ehbVisitorRegistrationService;

	@Autowired
	private EhbArticleService ehbArticleService;

    @Autowired
    public RedisService redisService;
	
	@Autowired
	private SessionState sessionState;

	@ApiOperation(value = "获取我的个人信息", notes = "通过用户登录token查询当前用户信息，如果传userid就按照userid查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = false, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "用户id", required = false, paramType = "query"), })
	@PostMapping("/getMe")
	public ResultItem<EhbAudienceDto> getMe(String token, Integer userid) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		EhbAudience ehbAudience = ehbAudienceService.getById(null != userid ? userid : sessionUser.getId());
		EhbAudienceDto ehbDto = new EhbAudienceDto();
		boolean bool = true;
		if (null == ehbAudience.getBopie()) {
			EhbAudiencegrDto ge = MeUserConv.do2dto1(ehbAudience);
			ehbDto.setAudienceyegrDto(ge);
			bool = false;
		} else {
			EhbExhibitor ee = ehbExhibitorService.getById(ehbAudience.getBopie());
			EhbAudienceyeDto qy = MeUserConv.do2dto(ehbAudience, ee);
			ehbDto.setEhbAudienceyeDto(qy);
		}
		ehbDto.setUserType(bool);
		ehbDto.setPhoneState(ehbAudience.getPhone() == null ? "0" : "1");
		ehbDto.setWxState(ehbAudience.getWxOpenid() == null ? "0" : "1");
		ehbDto.setQqState(ehbAudience.getQqOpenid() == null ? "0" : "1");
		return ok(ehbDto);
	}

	@ApiOperation(value = "改个人信息修", notes = "参数名称前缀为ehbAudienceInfogrDto是修改个人账号信息，参数名称前缀为ehbAudienceInfoyeDto是修改企业账号信息，两个类型不能同时传")
	@PostMapping("/api/upMe")
	public BaseResult<EhbAudienceDto> upMe(EhbAudienceInfoDto ehbAudienceInfoDto) {
		SessionUser sessionUser = sessionState.getCurrentUser(ehbAudienceInfoDto.getToken());
		if (null == sessionUser.getBopie()) {
			EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfogrDto().getName())) {
				ehbAudience.setName(ehbAudienceInfoDto.getEhbAudienceInfogrDto().getName());
			}
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfogrDto().getQyname())) {
				ehbAudience.setQyname(ehbAudienceInfoDto.getEhbAudienceInfogrDto().getQyname());
			}
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfogrDto().getHeadPortrait())) {
				ehbAudience.setHeadPortrait(ehbAudienceInfoDto.getEhbAudienceInfogrDto().getHeadPortrait());
			}
			ehbAudienceService.updateById(ehbAudience);
		} else if (null != sessionUser.getBopie()) {
			EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
			EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(sessionUser.getBopie());

			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getEnterprisename())) {
				ehbExhibitor.setEnterprisename(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getEnterprisename());
			}
			
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getHeadPortrait())) {
				ehbAudience.setHeadPortrait(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getHeadPortrait());
				ehbAudienceService.updateById(ehbAudience);
			}
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getPhone())) {
				if(!isMobile(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getPhone())) {
					return error(-100, "请输入一个正确的手机号");
				}
				ehbExhibitor.setPhone(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getPhone());
			}
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getMailbox())) {
				if (!isEmail(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getMailbox())) {
					return error(-100, "请输入一个正确的邮件地址");
				}
				ehbExhibitor.setMailbox(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getMailbox());
			}
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getTelphone())) {
				if (!isPhone(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getTelphone())) {
					return error(-100, "请输入一个正确的电话号码");
				}
				ehbExhibitor.setTelphone(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getTelphone());
				ehbExhibitor.setTel(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getTelphone());
			}
			if (StringUtils.isNoneBlank(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getExname())) {
				ehbExhibitor.setName(ehbAudienceInfoDto.getEhbAudienceInfoyeDto().getExname());
			}
			ehbExhibitorService.updateById(ehbExhibitor);
		} else {
			return error(-900, "服务器繁忙请稍后再试");
		}
		
		//刷新内存
		 String keys[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"
                ,"R","S","T","U","V","W","X","Y","Z"};
	     redisService.del(keys);
	     QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
	     ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
	     ehbExhibitorQueryWrapper.eq("state",1);
	     ehbExhibitorService.list(ehbExhibitorQueryWrapper).stream().forEach(i->{
			 String firstWord = PinyinUtil.morePronunciation(i.getEnterprisename());
	         ExhibitorDto exhibitorDto = new ExhibitorDto();
	         exhibitorDto.setId(i.getId());
	         exhibitorDto.setEnterprisename(i.getEnterprisename());
	         EhbAudience ehbAudience1 = ehbAudienceService.lambdaQuery().select(EhbAudience::getHeadPortrait).eq(EhbAudience::getBopie,i.getId()).last("limit 1").one();
	         exhibitorDto.setLogo(ehbAudience1!=null?ehbAudience1.getHeadPortrait():null);
	         exhibitorDto.setBoothno(i.getBoothno());
	         exhibitorDto.setEnglishname(i.getEnglishname());
	         redisService.sSet(firstWord, JSONObject.toJSONString(exhibitorDto));
	     });
		
		return ok();
	}

	public static boolean isWeb(final String str) {
		String url = "http:/klsfnklnklwnl.csfwfwn.cn?1231=sjkfjkf&sfwfw=";
		String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
		Pattern pattern = Pattern.compile(regex);
		if (pattern.matcher(url).matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobile(final String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(regEx1);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPhone(final String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	@ApiOperation(value = "我的企业", notes = "我的企业")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"), })
	@PostMapping("/api/getComp")
	public BaseResult<EhbExhibitorDto> getComp(@NotBlank(message = "token不能为空") String token) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		if (sessionUser == null || sessionUser.getBopie() == null) {
			return error(-502, "服务器繁忙请稍后再试！");
		}
		EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(sessionUser.getBopie());
		EhbAudience user = ehbAudienceService.getById(sessionUser.getId());
		return ok(EhbExhibitorDto.of(ehbExhibitor, user));
	}

	@ApiOperation(value = "我的企业编辑", notes = "我的企业编辑")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "img", value = "企业封面banner图 多张用英文逗号分隔", required = true, paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "联系电话", required = true, paramType = "query"),
			@ApiImplicitParam(name = "mailbox", value = "联系邮箱", required = true, paramType = "query"),
			@ApiImplicitParam(name = "website", value = "公司主页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "address", value = "地址", required = true, paramType = "query"),
			@ApiImplicitParam(name = "describes", value = "企业简介", required = true, paramType = "query"),
			@ApiImplicitParam(name = "zwimg", value = "展位效果图", required = false, paramType = "query"), })
	@PostMapping("/api/updateComp")
	public BaseResult updateComp(String token, String img,String phone,
			@NotBlank(message = "请输入一个正确邮箱地址") String mailbox, @NotBlank(message = "请输入一个正确官网地址") String website,
			String address, String describes, String zwimg) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);

		EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(sessionUser.getBopie());
		ehbExhibitor.setImg(img);
		ehbExhibitor.setTelphone(phone);
		ehbExhibitor.setMailbox(mailbox);
		ehbExhibitor.setWebsite(website);
		ehbExhibitor.setAddress(address);
		ehbExhibitor.setDescribes(describes);
		ehbExhibitor.setZwimg(zwimg);

		ehbExhibitorService.updateById(ehbExhibitor);
		return ok2();
	}

	@ApiOperation(value = "获取所有标签", notes = "获取所有标签")
	@GetMapping("/getLable")
	public ResultItem<List<EhbLabelDto>> getLable() {
		List<EhbLabelDto> ehbLabel = ehbLabelService.list().stream().map(i -> {
			EhbLabelDto ehbLabelDto = new EhbLabelDto();
			BeanUtils.copyProperties(i, ehbLabelDto);
			return ehbLabelDto;
		}).collect(Collectors.toList());
		return ok(ehbLabel);
	}

	@ApiOperation(value = "我的橱窗列表/我的商机列表", notes = "分页我的橱窗列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "titleorconnent", value = "搜索查詢条件", required = false, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "类型（1-商机  2-商品）", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/api/meShowWindow")
	public ResultItem<List<EhbOpportunityDto>> meShowWindow(String token, String titleorconnent, Integer page,
			Integer size, Integer type) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		PageHelper.startPage(page, size, "createtime DESC");
		PageInfo<EhbOpportunityDto> pageInfo = new PageInfo<>(ehbOpportunityService.lambdaQuery()
				.eq(EhbOpportunity::getExhibitorid, sessioner.getBopie() == null ? "-1" : sessioner.getBopie())
				.eq(EhbOpportunity::getType, type).and(j -> j.like(EhbOpportunity::getTitle, titleorconnent).or()
						.like(EhbOpportunity::getContent, titleorconnent))
				.list().stream().map(i -> {
					int c = ehbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, i.getExhibitorid()).count();
					Map<Integer, EhbLabel> map = ehbLabelService.list().stream()
							.collect(Collectors.toMap(EhbLabel::getId, j -> j));
					return EhbOpportunityDto.of(i, ehbExhibitorService.getById(i.getExhibitorid()), c, map);
				}).collect(Collectors.toList()));
		return ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}

	@ApiOperation(value = "(发布/修改)商品/商机", notes = "发布商品/商机（传id就是修改，不传就是添加）")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "title", value = "请输入商品名称标题", required = true, paramType = "query"),
			@ApiImplicitParam(name = "content", value = "请详细描述商品介绍", required = true, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "类型（1-商机  2-商品）", required = true, paramType = "query"),
			@ApiImplicitParam(name = "label", value = "请选择标签  标签id用英文逗号分隔 1,2,3...", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "商机/商品id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "picture", value = "商品多图片上传多张用印文逗号分隔 src1,src2", required = true, paramType = "query"), })
	@PostMapping("/api/pushGoods")
	public BaseResult pushGoods(String token, @NotBlank(message = "请添加一个正确的标签") String title,
			@NotBlank(message = "请添加一个正确的内容") String content, @NotBlank(message = "请选择一个正确的标签") String label,
			String picture, @NotBlank(message = "类型不能为空") String type, String id) {
		try {
			SessionUser sessionUser = sessionState.getCurrentUser(token);
			EhbOpportunity ehbOpportunity = new EhbOpportunity();
			if (StringUtils.isEmpty(id)) {
				ehbOpportunity.setTitle(title);
				ehbOpportunity.setContent(content);
				List<Integer> ids = Arrays.asList(label.split(",")).stream().map(Integer::parseInt)
						.collect(Collectors.toList());
				ehbOpportunity.setLabel(JSON.toJSONString(ids));
				ehbOpportunity.setPicture(picture);
				ehbOpportunity.setType(Integer.parseInt(type)); // 1-商机 2-商品
				ehbOpportunity.setCreatetime(LocalDateTime.now());
				ehbOpportunity.setReleasetime(LocalDateTime.now());
				ehbOpportunity.setIsdel(false);
				ehbOpportunity.setExhibitorid(ehbAudienceService.getById(sessionUser.getId()).getBopie());
				ehbOpportunityService.save(ehbOpportunity);
			} else {
				ehbOpportunity = ehbOpportunityService.getById(id);
				if (null == ehbOpportunity) {
					return error(-904, "修改的商机或商品不存在");
				}
				ehbOpportunity.setTitle(title);
				ehbOpportunity.setContent(content);
				List<Integer> ids = Arrays.asList(label.split(",")).stream().map(Integer::parseInt)
						.collect(Collectors.toList());
				ehbOpportunity.setLabel(JSON.toJSONString(ids));
				ehbOpportunity.setPicture(picture);
				ehbOpportunity.setType(Integer.parseInt(type)); // 1-商机 2-商品
				ehbOpportunityService.updateById(ehbOpportunity);
			}
			return ok2(ehbOpportunity);
		} catch (Exception e) {
			e.printStackTrace();
			return error(-900, e.getMessage());
		}

	}

	@ApiOperation(value = "(删除)商品/商机", notes = "删除我发布的商品/商机")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "商机/商品id", required = false, paramType = "query"), })
	@PostMapping("/api/deleGoods")
	public BaseResult deleGoods(String token, Integer id) {
		try {
			SessionUser sessionUser = sessionState.getCurrentUser(token);
			if (sessionUser.getBopie() == null) {
				return error(-101, "您没有权限删除该商品或商机");
			}
			EhbOpportunity ehbOpportunity = ehbOpportunityService.lambdaQuery()
					.eq(EhbOpportunity::getExhibitorid, sessionUser.getBopie()).last("LIMIT 1").one();
			ehbUseractionService.remove(
					new UpdateWrapper<EhbUseraction>().lambda().eq(EhbUseraction::getRelateid, ehbOpportunity.getId())
							.and(i -> i.eq(EhbUseraction::getType, 2).or().eq(EhbUseraction::getType, 4)));
			ehbOpportunityService.removeById(id);
			return ok2(ehbOpportunity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return error(-900, e.getMessage());
		}

	}

	@ApiOperation(value = "我的橱窗商品/商机详情", notes = "我的橱窗商品详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "商品/商机id", required = true, paramType = "query"), })
	@PostMapping("/api/goodsDes")
	public ResultItem<EhbOpportunityDto> goodsDes(String token, String id) {
		EhbOpportunity ehbOpportunity = ehbOpportunityService.getById(id);
		EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbOpportunity.getExhibitorid());
		Integer i = ehbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, ehbExhibitor.getId()).count();
		Map<Integer, EhbLabel> map = ehbLabelService.list().stream().collect(Collectors.toMap(EhbLabel::getId, j -> j));
		// 修改访问量
		//ehbOpportunityService.lambdaUpdate().setSql("countbrowse=countbrowse+1").eq(EhbOpportunity::getId, id).update();
		return ok(EhbOpportunityDto.of(ehbOpportunity, ehbExhibitor, i, map));
	}

	@ApiOperation(value = "（收藏/关注）的企业列表", notes = "我收藏的企业")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/api/meCollentionEnt")
	public ResultItem<List<MeCollectionDto>> meCollentionEnt(String token, Integer page, Integer size) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);

		int type = 1; // 企业
		int activeType = 1; // 收藏

		PageHelper.startPage(page, size, "createtime DESC");
		PageInfo<EhbUseraction> pageInfo = new PageInfo<>(
				ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessionUser.getId())
						.eq(EhbUseraction::getType, type).eq(EhbUseraction::getActivetype, activeType).list());

		List<MeCollectionDto> dataList = pageInfo.getList().stream().map(i -> {
			MeCollectionDto md = new MeCollectionDto();
			EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(i.getRelateid());
			if (null != ehbExhibitor) {
				BeanUtils.copyProperties(ehbExhibitor, md);
			}
			EhbAudience ehbAudience = ehbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, i.getRelateid())
					.last("limit 1").one();
			if(ehbAudience!=null){
				md.setImg(ehbAudience.getHeadPortrait());
			}
			return md;
		}).collect(Collectors.toList());

		return ok(dataList, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}

	@ApiOperation(value = "收藏的商机列表", notes = "我收藏的商机")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/api/meCollentionBol")
	public ResultItem<List<EhbOpportunityDto>> meCollentionBol(String token, Integer page, Integer size) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		int type = 2; // 商机
		int activeType = 1; // 收藏
		PageHelper.startPage(page, size, "createtime DESC");
		PageInfo<EhbUseraction> pageInfo = new PageInfo<>(
				ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessionUser.getId())
						.eq(EhbUseraction::getType, type).eq(EhbUseraction::getActivetype, activeType).list());
		List<EhbOpportunityDto> dataList = pageInfo.getList().stream().map(i -> {
			EhbOpportunity ehbOpportunity = ehbOpportunityService.getById(i.getRelateid());
			Map<Integer, EhbLabel> map = ehbLabelService.list().stream()
					.collect(Collectors.toMap(EhbLabel::getId, j -> j));
			EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbOpportunity.getExhibitorid());
			Integer c = ehbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, ehbExhibitor.getId()).count();
			EhbOpportunityDto md = EhbOpportunityDto.of(ehbOpportunity, ehbExhibitor, c, map);
			return md;
		}).collect(Collectors.toList());

		return ok(dataList, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}

	@ApiOperation(value = "收藏的商品列表", notes = "我收藏的商品")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/api/meCollentionBo")
	public ResultItem<List<EhbOpportunityDto>> meCollentionCom(String token, Integer page, Integer size) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		int type = 4; // 商品
		int activeType = 1; // 收藏
		PageHelper.startPage(page, size, "createtime DESC");
		PageInfo<EhbUseraction> pageInfo = new PageInfo<>(
				ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessionUser.getId())
						.eq(EhbUseraction::getType, type).eq(EhbUseraction::getActivetype, activeType).list());
		List<EhbOpportunityDto> dataList = pageInfo.getList().stream().map(i -> {
			EhbOpportunity ehbOpportunity = ehbOpportunityService.getById(i.getRelateid());
			Map<Integer, EhbLabel> map = ehbLabelService.list().stream()
					.collect(Collectors.toMap(EhbLabel::getId, j -> j));
			EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbOpportunity.getExhibitorid());
			Integer c = ehbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, ehbExhibitor.getId()).count();
			EhbOpportunityDto md = EhbOpportunityDto.of(ehbOpportunity, ehbExhibitor, c, map);
			return md;
		}).collect(Collectors.toList());

		return ok(dataList, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}

	@ApiOperation(value = "添加/删除（收藏/关注）", notes = "用户添加收藏（收藏关注展商就是收藏）行为", tags = { "C端模块-H5详情" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "relateid", value = "收藏（如果是关注展商就是展示id）内容的id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "1：展商   2：商机  3：资讯  4:商品", required = true, paramType = "query"), })
	@PostMapping("/api/addOrDelCollention")
	public BaseResult addOrDelCollention(String token, Integer relateid, Integer type) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		if(sessioner.getCode()==-1) {
			return error(-401,"token失效");
		}
		Integer i = 1;// （1：收藏 2：点赞 3：关注 4：浏览）

		switch (type) {
		case 1: // 展商
			if (ehbExhibitorService.getById(relateid) == null) {
				return error(-509, "展商不存在");
			}
			break;
		case 2: // 商机(1-商机 2-商品)
			EhbOpportunity et = ehbOpportunityService.lambdaQuery().eq(EhbOpportunity::getId, relateid)
					.eq(EhbOpportunity::getType, 1).one();
			if (et == null) {
				return error(-508, "商机不存在");
			}
			break;
		case 3:
			if (ehbArticleService.getById(relateid) == null) {
				return error(-507, "资讯不存在");
			}
			break;
		case 4: // 商品(1-商机 2-商品)
			EhbOpportunity et1 = ehbOpportunityService.lambdaQuery().eq(EhbOpportunity::getId, relateid)
					.eq(EhbOpportunity::getType, 2).one();
			if (et1 == null) {
				return error(-506, "商品不存在");
			}
			break;
		default:
			return error(-505, "收藏/关注类型错误");
		}

		EhbUseraction ehbUseraction = ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessioner.getId())
				.eq(EhbUseraction::getRelateid, relateid).eq(EhbUseraction::getType, type)
				.eq(EhbUseraction::getActivetype, i).one();
		if (ehbUseraction != null) {//取消收藏/关注
			ehbUseractionService.removeById(ehbUseraction.getId());
			if (type == 4 || type == 2) {
				ehbOpportunityService.lambdaUpdate().setSql("countcollection=countcollection-1")
						.eq(EhbOpportunity::getId, relateid).update();
			}
			if (type == 3) {
				ehbArticleService.lambdaUpdate().setSql("countcollection=countcollection-1")
						.eq(EhbArticle::getId, relateid).update();
			}
			return ok2(0);
		} else {//添加收藏/关注
			ehbUseractionService.save(EhbUseractionDto.of(sessioner, type, relateid, i));
			if (type == 4 || type == 2) {
				ehbOpportunityService.lambdaUpdate().setSql("countcollection=countcollection+1")
						.eq(EhbOpportunity::getId, relateid).update();
			}
			if (type == 3) {
				ehbArticleService.lambdaUpdate().setSql("countcollection=countcollection+1")
						.eq(EhbArticle::getId, relateid).update();
			}
			return ok2(1);
		}
	}

	@ApiOperation(value = "添加/取消点赞", notes = "用户点赞行为", tags = { "C端模块-H5详情" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "relateid", value = "点赞内容的id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "1：企业   2：商机  3：资讯 4:商品", required = true, paramType = "query"), })
	@PostMapping("/api/addOrDelPraise")
	public BaseResult addOrDelPraise(String token, Integer relateid, Integer type) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		if(sessioner.getCode()==-1) {
			return error(-401,"token失效");
		}
		Integer i = 2;// （1：收藏 2：点赞 3：关注 4：浏览）
		switch (type) {
		case 1: // 展商
			if (ehbExhibitorService.getById(relateid) == null) {
				return error(-509, "展商不存在");
			}
			break;
		case 2: // 商机(1-商机 2-商品)
			EhbOpportunity et = ehbOpportunityService.lambdaQuery().eq(EhbOpportunity::getId, relateid)
					.eq(EhbOpportunity::getType, 1).one();
			if (et == null) {
				return error(-508, "商机不存在");
			}
			break;
		case 3:
			if (ehbArticleService.getById(relateid) == null) {
				return error(-507, "资讯不存在");
			}
			break;
		case 4: // 商品(1-商机 2-商品)
			EhbOpportunity et1 = ehbOpportunityService.lambdaQuery().eq(EhbOpportunity::getId, relateid)
					.eq(EhbOpportunity::getType, 2).one();
			if (et1 == null) {
				return error(-506, "商品不存在");
			}
			break;
		default:
			return error(-505, "点赞类型错误");
		}
		EhbUseraction ehbUseraction = ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessioner.getId())
				.eq(EhbUseraction::getRelateid, relateid).eq(EhbUseraction::getType, type)
				.eq(EhbUseraction::getActivetype, i).one();
		if (ehbUseraction != null) {
			ehbUseractionService.removeById(ehbUseraction.getId());
			if (type == 4 || type == 2) {
				ehbOpportunityService.lambdaUpdate().setSql("countthumbs=countthumbs-1")
						.eq(EhbOpportunity::getId, relateid).update();
			}
			if (type == 3) {
				ehbArticleService.lambdaUpdate().setSql("countthumbs=countthumbs-1").eq(EhbArticle::getId, relateid)
						.update();
			}
			EhbOpportunity ehbOpportunity =  ehbOpportunityService.getById(ehbUseraction.getRelateid());
			return ok2(com.yl.soft.common.util.StringUtils.isEmpty(ehbOpportunity)?0:ehbOpportunity.getCountthumbs());
//			return ok2(ehbOpportunityService.getById(ehbUseraction.getRelateid()).getCountthumbs());
		} else {
			ehbUseractionService.save(EhbUseractionDto.of(sessioner, type, relateid, i));
			if (type == 4 || type == 2) {
				ehbOpportunityService.lambdaUpdate().setSql("countthumbs=countthumbs+1")
						.eq(EhbOpportunity::getId, relateid).update();
			}
			if (type == 3) {
				ehbArticleService.lambdaUpdate().setSql("countthumbs=countthumbs+1").eq(EhbArticle::getId, relateid)
						.update();
			}
			EhbOpportunity ehbOpportunity =  ehbOpportunityService.getById(relateid);
			return ok2(com.yl.soft.common.util.StringUtils.isEmpty(ehbOpportunity)?0:ehbOpportunity.getCountthumbs());
//			return ok2(ehbOpportunityService.getById(relateid).getCountthumbs());
		}

	}

	@ApiOperation(value = "浏览", notes = "用户浏览行为")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "relateid", value = "浏览内容的id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "1：企业   2：商机  3：资讯 4：商品", required = true, paramType = "query"), })
	@PostMapping("/addBrowse")
	public BaseResult addBrowse(String token, Integer relateid, Integer type) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		Integer i = 4;// （1：收藏 2：点赞 3：关注 4：浏览）
		switch (type) {
		case 2:
			ehbOpportunityService.lambdaUpdate().setSql("countbrowse=countbrowse+1").eq(EhbOpportunity::getId, relateid)
					.update();
			break;
		case 3:
			ehbArticleService.lambdaUpdate().setSql("countbrowse=countbrowse+1").eq(EhbArticle::getId, relateid)
					.update();
			break;
		case 4:
			ehbOpportunityService.lambdaUpdate().setSql("countbrowse=countbrowse+1").eq(EhbOpportunity::getId, relateid)
					.update();
			break;
		}

		ehbUseractionService.save(EhbUseractionDto.of(sessioner, type, relateid, i));
		return ok2();
	}

	@ApiOperation(value = "关于", notes = "关于")
	@PostMapping("/aboutus")
	public ResultItem<EhbAboutus> aboutus() {
		List<EhbAboutus> list = ehbAboutusService.list();
		return ok(list.size() > 0 ? list.get(0) : null);
	}

	@ApiOperation(value = "资料下载", notes = "资料下载")
	@PostMapping("/dataUpLoad")
	public ResultItem<List<EhbDataUpload>> dataUpLoad() {
//		List<EhbDataUpload> ehbDataUploads = ehbDataUploadService.list();
		List<EhbDataUpload> ehbDataUploads = new ArrayList<>();
		QueryWrapper<EhbDataUpload> ehbDataUploadQueryWrapper = null;
		// 企业授权书模板
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "企业授权书模板");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> qysqsmbs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (qysqsmbs != null && !qysqsmbs.isEmpty()) {
			ehbDataUploads.addAll(qysqsmbs);
		}

		// 展会中英文邀请函
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "展会中英文邀请函");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> zhzywyqhs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (zhzywyqhs != null && !zhzywyqhs.isEmpty()) {
			ehbDataUploads.addAll(zhzywyqhs);
		}

		// 展商合约
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "展商合约");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> zshys = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (zshys != null && !zshys.isEmpty()) {
			ehbDataUploads.addAll(zshys);
		}

		// 参展商手册
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "参展商手册");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> czsscs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (czsscs != null && !czsscs.isEmpty()) {
			ehbDataUploads.addAll(czsscs);
		}

		// 搭建商手册
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "搭建商手册");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> djsscs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (djsscs != null && !djsscs.isEmpty()) {
			ehbDataUploads.addAll(djsscs);
		}

		// 团体观众登记表
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "团体观众登记表");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> ttgzdjbs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (ttgzdjbs != null && !ttgzdjbs.isEmpty()) {
			ehbDataUploads.addAll(ttgzdjbs);
		}

		// 观众邀请券
		ehbDataUploadQueryWrapper = new QueryWrapper<>();
		ehbDataUploadQueryWrapper.eq("title", "观众邀请券");
		ehbDataUploadQueryWrapper.orderByDesc("createtime");
		ehbDataUploadQueryWrapper.last("limit 1");
		List<EhbDataUpload> gzyqqs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
		if (gzyqqs != null && !gzyqqs.isEmpty()) {
			ehbDataUploads.addAll(gzyqqs);
		}
		return ok(ehbDataUploads);
	}

	@ApiOperation(value = "参展登记", notes = "参展登记")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"), })
	@PostMapping("/api/registration")
	public ResultItem<List<EhbVisitorRegistration>> registration(String token) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		List<EhbVisitorRegistration> eh = ehbVisitorRegistrationService.lambdaQuery()
				.eq(EhbVisitorRegistration::getUserid, sessioner.getId()).list();
		return ok(eh);
	}

	@ApiOperation(value = "添加参展登记", notes = "添加参展登记", tags = { "C端模块-H5详情" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"), })
	@PostMapping("/api/addregistration")
	public BaseResult<String> addregistration(EhbVisitorRegistration ehbVisitorRegistration, String token) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		if (StringUtils.isAllEmpty(ehbVisitorRegistration.getName())) {
			return error(-101, "联系人不能为空");
		}
		if (StringUtils.isAllEmpty(ehbVisitorRegistration.getCompname())) {
			return error(-102, "公司名称不能为空");
		}
		if (StringUtils.isAllEmpty(ehbVisitorRegistration.getPhone())) {
			return error(-103, "联系人不能为空");
		}
		if (StringUtils.isAllEmpty(ehbVisitorRegistration.getShowarea())) {
			return error(-104, "预展位面积不能为空");
		}
		if (ehbVisitorRegistration.getAppellation() == null) {
			return error(-105, "称谓不能为空");
		}
		ehbVisitorRegistration.setUserid(sessioner.getId());
		ehbVisitorRegistration.setCreatetime(LocalDateTime.now());
		ehbVisitorRegistrationService.save(ehbVisitorRegistration);
		return ok(ehbVisitorRegistration.getId() + "");
	}

	@ApiOperation(value = "参展登记详情", notes = "参展登记详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"), })
	@PostMapping("/api/registrationDes")
	public ResultItem<EhbVisitorRegistration> registrationDes(Integer id, String token) {
		EhbVisitorRegistration eh = ehbVisitorRegistrationService.getById(id);
		return ok(eh);
	}

}

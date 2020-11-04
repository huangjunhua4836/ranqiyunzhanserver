package com.yl.soft.controller.api;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yl.soft.common.util.BaseConv;
import com.yl.soft.common.util.LogUtils;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.LiveDto;
import com.yl.soft.dto.LiveStartEndDto;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.po.EhbLiveMsg;
import com.yl.soft.po.EhbLiveRecording;
import com.yl.soft.service.EhbLiveBroadcastService;
import com.yl.soft.service.EhbLiveMsgService;
import com.yl.soft.service.EhbLiveRecordingService;
import com.yl.soft.service.EhbWonderfulAtlasService;
import com.yl.soft.service.EhbWonderfulVideoService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"C端模块-直播回调"})
@RestController
@RequestMapping("/")
public class LiveRController extends BaseController{

	@Autowired
	private EhbLiveBroadcastService ehbLiveBroadcastService;
	
	
	private static final Logger log = LoggerFactory.getLogger(LiveRController.class);

	
	@Autowired
	private EhbLiveRecordingService ehbLiveRecordingService;
	
	@Autowired
	private EhbLiveMsgService EhbLiveMsgService;
	
	
	@ApiOperation(value = "直播通知", notes = "直播通知")
	@PostMapping("/liveMsg")
	public void liveMsg(@RequestBody LiveStartEndDto liStartEndDto) {
		
		log.info(JSON.toJSONString(liStartEndDto));
		EhbLiveMsg ehbLiveMsg=new EhbLiveMsg();
		EhbLiveBroadcast ehbLiveBroadcast=ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getFlowName, liStartEndDto.getStream()).eq(EhbLiveBroadcast::getLiveStatus, 0).last("LIMIT 1").one();
		if(liStartEndDto.getEvent().equals("PUBLISH")) {
			ehbLiveBroadcast.setLiveStatus(1);
			ehbLiveBroadcast.setLiveStartTime(LocalDateTime.now());
			ehbLiveBroadcastService.updateById(ehbLiveBroadcast);
		}
		
		if(liStartEndDto.getEvent().equals("PUBLISH_DONE")){
//			ehbLiveBroadcast.setLiveStatus(2);
			ehbLiveBroadcast.setLiveEndtime(LocalDateTime.now());
			ehbLiveBroadcastService.updateById(ehbLiveBroadcast);
		}
		ehbLiveMsg.setDomain(liStartEndDto.getDomain());
		ehbLiveMsg.setApp(liStartEndDto.getApp());
		ehbLiveMsg.setStream(liStartEndDto.getClient_ip());
		ehbLiveMsg.setUsrArgs(liStartEndDto.getUsr_args());
		ehbLiveMsg.setClientIp(liStartEndDto.getClient_ip());
		ehbLiveMsg.setNodeIp(liStartEndDto.getNode_ip());
		ehbLiveMsg.setPublishTimestamp(liStartEndDto.getPublish_timestamp());
		ehbLiveMsg.setLiveid(ehbLiveBroadcast.getId());
		ehbLiveMsg.setEvent(liStartEndDto.getEvent());
		EhbLiveMsgService.save(ehbLiveMsg);
		
	}
	
	@ApiOperation(value = "直播录制回调", notes = "直播录制回调")
	@PostMapping("/liveN")
	public void liveN(@RequestBody LiveDto liveDto) {

		log.info(JSON.toJSONString(liveDto));
		EhbLiveRecording ehbLiveRecording=new EhbLiveRecording();
		ehbLiveRecording.setApp(liveDto.getApp());
		ehbLiveRecording.setProjectId(liveDto.getProject_id());
		ehbLiveRecording.setPublishDomain(liveDto.getPublish_domain());
		ehbLiveRecording.setEventType(liveDto.getEvent_type());
		ehbLiveRecording.setStream(liveDto.getStream());
		ehbLiveRecording.setRecordFormat(liveDto.getRecord_format());
		ehbLiveRecording.setDownloadUrl(liveDto.getDownload_url());
		ehbLiveRecording.setAssetId(liveDto.getAsset_id());
		ehbLiveRecording.setPlayUrl(liveDto.getPlay_url());
		ehbLiveRecording.setFileSize(liveDto.getFile_size());
		ehbLiveRecording.setRecordDuration(liveDto.getRecord_duration());
		ehbLiveRecording.setStartTime(liveDto.getStart_time());
		ehbLiveRecording.setEndTime(liveDto.getEnd_time());
		ehbLiveRecording.setWidth(liveDto.getWidth());
		ehbLiveRecording.setHeight(liveDto.getHeight());
		ehbLiveRecording.setObsLocation(liveDto.getObs_location());
		ehbLiveRecording.setObsBucket(liveDto.getObs_bucket());
		ehbLiveRecording.setObsObject(liveDto.getObs_object());
		ehbLiveRecording.setCreatetime(LocalDateTime.now());
		
		
		EhbLiveBroadcast ehbLiveBroadcast=ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getFlowName, liveDto.getStream()).eq(EhbLiveBroadcast::getLiveStatus, 1).last("LIMIT 1").one();
		
		if(StringUtils.isEmpty(ehbLiveBroadcast.getPlayback())) {
			ehbLiveBroadcast.setPlayback(ehbLiveRecording.getDownloadUrl());
//			ehbLiveBroadcast.setLiveStatus(2);
			ehbLiveBroadcast.setVideoDownUrl(ehbLiveRecording.getDownloadUrl());
			ehbLiveBroadcastService.updateById(ehbLiveBroadcast);
			
		}
		ehbLiveRecording.setLiveid(ehbLiveBroadcast.getId());
		ehbLiveRecordingService.save(ehbLiveRecording);
		
	}
}

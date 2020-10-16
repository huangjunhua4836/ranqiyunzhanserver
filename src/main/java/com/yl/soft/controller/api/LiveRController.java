package com.yl.soft.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.dto.LiveDto;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.po.EhbLiveRecording;
import com.yl.soft.service.EhbLiveBroadcastService;
import com.yl.soft.service.EhbLiveRecordingService;
import com.yl.soft.service.EhbWonderfulAtlasService;
import com.yl.soft.service.EhbWonderfulVideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"C端模块-直播回调"})
@RestController
@RequestMapping("/")
public class LiveRController {

	@Autowired
	private EhbLiveBroadcastService ehbLiveBroadcastService;
	
	
	
	@Autowired
	private EhbLiveRecordingService ehbLiveRecordingService;
	
	@ApiOperation(value = "直播回调", notes = "直播录制回调")
	@PostMapping("/liveN")
	public void liveN(@RequestBody LiveDto liveDto) {

		EhbLiveRecording ehbLiveRecording=new EhbLiveRecording();
//		if(liveDto.getEvent_type().equals("RECORD_START")) { //开始录制
//			//0即将直播
//			EhbLiveBroadcast ehbLiveBroadcastDto=ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getFlowName, liveDto.getStream()).eq(EhbLiveBroadcast::getLiveStatus, 0).last("LIMIT 1").one();
//			ehbLiveBroadcastService.lambdaUpdate().setSql("live_status=1")
//			.eq(EhbLiveBroadcast::getId, ehbLiveBroadcastDto.getId()).update();
//			
//			ehbLiveRecording.setLiveid(ehbLiveBroadcastDto.getId());
//		}else if(liveDto.getEvent_type().equals("RECORD_FILE_COMPLETE")) { //录制文件生成完成生成新文件
//			EhbLiveBroadcast ehbLiveBroadcastDto=ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getFlowName, liveDto.getStream()).eq(EhbLiveBroadcast::getLiveStatus, 1).last("LIMIT 1").one();
//			ehbLiveRecording.setLiveid(ehbLiveBroadcastDto.getId());
//		}else if(liveDto.getEvent_type().equals("RECORD_OVER")) { //录制结束
//			//直播结束回放
//			EhbLiveBroadcast ehbLiveBroadcastDto=ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getFlowName, liveDto.getStream()).eq(EhbLiveBroadcast::getLiveStatus, 1).last("LIMIT 1").one();
//			
//			ehbLiveBroadcastService.lambdaUpdate().setSql("live_status=1")
//			.eq(EhbLiveBroadcast::getId, ehbLiveBroadcastDto.getId()).update();
//			
//			
//			ehbLiveBroadcastService.lambdaUpdate().setSql("live_status=2")
//			.eq(EhbLiveBroadcast::getId, ehbLiveBroadcastDto.getId()).update();
//			ehbLiveRecording.setLiveid(ehbLiveBroadcastDto.getId());
//			
//
//		}
		
		
		EhbLiveBroadcast ehbLiveBroadcastDto=ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getFlowName, liveDto.getStream()).eq(EhbLiveBroadcast::getLiveStatus, 1).last("LIMIT 1").one();
		
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
		ehbLiveBroadcastDto.setPlayback(liveDto.getDownload_url());
		ehbLiveBroadcastService.updateById(ehbLiveBroadcastDto);
		ehbLiveRecordingService.save(ehbLiveRecording);
	}
}

package com.yl.soft.controller.api;

import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.DateUtils;
import com.yl.soft.dto.AttachmentDTO;
import com.yl.soft.po.CrmFile;
import com.yl.soft.service.CrmFileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Api(tags = {"C端模块-一龄云多文件上传"})
@RestController
@RequestMapping("/api")
public class UpLoadFileController2 extends BaseResponseUtil {

	@Value("${custom.uploadPath}")
	private String uploadPath;

	@Value("${custom.ip}")
	private String ip;

	@Value("${custom.port}")
	private String port;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Value("${custom.image_upload_ext}")
	private String image_upload_ext;

	@Autowired
	private CrmFileService crmFileService;

	/**
	 * 上传文件
	 * @return
	 */
	@ApiOperation(value = "上传文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "request", value = "上传文件流 每个文件都要有filename", required = true, dataType = "MultipartHttpServletRequest 文件流")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "成功")
			,@ApiResponse(code = 401, message = "token为空！")
			,@ApiResponse(code = 402, message = "token失效！")
			,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
			,@ApiResponse(code = -1, message = "系统异常")
	})
	@PostMapping("/upLoadByHttp2")
	public BaseResponse<List<AttachmentDTO>> upLoadByHttp2(@ApiIgnore MultipartHttpServletRequest request
			, @ApiParam(value = "文件标记（1营业执照，2企业授权书，3其他）",required = true) @RequestParam("remarks") String remarks
			, @ApiParam(hidden = true) String title){
		if(StringUtils.isEmpty(uploadPath)){
			return setResultError("服务器上传基础路径为空！");
		}
		String nowDateDir = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
		File fileDir = new File(uploadPath+File.separator+nowDateDir);
		if (!fileDir.exists() && !fileDir.isDirectory()) {
			fileDir.mkdirs();
		}
		StringBuffer buffer = new StringBuffer();
		List<AttachmentDTO> attachments = new ArrayList<>();
		// 获取多个file
		for (Iterator<String> it = request.getFileNames(); it.hasNext(); ) {
			String key = (String) it.next();
			List<MultipartFile> multipartFiles = request.getFiles(key);
			if (multipartFiles != null && multipartFiles.size() > 0) {
				for(int i = 0;i<multipartFiles.size();i++){
					try {
						String oldName = multipartFiles.get(i).getOriginalFilename();
						String suffix = oldName.substring(oldName.lastIndexOf(".")+1);
						if(!image_upload_ext.contains(suffix)){
							return setResultError("单个文件："+multipartFiles.get(i).getOriginalFilename()+"文件后缀错误！");
						}
						String relativePath = nowDateDir+File.separator+oldName;
						//以原来的名称命名,覆盖掉旧的
						String storagePath = uploadPath + relativePath;
						buffer.append("上传的文件：" + multipartFiles.get(i).getName()+";")
								.append("上传文件类型：" + multipartFiles.get(i).getContentType()+";")
								.append("老文件名称：" + multipartFiles.get(i).getOriginalFilename()+";")
								.append("保存的路径为：" + storagePath+";\n");
						Path path = Paths.get(storagePath);
						Files.write(path,multipartFiles.get(i).getBytes());
						//保存文件
						CrmFile crmFile = new CrmFile();
						crmFile.setTitle(title);
						crmFile.setName(multipartFiles.get(i).getOriginalFilename());
						crmFile.setType(Integer.parseInt(remarks));
						crmFile.setPath(relativePath);
						crmFile.setIsdel(false);
						crmFile.setCreatetime(LocalDateTime.now());
						crmFile = crmFileService.saveFile(crmFile);
						if(crmFile == null){
							return setResultError("保存文件失败！");
						}
						//返回文件列表
						AttachmentDTO attachmentDTO = new AttachmentDTO();
						attachmentDTO.setId(crmFile.getId());
						attachmentDTO.setName(oldName);
						attachmentDTO.setUrl("http://"+ip+":"+port+contextPath+"/api/showFile?id="+crmFile.getId());
						attachments.add(attachmentDTO);
					} catch (IOException e) {
						e.printStackTrace();
						return setResultError("单个文件："+multipartFiles.get(i).getOriginalFilename()+"上传失败！");
					}
					System.out.println(buffer.toString());
				}
       		 }else {
				return setResultError("上传对象为空！");
			}
        }
		return setResultSuccess(attachments);
	}
}
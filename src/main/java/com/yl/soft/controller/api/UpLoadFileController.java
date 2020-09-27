package com.yl.soft.controller.api;

import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.DateUtils;
import com.yl.soft.common.util.IOUtil;
import com.yl.soft.dto.AttachmentDTO;
import com.yl.soft.po.CrmFile;
import com.yl.soft.service.CrmFileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = {"C端模块-上传文件"})
@RestController
@RequestMapping("/api")
public class UpLoadFileController extends BaseResponseUtil {

	@Value("${custom.uploadPath}")
	private String uploadPath;

	@Value("${custom.ip}")
	private String ip;

	@Value("${server.port}")
	private String port;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Value("${custom.image_upload_ext}")
	private String image_upload_ext;

	@Autowired
	private CrmFileService crmFileService;

	/**
	 * 上传文件
	 * @param o
	 * @return
	 */
	@ApiOperation(value = "上传营业执照、企业授权书")
	@ApiImplicitParams({
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "成功")
			,@ApiResponse(code = 401, message = "token为空！")
			,@ApiResponse(code = 402, message = "token失效！")
			,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
			,@ApiResponse(code = -1, message = "系统异常")
	})
	@PostMapping("/upLoadByHttp")
	public BaseResponse<List<AttachmentDTO>> upLoadByHttp(@ApiParam(value = "文件",required = true) @RequestParam("file") MultipartFile[] multipartFiles,@ApiParam(value = "文件标记",required = true) @RequestParam("remarks") String remarks){
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
        if (multipartFiles != null && multipartFiles.length > 0) {
            for(int i = 0;i<multipartFiles.length;i++){
                try {
                    String oldName = multipartFiles[i].getOriginalFilename();
					String suffix = oldName.substring(oldName.lastIndexOf(".")+1);
					if(!image_upload_ext.contains(suffix)){
						return setResultError("单个文件："+multipartFiles[i].getOriginalFilename()+"文件后缀错误！");
					}
                    String relativePath = nowDateDir+File.separator+oldName;
                    //以原来的名称命名,覆盖掉旧的
                    String storagePath = uploadPath + relativePath;
                    buffer.append("上传的文件：" + multipartFiles[i].getName()+";")
                            .append("上传文件类型：" + multipartFiles[i].getContentType()+";")
                            .append("老文件名称：" + multipartFiles[i].getOriginalFilename()+";")
                            .append("保存的路径为：" + storagePath+";\n");
                    Path path = Paths.get(storagePath);
                    Files.write(path,multipartFiles[i].getBytes());
                    //保存文件
                    CrmFile crmFile = new CrmFile();
                    crmFile.setName(multipartFiles[i].getOriginalFilename());
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
                    return setResultError("单个文件："+multipartFiles[i].getOriginalFilename()+"上传失败！");
                }
				System.out.println(buffer.toString());
            }
        }else{
            return setResultError("上传对象为空！");
        }
		return setResultSuccess(attachments);
	}

	/**
	 * 在线预览文件
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "在线预览接口",hidden = true)
	@RequestMapping(value = "/showFile",method = RequestMethod.GET)
	public BaseResponse showFile(HttpServletResponse response, @RequestParam Integer id){
		if(StringUtils.isEmpty(id)){
			return setResultError("文件名为空！");
		}
		CrmFile crmFile = crmFileService.getById(id);
		String filePath = uploadPath +crmFile.getPath();
		try {
			String contentType=filePath.substring(filePath.lastIndexOf(".")+1);
			if("pdf".equals(contentType)) {
				contentType = "application/pdf";
			}else if("jpg".equals(contentType)||"png".equals(contentType)
					||"gif".equals(contentType)||"jpeg".equals(contentType)) {
				contentType = "image/jpeg";
			}else if("rar".equals(contentType)){
				contentType = "application/x-rar-compressed";
			}else {
				contentType = "application/pdf";
			}
			IOUtil.browseOnline(response,filePath,null,contentType);
		} catch (Exception e) {
			e.printStackTrace();
			return setResultError("文件："+filePath+"预览失败！");
		}
		return null;
	}
}
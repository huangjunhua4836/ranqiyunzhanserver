package com.yl.soft.controller.plantform;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.DateUtils;
import com.yl.soft.common.util.MinioUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.AttachmentDTO;
import com.yl.soft.po.CrmFile;
import com.yl.soft.po.EhbAbout;
import com.yl.soft.service.CrmFileService;
import com.yl.soft.service.EhbAboutService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 首页-关于表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/about")
public class AboutPlantformController extends BaseController {
    @Autowired
    public EhbAboutService ehbAboutService;

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

    @GetMapping("/list")
    public String list() {
        return "about/list";
    }

    /**
     * 查询关于列表
     * @param page
     * @param limit
     * @param ehbAbout
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbAbout ehbAbout, String startTime, String endTime) {
        QueryWrapper<EhbAbout> ehbAboutQueryWrapper = new QueryWrapper<>();
        ehbAboutQueryWrapper.like(!StringUtils.isEmpty(ehbAbout.getTitle()),"title",ehbAbout.getTitle());
        ehbAboutQueryWrapper.eq(!StringUtils.isEmpty(ehbAbout.getType()),"type",ehbAbout.getType());
        ehbAboutQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbAboutQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbAbout> ehbAbouts = ehbAboutService.list(ehbAboutQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbAbouts);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbAbout ehbAbout = new EhbAbout();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbAbout = ehbAboutService.getById(id);
        }
        modelMap.put("ehbAbout",ehbAbout);
        return "about/input";
    }

    /**
     * 添加或者修改
     * @param ehbAbout
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbAbout ehbAbout) {
        if(StringUtils.isEmpty(ehbAbout.getId())){
            ehbAbout.setCreatetime(LocalDateTime.now());
        }else{
        }
        if(ehbAboutService.saveOrUpdate(ehbAbout)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除关于
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"岗位删除ID为空！");
        }
        ehbAboutService.removeById(id);
        return setResultSuccess();
    }

    /**
     * 富文本上传图片
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/uploadImg")
    public JSONObject uploadImg(@RequestParam("uploadFile") MultipartFile multipartFile,
                                Integer remarks){
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(uploadPath)){
            jsonObject.put("error",1);
            jsonObject.put("message","服务器上传基础路径为空！");
            return jsonObject;
        }
        String nowDateDir = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
        File fileDir = new File(uploadPath+File.separator+nowDateDir);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        StringBuffer buffer = new StringBuffer();
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        if (multipartFile != null) {
            try {
                String oldName = multipartFile.getOriginalFilename();
                String suffix = oldName.substring(oldName.lastIndexOf(".")+1);
                if(!image_upload_ext.contains(suffix)){
                    jsonObject.put("error",1);
                    jsonObject.put("message","单个文件："+multipartFile.getOriginalFilename()+"文件后缀错误！");
                    return jsonObject;
                }
                String newName =System.currentTimeMillis()+new Random().nextInt()+"."+suffix;
                String relativePath = nowDateDir+"/"+newName;
                //以原来的名称命名,覆盖掉旧的
                String storagePath = uploadPath + relativePath;
                buffer.append("上传的文件：" + multipartFile.getName()+";")
                        .append("上传文件类型：" + multipartFile.getContentType()+";")
                        .append("老文件名称：" + multipartFile.getOriginalFilename()+";")
                        .append("保存的路径为：" + storagePath+";\n");
//                Path path = Paths.get(storagePath);
//                Files.write(path,multipartFile.getBytes());
                String url = MinioUtil.upload(multipartFile, relativePath);
                //保存文件
                CrmFile crmFile = new CrmFile();
                crmFile.setName(newName);
                crmFile.setType(remarks);
                crmFile.setPath(relativePath);
                crmFile.setIsdel(false);
                crmFile.setCreatetime(LocalDateTime.now());
                crmFile.setSuffix(suffix);
                crmFile = crmFileService.saveFile(crmFile);
                if(crmFile == null){
                    jsonObject.put("error",1);
                    jsonObject.put("message","保存文件失败！");
                    return jsonObject;
                }
                //返回文件列表
                attachmentDTO.setId(crmFile.getId());
                attachmentDTO.setName(oldName);
//                attachmentDTO.setUrl("http://"+ip+":"+port+contextPath+"/api/showFile?id="+crmFile.getId());
                attachmentDTO.setUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error",1);
                jsonObject.put("message","单个文件："+multipartFile.getOriginalFilename()+"上传失败！");
                return jsonObject;
            }
            System.out.println(buffer.toString());
        }else{
            jsonObject.put("error",1);
            jsonObject.put("message","上传对象为空！");
            return jsonObject;
        }
        jsonObject.put("error",0);
        jsonObject.put("url",attachmentDTO.getUrl());
        return jsonObject;
    }
}
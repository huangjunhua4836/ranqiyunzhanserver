//package com.yl.soft.controller;
//
//import com.yl.soft.common.util.StringUtilsEX;
//import com.yl.soft.common.base.R;
////import com.yl.soft.common.config.sftp.FileUploadUtil;
//import com.yl.soft.common.config.sftp.ResultEntity;
//import com.yl.soft.dto.base.BaseResult;
//import com.yl.soft.enums.base.RelationTypeEnum;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//
//@RestController
//public class ImgUploadController extends BaseController {
//    private static Log logger = LogFactory.getLog(ImgUploadController.class);
//
//    /**
//     * 文件上传目录
//     */
//    @Value("${custom.file_folder}")
//    private String file_folder;
//
//    /**
//     * 图片文件格式
//     */
//    @Value("${custom.image_upload_ext}")
//    private String image_upload_ext;
//
//    /**
//     * 图片访问路径
//     */
//    @Value("${custom.image_src}")
//    private String image_src;
//
//
//    @Value("${custom.file_host}")
//    private String file_host;
//
////    @Autowired
////    private FileUploadUtil fileUploadUtil;
//
//    @PostMapping("/img/upload")
//    public Map upload(MultipartHttpServletRequest request, String relationtype, String iskdr,
//                      HttpServletResponse response) {
//
//        BaseResult r = new BaseResult();
////        response.addHeader("Access-Control-Allow-Origin", "*");
////        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//        String name = "图片";
//        try {
////            logger.info("relationtype:" + relationtype + ",iskdr:" + iskdr);
////            Integer irelationtype = StringUtilsEX.ToIntNull(relationtype);
////            if (irelationtype == null) {
////                r.setCode(-100);
////                r.setDesc("参数类型不正确！");
////                return getrsl(r, iskdr);
////            }
//            String uploadpath = image_src;
//            List<String> rpaths = new ArrayList<String>();
//            // 获取多个file
//            for (Iterator<String> it = request.getFileNames(); it.hasNext(); ) {
//                String filePath = uploadpath;
//                SimpleDateFormat dataformat = new SimpleDateFormat("yyyyMMdd/");
//                String datePath = dataformat.format(new Date());
//                filePath += datePath;
//                String key = (String) it.next();
//                MultipartFile imgFile = request.getFile(key);
//                String fileName = imgFile.getOriginalFilename();
//                if (fileName == null || "".equals(fileName)) {
//                    r.setCode(-101);
//                    r.setDesc("请选择" + name);
//                    return getrsl(r, iskdr);
//                }
//                int Index = fileName.lastIndexOf(".");
//                String ext = fileName.substring(Index + 1, fileName.length());
//                if (!image_upload_ext.toLowerCase().contains(ext.toLowerCase())) {
//                    r.setCode(-102);
//                    r.setDesc("上传文件类型不正确，只允许上传文件类型：" + image_upload_ext + "！");
//                    return getrsl(r, iskdr);
//                }
//                String newFileName = UUID.randomUUID().toString() + "." + ext;
//
//
//
//
//
//
//
//
//
////                File f = fileUploadUtil.MultipartFileToFile(imgFile);
////                ResultEntity be = fileUploadUtil.uploadFile(f, "/home", newFileName);
//
//
//
//
////                String path = file_folder + filePath.replace("/upload/", ""); // 文件存储位置
////
////                filePath += newFileName;
////
////                File file = new File(path);
////                if (!file.exists() && !file.isDirectory()) {
////                    file.mkdirs();
////                }
////
////                File file1 = new File(path, newFileName);
////                imgFile.transferTo(file1);
////
////                long filesize = 10485760L;
////                String max = "10MB";
////                long size = imgFile.getSize();
////                if (size > filesize) {
////                    r.setCode(-103);
////                    r.setDesc("上传文件大小不能大于" + max + "!");
////                    return getrsl(r, iskdr);
////                }
////                rpaths.add("/upload/"+be.getMessage());
//            }
//            r.setCode(200);
//            r.setDesc("上传成功");
//            r.setData(rpaths);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("上传" + name + "错误：{}");
//            r = error();
//        }
//        return getrsl(r, iskdr);
//    }
//
//    private Map getrsl(BaseResult r, String iskdr) {
//        Map rsmap = new HashMap();
//        rsmap.put("code", r.getCode());
//        rsmap.put("desc", r.getDesc());
//        rsmap.put("data", r.getData());
//        Map<String, Object> map = new HashMap<>();
//        if (r.getCode() != 200) {
//            map.put("error", 1);
//            map.put("message", r.getDesc());
//        } else {
//            map.put("error", 0);
//            map.put("url", ((List<String>) r.getData()).get(0));
//        }
//        rsmap.putAll(map);
//        return rsmap;
//    }
//
//
//    @PostMapping("/img/upload/local")
//    public R uploadImg(MultipartHttpServletRequest request, String relationtype, String iskdr,
//                    HttpServletResponse response) {
//        R r;
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//        String name = "图片";
//        try {
//            Integer irelationtype = StringUtilsEX.ToIntNull(relationtype);
//            if (irelationtype == null) {
//                r = R.error(-100, "参数类型不正确！");
//                return getrsl(r, iskdr);
//            }
//            boolean bol = false;
//            RelationTypeEnum[] relationTypeEnums = RelationTypeEnum.values();
//            for (RelationTypeEnum iTypeEnum : relationTypeEnums) {
//                if (iTypeEnum.getValue().equals(irelationtype)) {
//                    bol = true;
//                    break;
//                }
//            }
//            if (!bol) {
//                r = R.error(-100, "参数类型不正确！");
//                return getrsl(r, iskdr);
//            }
//            String uploadpath = image_src;
//            List<String> rpaths = new ArrayList<String>();
//            // 获取多个file
//            for (Iterator<String> it = request.getFileNames(); it.hasNext(); ) {
//                String filePath = uploadpath;
//                SimpleDateFormat dataformat = new SimpleDateFormat("yyyyMMdd/");
//                String datePath = dataformat.format(new Date());
//                filePath += datePath;
//                String key = (String) it.next();
//                List<MultipartFile> imgFiles = request.getFiles(key);
//                for (MultipartFile imgFile : imgFiles) {
//                    if (imgFile.getOriginalFilename().length() > 0) {
//                        String fileName = imgFile.getOriginalFilename();
//                        if (fileName == null || "".equals(fileName)) {
//                            r = R.error(-101, "请选择" + name);
//                            return getrsl(r, iskdr);
//                        }
//                        int Index = fileName.lastIndexOf(".");
//                        String ext = fileName.substring(Index + 1, fileName.length());
//
//                        if (!image_upload_ext.toLowerCase().contains(ext.toLowerCase())) {
//                            r = R.error(-102, "上传文件类型不正确，只允许上传文件类型：" + image_upload_ext + "！");
//                            return getrsl(r, iskdr);
//                        }
//                        String newFileName = UUID.randomUUID().toString() + "." + ext;
//
//                        String path = file_folder + filePath.replace("/upload/", ""); // 文件存储位置
//
//                        filePath += newFileName;
//
//                        File file = new File(path);
//                        if (!file.exists() && !file.isDirectory()) {
//                            file.mkdirs();
//                        }
//                        File file1 = new File(path, newFileName);
//                        imgFile.transferTo(file1);
//
//                        long filesize = 10485760L;
//                        String max = "10MB";
//                        long size = imgFile.getSize();
//                        if (size > filesize) {
//                            r = R.error(-103, "上传文件大小不能大于" + max + "!");
//                            return getrsl(r, iskdr);
//                        }
//                        rpaths.add(file_host + filePath);
//                    }
//                }
//            }
//            r = R.ok("上传成功", rpaths);
//        } catch (Exception e) {
//            e.printStackTrace();
//            r = R.error(-100, "上传失败");
//        }
//        return getrsl(r, iskdr);
//    }
//
//    private R getrsl(R r, String iskdr) {
//        if (!StringUtilsEX.IsNullOrWhiteSpace(iskdr)) {
//            if ("1".equals(iskdr)) {
//                Map<String, Object> map = new HashMap<>();
//                if ((Integer) r.get("code") != 200) {
//                    map.put("error", 1);
//                    map.put("message", r.get("desc"));
//                } else {
//                    map.put("error", 0);
//                    map.put("url", ((List<String>) r.get("data")).get(0));
//                }
//                r.putAll(map);
//            }
//        }
//        return r;
//    }
//}

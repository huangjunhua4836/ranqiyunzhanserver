package com.yl.soft.controller.plantform;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.IOUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.po.EhbWonderfulAtlas;
import com.yl.soft.po.EhbWonderfulVideo;
import com.yl.soft.po.Fengniaoboothno;
import com.yl.soft.service.EhbLiveBroadcastService;
import com.yl.soft.service.EhbWonderfulAtlasService;
import com.yl.soft.service.EhbWonderfulVideoService;
import com.yl.soft.service.FengniaoboothnoService;
import com.yl.soft.vo.FengNiaoVo;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 精彩图集表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/atlas")
public class WonderfulAtlasController extends BaseController {
    @Autowired
    public EhbWonderfulAtlasService ehbWonderfulAtlasService;
    @Autowired
    public EhbLiveBroadcastService ehbLiveBroadcastService;
    @Autowired
    public FengniaoboothnoService fengniaoboothnoService;

    @GetMapping("/list")
    public String list(ModelMap modelMap) {
        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel", 1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts", ehbLiveBroadcasts);
        return "atlas/list";
    }

    /**
     * 查询列表
     *
     * @param page
     * @param limit
     * @param ehbWonderfulAtlas
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbWonderfulAtlas ehbWonderfulAtlas, String startTime, String endTime) {
        QueryWrapper<EhbWonderfulAtlas> ehbWonderfulAtlasQueryWrapper = new QueryWrapper<>();
        ehbWonderfulAtlasQueryWrapper.eq(!StringUtils.isEmpty(ehbWonderfulAtlas.getLiveId()), "live_id", ehbWonderfulAtlas.getLiveId());
        ehbWonderfulAtlasQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime), "createtime", startTime, endTime);
        ehbWonderfulAtlasQueryWrapper.eq("isdel", 1);
        ehbWonderfulAtlasQueryWrapper.orderByDesc("sort");
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        List<EhbWonderfulAtlas> ehbWonderfulAtlases = ehbWonderfulAtlasService.list(ehbWonderfulAtlasQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbWonderfulAtlases);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int) pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个添加或者修改页面
     *
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbWonderfulAtlas ehbWonderfulAtlas = new EhbWonderfulAtlas();
        if ("add".equals(type)) {

        } else if ("update".equals(type)) {
            ehbWonderfulAtlas = ehbWonderfulAtlasService.getById(id);
        }
        modelMap.put("ehbWonderfulAtlas", ehbWonderfulAtlas);

        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel", 1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts", ehbLiveBroadcasts);

        return "atlas/input";
    }

    /**
     * 添加或者修改
     *
     * @param ehbWonderfulAtlas
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbWonderfulAtlas ehbWonderfulAtlas) {
        if (StringUtils.isEmpty(ehbWonderfulAtlas.getId())) {
            ehbWonderfulAtlas.setCreatetime(LocalDateTime.now());
            ehbWonderfulAtlas.setIsdel(1);
            EhbWonderfulAtlas sortAtlas = ehbWonderfulAtlasService.lambdaQuery()
                    .orderByDesc(EhbWonderfulAtlas::getSort).last("limit 1").one();
            if(sortAtlas!=null && sortAtlas.getSort()!=null){
                ehbWonderfulAtlas.setSort(sortAtlas.getSort()+1);
            }else{
                ehbWonderfulAtlas.setSort(1);
            }
        } else {
        }
        if (ehbWonderfulAtlasService.saveOrUpdate(ehbWonderfulAtlas)) {
            return setResultSuccess();
        } else {
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除
     *
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String id) {
        System.out.println("ok");
        if (StringUtils.isEmpty(id)) {
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(), "岗位删除ID为空！");
        }
        ehbWonderfulAtlasService.removeById(id);
        return setResultSuccess();
    }

    /**
     * 跳转到单个添加或者修改页面
     *
     * @param id
     * @return
     */
    @GetMapping("/inputMultiple")
    public String inputMultiple(String id, String type, ModelMap modelMap) {
        modelMap.put("ehbWonderfulAtlas", new EhbWonderfulAtlas());

        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel", 1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts", ehbLiveBroadcasts);

        return "atlas/inputmultiple";
    }

    /**
     * 添加或者修改
     *
     * @param ehbWonderfulAtlas
     * @return
     */
    @PostMapping("/saveOrUpdateMuilt")
    @ResponseBody
    public BaseResponse saveOrUpdateMuilt(EhbWonderfulAtlas ehbWonderfulAtlas) {
        StringBuffer buffer = new StringBuffer(ehbWonderfulAtlas.getImgUrl());
        buffer = buffer.deleteCharAt(buffer.lastIndexOf(","));
        String img[] = buffer.toString().split(",");

        List<EhbWonderfulAtlas> addAtlasList = Arrays.asList(img).stream().map(i->{
            EhbWonderfulAtlas addAtlas = new EhbWonderfulAtlas();
            BeanUtil.copyProperties(ehbWonderfulAtlas,addAtlas);
            try {
                addAtlas.setWide(IOUtil.getImgWidth(new URL(i)));
                addAtlas.setHigh(IOUtil.getImgHeight(new URL(i)));
            }catch (Exception e){
                e.printStackTrace();
            }
            addAtlas.setImgUrl(i);
            addAtlas.setCreatetime(LocalDateTime.now());
            addAtlas.setIsdel(1);
            EhbWonderfulAtlas sortAtlas = ehbWonderfulAtlasService.lambdaQuery()
                    .orderByDesc(EhbWonderfulAtlas::getSort).last("limit 1").one();
            if(sortAtlas!=null && sortAtlas.getSort()!=null){
                addAtlas.setSort(sortAtlas.getSort()+1);
            }else{
                addAtlas.setSort(1);
            }
            addAtlas.setWide(addAtlas.getWide()==-1?1000:addAtlas.getWide());
            addAtlas.setHigh(addAtlas.getHigh()==-1?900:addAtlas.getHigh());
            return addAtlas;
        }).collect(Collectors.toList());
        if (ehbWonderfulAtlasService.saveBatch(addAtlasList)) {
            return setResultSuccess();
        } else {
            return setResultError("操作失败！");
        }
    }

    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) {
        List<FengNiaoVo> list = new ArrayList<>();
        list.add(new FengNiaoVo("zhangsan", "1231"));
        list.add(new FengNiaoVo("zhangsan", "1232"));
        list.add(new FengNiaoVo("zhangsan", "1233"));
        list.add(new FengNiaoVo("zhangsan", "1234"));
        list.add(new FengNiaoVo("zhangsan", "1235"));
        list.add(new FengNiaoVo("zhangsan", "1236"));
        list.add(new FengNiaoVo("zhangsan", "1237"));
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("birthDay", "生日");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(1, "申请人员信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        try {
            String name = URLEncoder.encode("中文名称", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        }catch (Exception e){
            e.printStackTrace();
        }
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    //处理文件上传
    @ResponseBody//返回json数据
    @RequestMapping(value = "/excelImport", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println(file);
        if (file.isEmpty()) {
            System.out.println("文件为空！");
            return "excelImport";
        }
        // 1.获取上传文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            //return ResponseData.fail(ResponseCodeEnum.ERROR_PARAM_INVALID);
            e.printStackTrace();
        }
//        // 2.应用HUtool ExcelUtil获取ExcelReader指定输入流和sheet
//        ExcelReader excelReader = ExcelUtil.getReader(inputStream, "导入材料清单");
//        // 可以加上表头验证
//        // 3.读取第二行到最后一行数据
//        List<List<Object>> read = excelReader.read(2, excelReader.getRowCount());
//        for (List<Object> objects : read) {
//            // objects.get(0),读取某行第一列数据
//            // objects.get(1),读取某行第二列数据
//            System.out.println(objects.get(0));
//        }


        //调用用 hutool 方法读取数据 默认调用第一个sheet
        ExcelReader excelReader = ExcelUtil.getReader(inputStream);
        //从第二行开始获取数据   excelReader.read的结果是一个2纬的list，外层是行，内层是行对应的所有列
        //读取方式1
        List<List<Object>> read = excelReader.read(1, excelReader.getRowCount());
        //System.out.println("数据:"+read);
        List<Fengniaoboothno> excels = new ArrayList<>();
        //循环获取的数据
        for (int i = 0; i < read.size(); i++) {
            List list = read.get(i);
            String fid = StringUtils.isEmpty(list.get(0))?"":list.get(0).toString().trim().replace("\t","");
            String boothNoName = StringUtils.isEmpty(list.get(1))?"":list.get(1).toString().trim().replace("\t","");
            Fengniaoboothno fengniaoboothno = new Fengniaoboothno();
            fengniaoboothno.setFid(fid);
            fengniaoboothno.setBoothnoname(boothNoName);
            excels.add(fengniaoboothno);
        }
        fengniaoboothnoService.saveOrUpdateBatch(excels);


        //读取方式2
        //读取为Bean列表，Bean中的字段名为标题，字段值为标题对应的单元格值。
//        List<FengNiaoVo> excels = excelReader.readAll(FengNiaoVo.class);

//        service.addList(excels);
        return "导入成功";
    }
}
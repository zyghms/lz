package com.zygh.lz.controller;

import com.zygh.lz.entity.Xlevelservice;
import com.zygh.lz.service.PatrolrecordService;
import com.zygh.lz.service.XlevelserviceService;
import com.zygh.lz.vo.ResultBean;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
@RestController
public class XlevelserviceController {

    @Autowired
    private PatrolrecordService patrolrecordService;
    @Autowired
    private XlevelserviceService  xlevelserviceService;



    //按大队统计等级勤务所有应到人数
    @GetMapping("selectorderlyAllqw")
    public ResultBean selectorderlyAll(String sectionName){
        return xlevelserviceService.selectorderlyAll(sectionName);
    }
    //按部门
    @GetMapping("selectorderlydjyd")
    public ResultBean selectorderlydjyd(String sectionName){
        return xlevelserviceService.selectorderlydjyd(sectionName);
    }

    //按等级按大队查询等级勤务
    @GetMapping("selectxleveBydj")
    public ResultBean selectxleveBydj(Integer hierarchy,String sectionName){
        return xlevelserviceService.selectxleveBydj(hierarchy,sectionName);
    }

    //按等级按大队查询等级勤务
    @GetMapping("selectXlevedJ")
    public ResultBean selectXlevedJ(Integer hierarchy){
        return xlevelserviceService.selectXlevedJ(hierarchy);
    }

    //特殊勤务左边下拉框
    @GetMapping("selectSpecialService")
    public ResultBean selectSpecialService(){
       return xlevelserviceService.selectSpecialService();
    }

    //删除特殊勤务
    @GetMapping("delSpecialService")
    public ResultBean delSpecialService(Integer id ){
        return xlevelserviceService.delSpecialService(id);
    }

    //添加勤务
    @GetMapping("addSpecialService")
    public ResultBean addSpecialService(Xlevelservice xlevelservice ){
        return xlevelserviceService.addSpecialService(xlevelservice);
    }

    //修改特殊勤务
    @GetMapping(" updateSpecialService")
    public ResultBean updateSpecialService(Xlevelservice xlevelservice){
        return xlevelserviceService.updateSpecialService(xlevelservice);
    }

    @RequestMapping(value = "exportPersonServiceExcel")  //导出勤务报表
    @ResponseBody
    public void exportPersonServiceExcel(HttpServletResponse response, String battalion, Integer type, String level) throws Exception {

        //  String realPath = request.getRealPath("/Excel/");
        String property = System.getProperty("user.dir")+"\\Excel\\";

        File file1 = new File(property);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        String fileName = UUID.randomUUID().toString()+"导出特殊勤务表.xls";

        File file = new File(property+fileName);

        try {
            file.createNewFile();
            WritableWorkbook wwb = Workbook.createWorkbook(file);


            //start设置每列的宽度
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            //给sheet电子版中所有的列设置默认的列的宽度;
            ws.getSettings().setDefaultColumnWidth(8);

            //分别给列设置不同的宽度;
            ws.setColumnView(0, 20);

            List<HashMap> ddDaySumList = null;

            if (type == 1){
                if (StringUtils.isEmpty(battalion)){
                    ddDaySumList = patrolrecordService.countZD(battalion);
                    battalion = "";
                }else {
                    ddDaySumList = (List<HashMap>) patrolrecordService.countZD(battalion).get(0).get("zdCount");
                }
            }else if (type == 2){
                if (StringUtils.isEmpty(level)){
                    ddDaySumList = (List<HashMap>) xlevelserviceService.selectorderlydjyd(battalion).getData();
                    battalion = "";
                }else {
                    int lv = Integer.parseInt(level);
                    List<HashMap> sumList = (List<HashMap>) xlevelserviceService.selectorderlydjyd(battalion).getData();
                    ddDaySumList = (List<HashMap>) sumList.get(lv - 1).get("zdCount");
                    battalion = level+"级岗";
                }
            }


            //start 合并单元格（左上列，左上行，右下列，右下行）
            ws.mergeCells(0, 0, 3, 0);
            Label sheetTitle = new Label(0, 0, battalion+"特殊勤务",getHeaderCellStyle(12));
            ws.addCell(sheetTitle);

            //（列，行）
            Label sheetTitle2 = new Label(0, 1, "勤务名称",getHeaderCellStyle(10));
            ws.addCell(sheetTitle2);

            //（列，行）
            Label sheetTitle3 = new Label(1, 1, "勤务管辖区域",getHeaderCellStyle(10));
            ws.addCell(sheetTitle3);

            //列，行
            Label sheetTitle4 = new Label(2, 1, "执勤时间",getHeaderCellStyle(10));
            ws.addCell(sheetTitle4);

            //列，行
            Label sheetTitle5 = new Label(3, 1, "警力明细",getHeaderCellStyle(10));
            ws.addCell(sheetTitle5);

            int c = 2;
            for (int i = 0;i<ddDaySumList.size();i++) {
                int h = i + 2;
                c++;

                if (!StringUtils.isEmpty(battalion)){
                    String zdname = ddDaySumList.get(i).get("name")+"";

                    if (StringUtils.isEmpty(zdname)){
//                        zdname = "大队领导";
                        zdname = "大队领导";
                    }
                    Label name = new Label(0, h, zdname,getHeaderCellStyle(10));
                    ws.addCell(name);
                    //勤务名称
                    Label count1 = new Label(1, h, ddDaySumList.get(i).get("YDnum")+"",getHeaderCellStyle(11));
                    ws.addCell(count1);
                    //勤务管辖区域
                    Label count2 = new Label(2, h, ddDaySumList.get(i).get("ondutytime")+"",getHeaderCellStyle(11));
                    ws.addCell(count2);
                    //执勤时间
                    Label count3 = new Label(3, h, ddDaySumList.get(i).get("gfZXL")+"",getHeaderCellStyle(11));
                    ws.addCell(count3);
                }else {
                    Label name = new Label(0, h, (String) ddDaySumList.get(i).get("ddName"),getHeaderCellStyle(10));
                    ws.addCell(name);
                    //人员名称
                    Label count1 = new Label(1, h, ddDaySumList.get(i).get("ddYDnum")+"",getHeaderCellStyle(11));
                    ws.addCell(count1);
                    //分配所在区域
                    Label count2 = new Label(2, h, ddDaySumList.get(i).get("ddSDnum")+"",getHeaderCellStyle(11));
                    ws.addCell(count2);
                    //
                    Label count3 = new Label(3, h, ddDaySumList.get(i).get("gfZXL")+"",getHeaderCellStyle(11));
                    ws.addCell(count3);
                }

            }



            for (int i=0; i<=c ;i++){
                //            行数    高
                ws.setRowView(i, 400, false); //设置行高
            }
            wwb.write();
            wwb.close();

            //获取要下载的文件名
            String excelName = fileName;

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //设置content-disposition响应头控制浏览器以下载的形式打开文件
            response.addHeader("Content-Disposition", "attachment;filename="+excelName);

            //获取要下载的文件的绝对路径
            String realPath = property+fileName;

            InputStream in = null;
            OutputStream out = null;
            try {
                //获取要下载的文件输入流
                in = new FileInputStream(realPath);
                int len = 0;
                //创建数据缓冲区
                byte[] buffer = new byte[1024];
                //通过response对象获取outputStream流
                out = response.getOutputStream();
                //将FileInputStream流写入到buffer缓冲区
                while((len = in.read(buffer)) > 0) {
                    //使用OutputStream将缓冲区的数据输出到浏览器
                    out.write(buffer,0,len);
                }
                out.flush();
                in.close();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally{
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw  e;

        }


    }
    /**
     * 表头单元格样式的设定 单元格为黑色
     */
    public WritableCellFormat getHeaderCellStyle(int a) throws Exception {
        /*
         * WritableFont.createFont("宋体")：设置字体为宋体
         * 10：设置字体大小
         * WritableFont.BOLD:设置字体加粗（BOLD：加粗     NO_BOLD：不加粗）
         * false：设置非斜体
         * UnderlineStyle.NO_UNDERLINE：没有下划线
         */
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
                a,
                WritableFont.NO_BOLD,
                false,
                UnderlineStyle.NO_UNDERLINE);

        WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);
        try {
            //添加字体设置
            headerFormat.setFont(font);
            //设置单元格背景色：表头为黄色
            // headerFormat.setBackground(Colour.YELLOW);
            //设置表头表格边框样式
            //整个表格线为细线、黑色
            headerFormat.setBorder(Border.ALL,  BorderLineStyle.THIN, Colour.BLACK);
            headerFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        } catch (WriteException e) {
            System.out.println("表头单元格样式设置失败！");
        }
        return headerFormat;
    }
}

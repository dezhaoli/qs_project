package com.qs.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qs.common.dtgrid.model.Column;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.GridUtils;
import com.qs.common.enums.ExportType;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.commons.collections.MapUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  //@Author:zun.wei, @Date:2017/4/6 18:51
 *  输出工具
 * Created by zun.wei on 2017/4/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class MyExportUtils {


    public static void export(HttpServletResponse response, Pager pager)
            throws Exception {
        // 获取文件名
        String fileName = "datas";
        fileName = URLEncoder.encode(pager.getExportFileName(), "UTF-8");
        // 导出excel
        if (ExportType.EXCEL.name().equalsIgnoreCase(pager.getExportType())) {
            MyExportUtils.exportExcel(response, pager, pager.getExportDatas(),
                    fileName);
            return;
        }
    }


    public static void exportAll(HttpServletResponse response, Pager pager, List<?> list)
            throws Exception {
        List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
        String json = JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);
        datas = JSON.parseObject(json, datas.getClass());
        pager.setExportDatas(datas);
        export(response,pager);
    }


    private static void exportExcel(HttpServletResponse response, Pager pager,
                                    List<Map<String, Object>> exportDatas, String fileName)
            throws Exception {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        // 执行文件写入
        response.setHeader("Content-Disposition", "attachment;filename="
                + fileName + ".xls");
        // 获取输出流
        OutputStream outputStream = response.getOutputStream();
        // 定义Excel对象
        WritableWorkbook book = Workbook.createWorkbook(outputStream);
        // 创建Sheet页
        WritableSheet sheet = book.createSheet("数据", 0);
        // 冻结表头
        SheetSettings settings = sheet.getSettings();
        settings.setVerticalFreeze(1);
        // 定义表头字体样式、表格字体样式
        WritableFont headerFont = new WritableFont(
                WritableFont.createFont("Lucida Grande"), 9, WritableFont.BOLD);
        WritableFont bodyFont = new WritableFont(
                WritableFont.createFont("Lucida Grande"), 9,
                WritableFont.NO_BOLD);
        WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
        WritableCellFormat bodyCellFormat = new WritableCellFormat(bodyFont);
        // 设置表头样式：加边框、背景颜色为淡灰、居中样式
        headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        headerCellFormat.setBackground(Colour.PALE_BLUE);
        headerCellFormat.setAlignment(Alignment.CENTRE);
        headerCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        // 设置表格体样式：加边框、居中
        bodyCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        bodyCellFormat.setAlignment(Alignment.CENTRE);
        bodyCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        // 判断一下表头数组是否有数据
        if (pager.getExportColumns() != null
                && pager.getExportColumns().size() > 0) {
            // 循环写入表头
            int seq = 0;
            for (Column column : pager.getExportColumns()) {
                sheet.addCell(new Label(seq, 0, column.getTitle(),
                        headerCellFormat));
                seq++;
            }
            // 判断表中是否有数据
            if (exportDatas != null && exportDatas.size() > 0) {
                // 循环写入表中数据
                for (int i = 0; i < exportDatas.size(); i++) {
                    Map<String, Object> record = (Map<String, Object>) exportDatas
                            .get(i);
                    seq = 0;
                    for (int j = 0; j < pager.getExportColumns().size(); j++) {
                        Column column = pager.getExportColumns().get(j);
                        String content = MapUtils.getString(record,column.getId());
                        // 如果内容未被处理则进行格式化
                        if ("返现状态".equals(column.getTitle())) {
                            if ("0".equals(content)) {//0未审核1审核通过2发放
                                content = "未审核";
                            } else if ("1".equals(content)) {
                                content = "审核通过";
                            } else if ("2".equals(content)) {
                                content = "已发放";
                            }
                        }
                        if ("操作".equals(column.getTitle())) {
                            if ("0".equals(content)) {//0未审核1审核通过2发放
                                content = "未审核";
                            } else if ("1".equals(content)) {
                                content = "未支付";
                            } else if ("2".equals(content)) {
                                content = "已支付";
                            }
                        }
                        if ("游戏类型".equals(column.getTitle())) {
                            int  value=Integer.parseInt(content);
                            switch (value)
                        	{
                        	case 0:
                        		content="牵手跑得快";
                        		break;
                        	case 2:
                        		content="牵手跑胡子";
                        		break;
                        	case 3:
                        		content="疯狂斗牛OL";
                        		break;
                        	case 4:
                        		content="牵手湖南麻将";	
                        	case 9:
                        		content="牵手木虱";	
                        		break;
                        	case 5:
                        		content="金溪麻将";	
                        		break;
                        	}
                        }
                        if ("支付状态".equals(column.getTitle())) {
                        	
                            if ("0".equals(content)) {//0未审核1审核通过2发放
                                content = "支付失败";
                            } else if ("1".equals(content)) {
                                content = "支付成功";
                            } 
                        }
                        if ("支付类型".equals(column.getTitle())) {
                        	
                            if ("1".equals(content)) {//0未审核1审核通过2发放
                                content = "自定义支付";
                            } else if ("2".equals(content)) {
                                content = "周结算";
                            } 
                        }
                        if (!pager.getExportDataIsProcessed()) {
                            content = GridUtils.formatContent(column, content);
                        }
                        sheet.addCell(new Label(seq, i + 1, content,
                                bodyCellFormat));
                        seq++;
                    }
                }
            }
            // 写入Excel工作表
            book.write();
            // 关闭Excel工作薄对象
            book.close();
            // 关闭流
            outputStream.flush();
            outputStream.close();
            outputStream = null;
        }
    }

}

package com.zhengbing.cloud.utils.file.excel;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Collection;

/**
 * @Description:
 * @author: zhengbing_vendor
 * @date: 2019/8/2
 */
public class ExportExcelWrapper<T> extends ExportExcelUtil<T> {
    /**
     * <p>
     * 导出带有头部标题行的Excel <br>
     * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
     * </p>
     *
     * @param sheetName sheet名称
     * @param headers 头部标题集合
     * @param dataset 数据集合
     * @paran response Http响应对象
     * @param version 2003 或者 2007，不传时默认生成2003版本
     */
    public void exportExcel( String fileName, String sheetName, String[] headers, Collection<T> dataset, HttpServletResponse response, String version) {
        try {
            String extendName = ExportExcelUtil.EXCEL_FILE_EXTEND_NAME_03;
            if ( ExportExcelUtil.EXCEL_FILE_2007.equals( version ) ){
                extendName = ExportExcelUtil.EXCEL_FILE_EXTEND_NAME_07;
            }
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + extendName);
            if( StringUtils.isBlank(version) || EXCEL_FILE_2003.equals(version.trim())){
                exportExcel2003(sheetName, headers, dataset, response.getOutputStream(), ExportExcelUtil.PATTERN_DATE_TIME);
            }else{
                exportExcel2007(sheetName, headers, dataset, response.getOutputStream(), ExportExcelUtil.PATTERN_DATE_TIME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
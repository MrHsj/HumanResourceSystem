package com.stu.hrs.controller;

import com.stu.hrs.domain.Document;
import com.stu.hrs.domain.User;
import com.stu.hrs.service.HrsService;
import com.stu.hrs.util.HrsConstants;
import com.stu.hrs.util.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-16 19:29
 */
@Controller
public class DocumentController {
    @Autowired
    @Qualifier("hrsService")
    private HrsService hrsService;

    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(Model model, Integer pageIndex, @ModelAttribute Document document) {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        // 查询文档信息
        List<Document> documents = hrsService.findDocument(document, pageModel);
        model.addAttribute("documents", documents);
        model.addAttribute("pageModel", pageModel);
        return "document/document";
    }

    @RequestMapping(value = "/document/addDocument")
    public ModelAndView addDocument(String flag, @ModelAttribute Document document, ModelAndView mv, HttpSession session) {
        if ("1".equals(flag)) {
            mv.setViewName("document/showAddDocument");
        } else {
            // 上传文件路径
            String path = session.getServletContext().getRealPath("/upload/");
            // 上传文件名
            String fileName = document.getFile().getOriginalFilename();
            // 将上传文件保存到一个目标文件当中
            try {
                document.getFile().transferTo(new File(path + File.separator + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 插入数据库，设置fileName
            document.setFileName(fileName);
            // 设置关联的User对象
            User user = (User) session.getAttribute(HrsConstants.USER_SESSION);
            document.setUser(user);
            hrsService.addDocument(document);
            mv.setViewName("document/selectDocument");
        }
        return mv;
    }

    @RequestMapping(value = "/document/removeDocument")
    public ModelAndView removeDocument(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");
        for(String id : idArray) {
            hrsService.removeDocumentById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/document/selectDocument");
        return mv;
    }

    @RequestMapping(value = "/document/updateDocument")
    public ModelAndView updateDocument(String flag, @ModelAttribute Document document, ModelAndView mv) {
        if ("1".equals(flag)) {
            // 根据id查询文档
            Document target = hrsService.findDocumentById(document.getId());
            mv.addObject("document", target);
            mv.setViewName("document/showUpdateDocument");
        } else {
            // 执行修改操作
            hrsService.modifyDocument(document);
            mv.setViewName("redirect:/document/selectDocument");
        }
        return mv;
    }

    @RequestMapping(value = "/document/download")
    public ResponseEntity<byte[]> download(Integer id, HttpSession session) throws Exception {
        // 根据id查询文档
        Document target = hrsService.findDocumentById(id);
        String fileName = target.getFileName();
        // 上传路径
        String path = session.getServletContext().getRealPath("/upload/");
        // 获取要下载文件的File对象
        File file = new File(path + File.separator + fileName);
        // 创建springframework的HttpHeaders对象
        HttpHeaders headers = new HttpHeaders();
        // 下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        // application/octet-stream：二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}

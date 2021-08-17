package com.stu.hrs.controller;

import com.stu.hrs.domain.Job;
import com.stu.hrs.service.HrsService;
import com.stu.hrs.util.PageModel;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimePartDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-16 18:07
 */
@Controller
public class JobController {

    @Autowired
    @Qualifier("hrsService")
    private HrsService hrsService;

    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Model model, Integer pageIndex, @ModelAttribute Job job) {
        System.out.println("selectJob -->" + job);
        PageModel pageModel = new PageModel();
        if(pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        // 查询用户信息
        List<Job> jobs = hrsService.findJob(job, pageModel);
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageModel", pageModel);
        return "job/job";
    }

    @RequestMapping(value = "/job/removeJob")
    public ModelAndView removeJob(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");
        for(String id : idArray) {
            hrsService.removeJobById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/job/selectJob");
        return  mv;
    }

    @RequestMapping(value = "/job/addJob")
    public ModelAndView addJob(String flag, @ModelAttribute Job job, ModelAndView mv) {
        if ("1".equals(flag)) {
            // 设置跳转到添加页面
            mv.setViewName("redirect:/job/selectJob");
        } else {
            hrsService.addJob(job);
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }

    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateJob(String flag, @ModelAttribute Job job, ModelAndView mv) {
        if ("1".equals(flag)) {
            Job target = hrsService.findJobById(job.getId());
            mv.addObject("job", target);
            mv.setViewName("job/showUpdateJob");
        } else {
            // 执行修改操作
            hrsService.modifyJob(job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }
}

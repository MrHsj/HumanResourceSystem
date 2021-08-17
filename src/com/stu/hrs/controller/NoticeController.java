package com.stu.hrs.controller;

import com.stu.hrs.domain.Notice;
import com.stu.hrs.domain.User;
import com.stu.hrs.service.HrsService;
import com.stu.hrs.util.HrsConstants;
import com.stu.hrs.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin.dom.html.HTMLConstants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-16 18:30
 */
@Controller
public class NoticeController {
    @Autowired
    @Qualifier("hrsService")
    private HrsService hrsService;

    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex, @ModelAttribute Notice notice) {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        // 查询通知信息
        List<Notice> notices = hrsService.findNotice(notice, pageModel);
        model.addAttribute("notices", notices);
        model.addAttribute("pageModel", pageModel);
        return "notice/notice";
    }

    @RequestMapping(value = "/notice/removeNotice")
    public ModelAndView removeNotice(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");
        for(String id: idArray) {
            // 根据id删除公告
            hrsService.removeNoticeById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("/notice/selectNotice");
        return mv;
    }

    @RequestMapping(value = "/notice/addNotice")
    public ModelAndView addNotice(String flag, @ModelAttribute Notice notice, ModelAndView mv, HttpSession session) {
        if ("1".equals(flag)) {
            mv.setViewName("notice/showAddNotice");
        } else {
            User user = (User) session.getAttribute(HrsConstants.USER_SESSION);
            notice.setUser(user);
            hrsService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        return mv;
    }

    @RequestMapping(value = "/notice/updateNotice")
    public ModelAndView updateNotice(String flag, @ModelAttribute Notice notice, ModelAndView mv, HttpSession session) {
        if ("1".equals(flag)) {
            Notice target = hrsService.findNoticeById(notice.getId());
            mv.addObject("notice", target);
            mv.setViewName("notice/showUpdateNotice");
        } else {
            hrsService.modifyNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        return mv;
    }
}

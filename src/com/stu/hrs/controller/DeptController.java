package com.stu.hrs.controller;

import com.stu.hrs.domain.Dept;
import com.stu.hrs.service.HrsService;
import com.stu.hrs.util.PageModel;
import org.apache.ibatis.annotations.Delete;
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
 * @date 2021-08-15 18:17
 */
@Controller
public class DeptController {

    @Autowired
    @Qualifier("hrsService")
    private HrsService hrsService;

    /**
     * 处理部门查找请求
     * @param model Model对象
     * @param pageIndex 查找的页数
     * @param dept 查找条件
     * @return 部门查找返回结果视图
     */
    @RequestMapping(value = "/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex, @ModelAttribute Dept dept) {
        System.out.println("selectDept -->");
        System.out.println("pageIndex = " + pageIndex);
        System.out.println("dept" + dept);
        PageModel pageModel = new PageModel();
        System.out.println("getPageIndex = " + pageModel.getPageIndex());
        System.out.println("getPageSize = " + pageModel.getPageSize());
        System.out.println("getRecordCount = " + pageModel.getRecordCount());
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        List<Dept> depts = hrsService.findDept(dept, pageModel);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);
        return "dept/dept";
    }

    /**
     * 处理删除部门请求
     * @param ids 要删除的所有部门的id字符串
     * @param mv ModelAndView对象
     * @return 处理后的视图
     */
    @RequestMapping(value = "/dept/removeDept")
    public ModelAndView removeDept(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            hrsService.removeDeptById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/dept/selectDept");
        return mv;
    }

    /**
     * 处理添加部门请求
     * @param flag 标记，1表示跳转到添加界面，2表示执行添加操作
     * @param dept 要添加的部门对象
     * @param mv ModelAndView对象
     * @return 返回对应的视图
     */
    @RequestMapping(value = "/dept/addDept")
    public ModelAndView addDept(String flag, @ModelAttribute Dept dept, ModelAndView mv) {
        if ("1".equals(flag)) {
            mv.setViewName("dept/showAddDept");
        } else {
            hrsService.addDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;
    }

    /**
     * 处理更新部门请求
     * @param flag 标记，1表示跳转到更新界面，2表示执行更新操作
     * @param dept 要添加的部门对象
     * @param mv ModelAndView对象
     * @return 返回对应的视图
     */
    @RequestMapping(value = "/dept/updateDept")
    public ModelAndView updateDept(String flag, @ModelAttribute Dept dept, ModelAndView mv) {
        if ("1".equals(flag)) {
            Dept target = hrsService.findDeptById(dept.getId());
            mv.addObject("dept", target);
            mv.setViewName("dept/showUpdateDept");
        } else {
            hrsService.modifyDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;
    }



}

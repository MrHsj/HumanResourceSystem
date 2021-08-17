package com.stu.hrs.controller;

import com.stu.hrs.domain.Dept;
import com.stu.hrs.domain.Employee;
import com.stu.hrs.domain.Job;
import com.stu.hrs.service.HrsService;
import com.stu.hrs.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-16 16:17
 */
@Controller
public class EmployeeController {
    @Autowired
    @Qualifier("hrsService")
    private HrsService hrsService;

    @RequestMapping(value = "/employee/selectEmployee")
    public String selectEmployee(Integer pageIndex, Integer job_id, Integer dept_id, @ModelAttribute Employee employee, Model model) {
        this.genericAssociation(job_id, dept_id, employee);
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        // 查询职位信息，用于模糊查询
        List<Job> jobs = hrsService.findAllJob();
        // 查询部门信息，用于模糊查询
        List<Dept> depts = hrsService.findAllDept();
        // 查询员工信息
        List<Employee> employees = hrsService.findEmployee(employee, pageModel);
        // 设置Model数据
        model.addAttribute("employees", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);
        // 返回员工页面
        return "employee/employee";
    }

    /**
     * 处理添加员工请求
     * @param flag 标记
     * @param job_id 职位id
     * @param dept_id 部门id
     * @param employee 员工对象
     * @param mv ModelAndView对象
     * @return 返回对应的视图界面
     */
    @RequestMapping(value = "employee/addEmployee")
    public ModelAndView addEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute Employee employee, ModelAndView mv) {
        if ("1".equals(flag)) {
            // 查询职位信息
            List<Job> jobs = hrsService.findAllJob();
            // 查询部门信息
            List<Dept> depts = hrsService.findAllDept();
            // 设置Model数据
            mv.addObject("jobs", jobs);
            mv.addObject("depts", depts);
            // 返回添加员工页面
            mv.setViewName("employee/showAddEmployee");
        } else {
            // 判断是否有关联对象传递，如果有，创建关联对象
            this.genericAssociation(job_id, dept_id, employee);
            hrsService.addEmployee(employee);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/employee/selectEmployee");
        }
        return mv;
    }

    /**
     * 处理删除员工请求
     * @param ids 需要删除的员工id集合字符串
     * @param mv ModelAndView
     * @return 返回删除对应员工后的员工界面
     */
    @RequestMapping(value = "/employee/removeEmployee")
    public ModelAndView removeEmployee(String ids, ModelAndView mv) {
        // 分解字符串
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            // 根据id删除员工
            hrsService.removeEmployeeById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/employee/selectEmployee");
        return mv;
    }

    @RequestMapping(value = "/employee/updateEmployee")
    public ModelAndView updateEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute Employee employee, ModelAndView mv) {
        if ("1".equals(flag)) {
            // 根据id查询员工
            Employee target = hrsService.findEmployeeById(employee.getId());
            // 需要查询职位信息
            List<Job> jobs = hrsService.findAllJob();
            // 需要查询部门信息
            List<Dept> depts = hrsService.findAllDept();
            // 设置Model数据
            mv.addObject("jobs", jobs);
            mv.addObject("depts", depts);
            mv.addObject("employee", target);
            mv.setViewName("employee/showUpdateEmployee");
        } else {
            //创建并封装关联对象
            this.genericAssociation(job_id, dept_id, employee);
            System.out.println("updateEmployee -->" + employee);
            // 执行修改操作
            hrsService.modifyEmployee(employee);
            mv.setViewName("redirect:/employee/selectEmployee");
        }
        return mv;
    }
    /**
     * 由于部门和职位在Employee中是对象关联映射，所以不能直接接收参数，需要创建Job对象和Dept对象
     * @param job_id 职位id
     * @param dept_id 部门id
     * @param employee 员工对象
     */
    private void genericAssociation(Integer job_id, Integer dept_id, Employee employee) {
        if (job_id != null) {
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id != null) {
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }
}

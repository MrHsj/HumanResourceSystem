package com.stu.hrs.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理用户请求控制器
 * @author hsj
 * @version 1.0
 * @date 2021-08-04 15:16
 */
@Controller
public class UserController {
  /** 自动注入UserService */
    @Autowired
    @Qualifier("hrsService")
    private HrsService hrsService;

    /**
     * 处理登录请求
     * @param loginName 登录名
     * @param password 密码
     * @param session session
     * @param mv ModelAndView对象
     * @return mv
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("loginName") String loginName, @RequestParam("password") String password,
                              HttpSession session, ModelAndView mv) {
        System.out.println("this is login url!");
        // 调用业务逻辑组件判断用户是否可以登录
        User user = hrsService.login(loginName, password);
        if (user != null) {
            // 将用户保存到HttpSession当中
            session.setAttribute(HrsConstants.USER_SESSION, user);
            mv.setViewName("index");
        } else {
            System.out.println("oooooookkkk");
            // 设置登录失败提示信息
            mv.addObject("message","登录名或密码错误！请重新输入！");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/");
        }
        return mv;
    }

    /**
     * 处理查询请求
     * @param pageIndex 请求的是第几页
     * @param user 模糊查询参数
     * @param model model
     * @return "user/user"
     */
    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex, @ModelAttribute User user, Model model) {
        System.out.println("user = " + user);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        // 查询用户信息
        List<User> users = hrsService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);
        return "/user/list";
    }

    /**
     * 处理修改用户请求
     * @param ids 需要删除的id字符串
     * @param mv ModelAndView
     * @return mv
     */
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids, ModelAndView mv) {
        // 分解id字符串
        String [] idArray = ids.split(",");
        for (String id : idArray) {
            // 根据id删除员工
            hrsService.removeUserById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("/user/list");
        // 返回ModelAndView
        return mv;
    }

    /**
     * 处理修改用户请求
     * @param flag 标记：1表示跳转到修改页面，2表示执行修改操作
     * @param user 要修改用户的对象
     * @param mv ModelAndView
     * @return mv
     */
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(String flag, @ModelAttribute User user, ModelAndView mv) {
        if (("1").equals(flag)) {
            // 根据id查询用户
            User target = hrsService.findUserById(user.getId());
            // 设置Model数据
            mv.addObject("user", user);
            // 返回修改员工页面
            mv.setViewName("user/showUpdateUser");
        } else {
            // 执行修改操作
            hrsService.modifyUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("user/list");
        }
        // 返回
        return mv;
    }

    @RequestMapping(value = "user/addUser")
    public ModelAndView addUser(String flag, @ModelAttribute User user, ModelAndView mv) {
        if (("1").equals(flag)) {
            // 设置跳转到添加页面
            mv.setViewName("/user/add");
        } else {
            // 执行添加操作
            hrsService.addUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("/user/list");
        }
        return mv;
    }

}

package com.stu.hrs.service;

import com.stu.hrs.domain.*;
import com.stu.hrs.util.PageModel;

import java.util.List;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-03 10:27
 */
public interface HrsService {
    /**
     * 用户登录
     * @param loginName loginName
     * @param password password
     * @return User对象
     */
    User login(String loginName, String password);

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户对象
     */
    User findUserById(Integer id);

    /**
     * 获得所有用户
     * @param user
     * @param pageModel
     * @return User对象的List集合
     */
    List<User> findUser(User user, PageModel pageModel);

    /**
     * 根据id删除用户
     * @param id 用户id
     */
    void removeUserById(Integer id);

    /**
     * 修改用户
     * @param user 修改后的用户对象
     */
    void modifyUser(User user);

    /**
     * 添加用户
     * @param user 用户对象
     */
    void addUser(User user);

    /**
     * 获取所有员工
     * @param employee 查询条件
     * @param pageModel 分页对象
     * @return Dept对象的List集合
     */
    List<Employee> findEmployee(Employee employee, PageModel pageModel);

    /**
     * 根据id删除员工
     * @param id 员工id
     */
    void removeEmployeeById(Integer id);

    /**
     *根据id查询员工对象
     * @param id 员工id
     * @return 员工对象
     */
    Employee findEmployeeById(Integer id);

    /**
     * 新增员工
     * @param employee 员工对象
     */
    void addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee 员工对象
     */
    void modifyEmployee(Employee employee);

    /**
     * 获取所有部门，分页查询
     * @param dept 查询条件
     * @param pageModel 分页对象
     * @return Dept对象的List集合
     */
    List<Dept> findDept(Dept dept, PageModel pageModel);

    /**
     * 获取所有部门
     * @return Dept对象的List集合
     */
    List<Dept> findAllDept();

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    public void removeDeptById(Integer id);

    /**
     * 新增部门
     * @param dept 部门对象
     */
    void addDept(Dept dept);

    /**
     * 根据id查询部门
     * @param id 部门id
     * @return 部门对象
     */
    Dept findDeptById(Integer id);

    /**
     * 修改部门
     * @param dept 部门对象
     */
    void modifyDept(Dept dept);

    /**
     * 获取所有职位，分页查询
     * @param job 查询条件
     * @param pageModel 分页对象
     * @return Job对象的List集合
     */
    List<Job> findJob(Job job, PageModel pageModel);

    /**f
     * 找到所有的职位信息
     * @return Job对象的List集合
     */
    List<Job> findAllJob();

    /**
     * 根据id删除Job对象
     * @param id 职位id
     */
    public void removeJobById(Integer id);

    /**
     * 添加职位
     * @param job 职位对象
     */
    void addJob(Job job);

    /**
     * 根据id查询职位
     * @param id  职位id
     * @return Job对象
     */
    Job findJobById(Integer id);

    /**
     * 修改职位
     * @param job job对象
     */
    void modifyJob(Job job);

    /**
     * 获取所有公告，分页查询
     * @param notice 查询条件
     * @param pageModel 分页对象
     * @return Notice的List对象
     */
    List<Notice> findNotice(Notice notice, PageModel pageModel);

    /**
     * 根据id查询公告
     * @param id 公告id
     * @return 公告对象
     */
    Notice findNoticeById(Integer id);

    /**
     * 根据id删除公告
     * @param id 公告id
     */
    public void removeNoticeById(Integer id);

    /**
     * 新增公告
     * @param notice 公告对象
     */
    void addNotice(Notice notice);

    /**
     * 修改公告
     * @param notice 公告对象
     */
    void modifyNotice(Notice notice);

    /**
     * 获得所有文档，分页查询
     * @param document 查询条件
     * @param pageModel 分页对象
     * @return Document对象的List集合
     */
    List<Document> findDocument(Document document, PageModel pageModel);

    /**
     * 新增文档
     * @param document 公告文档
     */
    void addDocument(Document document);

    /**
     * 根据id查询文档
     * @param id 文档id
     * @return 文档对象
     */
    Document findDocumentById(Integer id);

    /**
     * 根据id删除文档
     * @param id 文档id
     */
    public void removeDocumentById(Integer id);

    /**
     * 修改文档
     * @param document 公告对象
     */
    void modifyDocument(Document document);



}

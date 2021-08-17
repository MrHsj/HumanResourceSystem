package com.stu.hrs.service.impl;

import com.stu.hrs.dao.*;
import com.stu.hrs.domain.*;
import com.stu.hrs.service.HrsService;
import com.stu.hrs.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-03 14:56
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("hrsService")
public class HrsServiceImpl implements HrsService {
    /**
     * 自动注入持久层Dao对象
     */
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DeptDAO deptDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private JobDAO jobDAO;
    @Autowired
    private NoticeDAO noticeDAO;
    @Autowired
    private DocumentDAO documentDAO;
    /*******************************用户服务接口实现*********************/

    /**
     * HrsServiceImpl接口login方法实现
     * @param loginName loginName
     * @param password password
     * @return User对象
     */
    @Transactional(readOnly = true)
    @Override
    public User login(String loginName, String password) {
        System.out.println("HrsServiceImpl login -- >>");
        return userDAO.selectByLoginNameAndPassword(loginName, password);
    }

    /**
     * 分页查询
     * @param user user
     * @param pageModel pageModel
     * @return List<User>
     */
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("pageModel", pageModel);
        int recordCount = userDAO.count(params);
        System.out.println("recordCount -->> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 开始分页查询数据：查询第几页的数据
            params.put("pageModel", pageModel);
        }
        return userDAO.selectByPage(params);
    }

    /**
     * hrsServiceImpl接口findUserById方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDAO.selectById(id);
    }

    /**
     * hrsServiceImpl接口removeUserById方法实现
     * @see HrsService
     */
    @Override
    public void removeUserById(Integer id) {
        userDAO.deleteById(id);
    }

    /**
     * hrsServiceImpl接口modifyUser方法实现
     * @see HrsService
     */
    @Override
    public void modifyUser(User user) {
        userDAO.update(user);
    }

    /**
     * hrsServiceImpl接口addUser方法实现
     * @see HrsService
     */
    @Override
    public void addUser(User user) {
        userDAO.save(user);
    }

    /********************部门服务接口实现*************************/
    /**
     * hrsServiceImpl接口findAllDept方法实现
     * @see HrsService
     */
    @Override
    @Transactional(readOnly = true)
    public List<Dept> findAllDept() {
        return deptDAO.selectAllDept();
    }

    /**
     * hrsServiceImpl接口findDept方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        // 当前需要分页的总数据条数
        Map<String, Object> params = new HashMap<>();
        params.put("dept", dept);
        int recordCount = deptDAO.count(params);
        System.out.println("recordCount --> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 开始分页查询数据：查询第几页的数据
            params.put("pageModel", pageModel);
        }
        return deptDAO.selectByPage(params);
    }

    /**
     * hrsServiceImpl接口removeDeptById方法实现
     * @see HrsService
     */
    @Override
    public void removeDeptById(Integer id) {
        deptDAO.deleteById(id);
    }

    /**
     * hrsServiceImpl接口addDept方法实现
     * @see HrsService
     */
    @Override
    public void addDept(Dept dept) {
        deptDAO.save(dept);
    }

    /**
     * hrsServiceImpl接口findDeptById方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public Dept findDeptById(Integer id) {
        return deptDAO.selectById(id);
    }

    /**
     * hrsServiceImpl接口modifyDept方法实现
     * @see HrsService
     */
    @Override
    public void modifyDept(Dept dept) {
        deptDAO.update(dept);
    }

    /*************************员工服务接口实现******************/

    /**
     * hrsServiceImpl接口findEmployee方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        // 当前需要分页的总数据数
        Map<String, Object> params = new HashMap<>();
        params.put("employee", employee);
        int recordCount = employeeDAO.count(params);
        System.out.println("recordCount --> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 开始分页查询数据，查询第几页的数据
            params.put("pageModel", pageModel);
        }
        return employeeDAO.selectByPage(params);
    }

    /**
     * hrsServiceImpl接口removeEmployeeById方法实现
     * @see HrsService
     */
    @Override
    public void removeEmployeeById(Integer id) {
        employeeDAO.deleteById(id);
    }

    /**
     * hrsServiceImpl接口findEmployeeById方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDAO.selectById(id);
    }

    /**
     * hrsServiceImpl接口addEmployee方法实现
     * @see HrsService
     */
    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    /**
     * hrsServiceImpl接口modifyEmployee方法实现
     * @see HrsService
     */
    @Override
    public void modifyEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    /************************职位接口实现******************************/
    /**
     * hrsServiceImpl接口findAllJob方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public List<Job> findAllJob() {
        return jobDAO.selectAllJob();
    }

    /**
     * hrsServiceImpl接口findJob方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        // 当前需要分页的总数据条数
        Map<String, Object> params = new HashMap<>();
        params.put("job", job);
        int recordCount = jobDAO.count(params);
        System.out.println("recordCount --> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return jobDAO.selectByPage(params);
    }

    /**
     * hrsServiceImpl接口removeJobById方法实现
     * @see HrsService
     */
    @Override
    public void removeJobById(Integer id) {
        jobDAO.deleteById(id);
    }

    /**
     * hrsServiceImpl接口addJob方法实现
     * @see HrsService
     */
    @Override
    public void addJob(Job job) {
        jobDAO.save(job);
    }

    /**
     * hrsServiceImpl接口findJobById方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public Job findJobById(Integer id) {
        return jobDAO.selectById(id);
    }

    /**
     * hrsServiceImpl接口modifyJob方法实现
     * @see HrsService
     */
    @Override
    public void modifyJob(Job job) {
        jobDAO.update(job);
    }

    /*****************************公告接口实现************************/
    /**
     * hrsServiceImpl接口findNotice方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        // 当前需要分页的总数据条数
        Map<String, Object> params = new HashMap<>();
        params.put("notice", notice);
        int recordCount = noticeDAO.count(params);
        System.out.println("recordCount --> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0 ) {
            // 开始分页查询数据：查询第几页的数据
            params.put("pageModel", pageModel);
        }
        return noticeDAO.selectByPage(params);
    }

    /**
     * hrsServiceImpl接口findNoticeById方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDAO.selectById(id);
    }

    /**
     * hrsServiceImpl接口addNotice方法实现
     * @see HrsService
     */
    @Override
    public void addNotice(Notice notice) {
        noticeDAO.save(notice);
    }

    /**
     * hrsServiceImpl接口modifyNotice方法实现
     * @see HrsService
     */
    @Override
    public void modifyNotice(Notice notice) {
        noticeDAO.update(notice);
    }

    /**
     * hrsServiceImpl接口removeNoticeById方法实现
     * @see HrsService
     */
    @Override
    public void removeNoticeById(Integer id) {
        noticeDAO.delete(id);
    }

    /***************************文件接口实现****************************/
    /**
     * hrsServiceImpl接口findDocument方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        // 当前需要分页的总数居条数
        Map<String, Object> params = new HashMap<>();
        params.put("document", document);
        int recordCount = documentDAO.count(params);
        System.out.println("recordCount --> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return documentDAO.selectByPage(params);
    }

    /**
     * hrsServiceImpl接口removeDocumentById方法实现
     * @see HrsService
     */
    @Override
    public void removeDocumentById(Integer id) {
        documentDAO.deleteById(id);
    }

    /**
     * hrsServiceImpl接口modifyDocument方法实现
     * @see HrsService
     */
    @Override
    public void modifyDocument(Document document) {
        documentDAO.update(document);
    }

    /**
     * hrsServiceImpl接口addDocument方法实现
     * @see HrsService
     */
    @Override
    public void addDocument(Document document) {
        documentDAO.save(document);
    }

    /**
     * hrsServiceImpl接口findDocumentById方法实现
     * @see HrsService
     */
    @Transactional(readOnly = true)
    @Override
    public Document findDocumentById(Integer id) {
        return documentDAO.selectById(id);
    }
}

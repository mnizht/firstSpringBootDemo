package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.repository.JpaUserRepository;
import com.example.demo.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhuht
 * @date 2018-12-17
 */

@RestController
@RequestMapping(value = "/user")

public class UserController {


    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @PostMapping("")
    public void postMethod(){
        System.out.println("post");
    }

    @GetMapping("")
    public void getMethod(){
        System.out.println("get");
    }


    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id) {
        User user = jpaUserRepository.getUserById(id);
        return user;
    }

    @RequestMapping("/findByUserNameLike")
    @ResponseBody
    public List<User> findByUserNameLike(String userName) {
        List<User> userList = jpaUserRepository.findByUserNameLike(userName);
        return userList;
    }

    @RequestMapping("/findByLastNameAndFirstName")
    @ResponseBody
    public List<User> findByLastNameAndFirstName(String lastName, String firstName) {
        List<User> userList = jpaUserRepository.findByLastNameAndFirstName(lastName, firstName);
        return userList;
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public List<User> findByUserNameLikeOrNoteLike(String userName, String note) {
        String userNameLike = "%" + userName + "%";
        String noteLike = "%" + note + "%";
        List<User> userList = jpaUserRepository.findByUserNameLikeOrNoteLike(userNameLike, noteLike);
        return userList;
    }

    @RequestMapping("/findByStartDateBetween")
    @ResponseBody
    public List<User> findByStartDateBetween(@RequestParam("beginDate") Date beginDate, @RequestParam("endDate") Date endDate) {

        List<User> userList = jpaUserRepository.findByStartDateBetween(beginDate, endDate);
        return userList;
    }

    @RequestMapping("/findByStartDateAfter")
    @ResponseBody
    public List<User> findByStartDateAfter(@RequestParam("beginDate") Date beginDate){
        return jpaUserRepository.findByStartDateAfter(beginDate);
    }

    //解决传递日期字符串时无法自动转换成日期类型的情况
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }

    @RequestMapping("/findByAgeLessThan")
    @ResponseBody
    public List<User> findByAgeLessThan(@RequestParam("age") Integer age) {
        return jpaUserRepository.findByAgeLessThan(age);
    }

    @RequestMapping("/findByAgeIn")
    @ResponseBody
    public List<User> findByAgeIn(@RequestParam("ages") Collection<Integer> ages) {
        return jpaUserRepository.findByAgeIn(ages);
    }

    @RequestMapping("/findByActiveTrue")
    @ResponseBody
    public List<User> findByActiveTrue() {
        return jpaUserRepository.findByActiveTrue();
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }


    @PostMapping(value = "/create")
    public void createUser(@RequestBody User user, @RequestParam("type") String type) {
        System.out.println("type="+type);
        System.out.println(user.getUserName());
        System.out.println(getEncoding(user.getUserName()));
        //返回插入的实例，包括自动生成的id
        User u = jpaUserRepository.save(user);
        if (u == null) {
            System.out.println("create failed");
        }
        System.out.println("create success " + u.getId());
    }

    /***
     * 创建多条记录
     */
    @RequestMapping("createUserAll")
    public ServiceResult createUserAll(@RequestBody List<User> users ){
        ServiceResult sr = new ServiceResult();
        try{
            List<User> userList = jpaUserRepository.saveAll(users);
            sr.setSuccess(true);

        }catch (Exception e){
            sr.setSuccess(false);
            sr.setErrorMessage(e.toString());
        }

        return sr;

    }

    @PostMapping(value = "/update")
    public void updateUser(@RequestBody User user) {
        //返回插入的实例，包括自动生成的id
        User u = jpaUserRepository.save(user);
        if (u == null) {
            System.out.println("update failed");
        }
        System.out.println("update success " + u);
    }

    /**
     * 按实体对象删除数据时，是根据对象的id来删除的；
     * 当这个对象没有id时，会先把这个对象insert一下，获得一个返回的id，再用这个id去删除
     * 这样当id为自增时，就会占用一个id号
     */
    @PostMapping("/delete")
    public ServiceResult deleteUser(@RequestBody User user) {
        ServiceResult sr = new ServiceResult();
        sr.setSuccess(true);
        try {
            jpaUserRepository.delete(user);

        } catch (IllegalArgumentException e) {
            sr.setSuccess(false);
            sr.setErrorMessage(e.toString());
            sr.setRtnInfo("删除用户 " + user.getUserName() + " 失败!");
        }
        return sr;
    }

    /**
     * 按id删除记录。会先按id查询一下数据。
     * 如果能查到数据，再以这个id为条件执行delete语句。
     * 如果查不到数据，会报异常。
     * 例：org.springframework.dao.EmptyResultDataAccessException: No class com.example.demo.pojo.User entity with id 8 exists!
     */
    @RequestMapping("/deleteById")
    public ServiceResult deleteById(Long id) {
        ServiceResult sr = new ServiceResult();
        sr.setSuccess(true);
        try {
            jpaUserRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            sr.setSuccess(false);
            sr.setErrorMessage(e.toString());
            sr.setRtnInfo("删除用户失败!没有找到id为 " + id + " 的用户！");
        }

        return sr;
    }

    /**
     * 判断此id的记录是否存在
     */
    @RequestMapping("/existsById")
    public ServiceResult existsById(long id) {
        ServiceResult sr = new ServiceResult();
        if (jpaUserRepository.existsById(id)) {
            sr.setSuccess(true);
            sr.setRtnInfo("该记录存在！");
        } else {
            sr.setSuccess(false);
            sr.setRtnInfo("该记录不存在！");
        }

        return sr;

    }

    /**
     *计数*/
    @RequestMapping("/count")
    public long count(){
        return jpaUserRepository.count();
    }

    /**
     * 分页查询
     * @param page 页码（从0开始）
     * @param size 每页显示数量
     * @param sort 按某个字段排序*/
    @RequestMapping("/findByPage")
    public List<User> findByPage(@RequestParam("page") int page, @RequestParam("size") int size,
                                 @RequestParam("sort") String sort){
        Pageable pageable = PageRequest.of(page,size,new Sort(Sort.Direction.DESC,sort));
        return jpaUserRepository.findAll(pageable).getContent();


    }

    /**
     *带分页的列表查询（按创建时间降序排序）
     * @param page 当前页
     * @param size 每一页数据条数
     * @param startDate 开始时间
     * @param endDate 结束时间*/
    @GetMapping("/pagelist")
    public List<User> getPageList(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "2") int size,
                                  @RequestParam(value = "startDate",required = false) Date startDate,
                                  @RequestParam(value = "endDate",required = false) Date endDate){


        return jpaUserRepository.findPageListByStartDate(page,size,startDate,endDate);
    }

    /**
     * 按List<Integer> ages 查询*/
    @GetMapping("/agesIn")
    public List<User> findAllByAgesIn(@RequestParam("ages") List<Integer> ages){
        return jpaUserRepository.findAllByAgeIn(ages);
    }

    /**
     * 查询用户状态*/
    @GetMapping("/status")
    public List<Map> findStatus(String userId){
        return jpaUserRepository.searchInfo(userId);
    }

    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode[] = new String[]{
          "UTF-8",
          "ISO-8859-1",
          "GB2312",
          "GBK",
          "GB18030",
          "Big5",
          "Unicode",
          "ASCII"
        };
        for (int i = 0; i < encode.length; i++){
            try {
                if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {
                    return encode[i];
                }
            } catch (Exception ex) {
            }
        }

        return "";
    }

}

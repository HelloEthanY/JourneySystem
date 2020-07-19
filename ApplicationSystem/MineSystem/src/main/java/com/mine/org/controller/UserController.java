package com.mine.org.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.org.TechnicalException;
import com.common.org.utils.ResponseCode;
import com.common.org.utils.ResultData;
import com.common.org.utils.md.DigestUtil;
import com.mine.org.dto.user.UserLoginDto;
import com.mine.org.entity.UserEntity;
import com.mine.org.service.TokenService;
import com.mine.org.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: YU
 * @Date: 12:32 2020/7/19
 * @Description: 用户控制台
 */
@Api(value = "用户信息接口", tags = {"用户信息接口"})
@RestController()
@RequestMapping(path = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @ApiOperation(value = "登陆", notes = "登陆:/user/login")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping(path = "/login")
    public Object login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password,
                        HttpServletResponse response) {
        try {
            // 判断用户名是否为空
            if (userName == null || "".equals(userName)) {
                return ResultData.newError(ResponseCode.USER_MOBILE_PATTERN_WRONG);
            }
            // 判断密码是否为空
            if (password == null || "".equals(password)) {
                return ResultData.newError(ResponseCode.USER_ACPASS_WRONG);
            }
            UserEntity userEntity = userService.login(userName);
            // 该用户不存在
            if (userEntity == null) {
                return ResultData.newError(ResponseCode.USER_NOT_EXIST);
            }
            String md5 = DigestUtil.md5Hex(password);
            if (!userEntity.getPassword().equals(md5)) {
                return ResultData.newError(ResponseCode.USER_ACPASS_WRONG);
            }
            // 得到token
            String token = tokenService.getToken(userEntity);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            JSONObject entity = new JSONObject();
            entity.put("token", token);
            entity.put("userName", userEntity.getUserName());
            entity.put("nickName", userEntity.getNickName());
            entity.put("address", userEntity.getAddress());
            entity.put("headImg", userEntity.getHeadImg());
            entity.put("password", password);
            entity.put("level", userEntity.getRoleEntity().getLevel());
            entity.put("phone", userEntity.getPhone());
            return ResultData.newSuccess(ResponseCode.COMMON_SUCCESS, entity);
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(-1, e.getMessage()));
        }
    }

    @ApiOperation(value = "创建或更新用户", notes = "创建或者更新用户，通过是否有Id来判断：/user/createUser")
    @PostMapping("/saveUser")
    public Object create(@RequestBody UserEntity user) {
        try {
            if (user.getUserName() == null || "".equals(user.getUserName())) {
                return ResultData.newError(1002, "用户名不能为空");
            }
            // 判断是否是更新操作
            if (user.getId() != null && !"".equals(user.getId())) { // 更新
                System.out.println("更新：：" + user.getId());
                user.setPassword(DigestUtil.md5Hex(user.getPassword()));
                userService.save(user);
                return ResultData.newSuccess("更是用户成功", null);
            }
            // 不是更新 判断是否用户名已存在
            UserLoginDto userLoginDto = userService.getUserInfo(user.getUserName());
            if (userLoginDto != null) {
                return ResultData.newSuccess("用户名已存在", null);
            }
            // 不是更新 用户名不存在
            if (user.getHeadImg() == null || "".equals(user.getHeadImg())) {
                user.setHeadImg("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2841348965,2123181886&fm=26&gp=0.jpg");
            }
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
            userService.save(user);
            return ResultData.newSuccess("用户创建成功", null);
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(-1, e.getMessage()));
        }
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表：/user/getUserList")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "size", value = "大小", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "模糊查询条件", dataType = "String")})
    @GetMapping(path = "/getUserList")
    public Object getUserList(@RequestParam(value = "size") int size, @RequestParam(value = "page") int page,
                              @RequestParam(value = "content") String content) {
        try {
            if (content == null || "".equals(content)) {
                return ResultData.newSuccess(userService.getUserList(page, size, ""));
            } else {
                return ResultData.newSuccess(userService.getUserList(page, size, content));
            }
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(-1, e.getMessage()));
        }
    }

    @ApiOperation(value = "删除用户信息", notes = "删除用户信息：/user/deleteUser")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @GetMapping(path = "/deleteUser")
    public Object deleteUser(@RequestParam(value = "id") String id) {
        try {
            userService.deleteUser(id);
            return ResultData.newSuccess("删除成功！", null);
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(-1, e.getMessage()));
        }
    }
}

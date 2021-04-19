package com.miaosha.miaosha.service;

import com.miaosha.miaosha.dao.MiaoshaUserDao;
import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.exception.GlobalException;
import com.miaosha.miaosha.redis.MiaoshaUserKey;
import com.miaosha.miaosha.redis.RedisService;
import com.miaosha.miaosha.result.CodeMsg;
import com.miaosha.miaosha.util.MD5Util;
import com.miaosha.miaosha.util.UUIDUtil;
import com.miaosha.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 17:09
 */
@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Resource
    private MiaoshaUserDao miaoshaUserDao;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(Long id) {
        return miaoshaUserDao.getById(id);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        //生成session，并把session放到redis
        if (user != null) {
            addCookie(response, user);
        }
        return user;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NO_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成session，并把session放到redis
        addCookie(response, user);
        return true;
    }

    /**
     * 生成cookie,生成session，并把session放到redis,
     *  把session(只是项目中叫token，实际是session)放到cookie中
     * @param response
     * @param user
     */
    private void addCookie(HttpServletResponse response, MiaoshaUser user) {
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        //cookie写到客户端里去了
        response.addCookie(cookie);
    }

}

package com.QA.shiro;

import com.QA.controller.UserController;
import com.QA.po.User;
import com.QA.po.UserPermission;
import com.QA.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("This is realm");
        //token是用户输入的
        //第一步:丛token中取出身份信息
        String username= (String) token.getPrincipal();

       //第二步:根据用户输入的username丛数据库查询
        User user = userService.findUserByName(username);

        //判断是否从数据库中查询到用户信息
        if (user==null)
        {
            return null;
        }

        //从数据库查询到的密码
        String password=user.getPassword();

        //盐salt
        String salt="salt";
        System.out.println(salt);

        System.out.println(user);

        ///将user设置到simpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo=new
                SimpleAuthenticationInfo(user.getUsername(), password, this.getName());

        return simpleAuthenticationInfo;
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) 	{
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("This is author.. realm");
        //从principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型(在上边的goGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo)
        User user= (User) principals.getPrimaryPrincipal();

        //根据身份信息获取权限信息,
        //从数据库中获取到的动态权限数据
        List<UserPermission> permissionList=null;
        ArrayList<String> list = new ArrayList<String>();
        list.add("user:delete");
        //permissionList= userService.findPermissionListByUserId(user.getId());

        List<String> permissions=new ArrayList<String>();

        if (permissionList == null)
        {
            for (String s:list)//UserPermission userPermission:permissionList)
            {
                logger.info(s);
                //将数据库中的权限标签符放入集合
                permissions.add(s);
            }
        }

        //查到权限数据，返回授权信息(包括上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();

        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}

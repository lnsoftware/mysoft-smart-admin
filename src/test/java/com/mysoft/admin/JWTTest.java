package com.mysoft.admin;

import com.mysoft.admin.bean.HrBean;
import com.mysoft.admin.bean.HrRoleBean;
import com.mysoft.admin.bean.RoleBean;
import com.mysoft.admin.common.jwt.JWT;
import com.mysoft.admin.common.jwt.TokenState;
import com.mysoft.admin.db.entity.HrRole;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/19/2018 10:01 AM
 * @Version 1.0
 */
public class JWTTest {

    @Test
    public void testJwt() {

        HrRoleBean hrRoleBean = new HrRoleBean();
        List<RoleBean> roleList = new ArrayList<>();
        RoleBean rb = new RoleBean();
        rb.setId(1001);
        rb.setName("admin");
        rb.setNameZh("管理员");
        hrRoleBean.setRoleList(roleList);
        hrRoleBean.setAddress("BeiJing");
        hrRoleBean.setEnable(1);
        hrRoleBean.setId(100);
        hrRoleBean.setName("name");
        hrRoleBean.setPassword("password");
        hrRoleBean.setPhone("phone");
        hrRoleBean.setUsername("username");
        hrRoleBean.setRemark("remark");
        hrRoleBean.setUserface("face");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("exp", System.currentTimeMillis() + 1000);
        param.put("content", hrRoleBean);
        String token = JWT.createToken(param);

        Map<String, Object> map = JWT.validToken(token);
        String state = (String) map.get("state");
        if (state.equals(TokenState.INVALID.toString())) {
            JSONObject jsonObject = (JSONObject) map.get("data");
            if (jsonObject.containsKey("content")) {
            }
        }
    }

}

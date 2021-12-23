package cn.project.demo.framework.security.pojo;

import cn.project.demo.framework.common.constants.enums.PlainOrdinaryStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 登录用户信息 redis存储
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     * <p>
     * 关联 {@link cn.project.demo.framework.common.constants.enums.UserTypeEnum}
     */
    private Integer userType;
    /**
     * 部门编号
     */
    private Long deptId;
    /**
     * 角色编号数组
     */
    private Set<Long> roleIds;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 所属岗位
     */
    private Set<Long> postIds;
    /**
     * group  目前指岗位代替
     */
    // TODO jason：这个字段，改成 postCodes 明确更好哈
    private List<String> groups;


    // TODO @芋艿：怎么去掉 deptId

    @Override
    @JsonIgnore// 避免序列化
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isEnabled() {
        return PlainOrdinaryStatusEnum.STATUS_ENUM_ENABLE.getSymbol().equals(status);
    }

    @Override
    @JsonIgnore// 避免序列化
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>(1);
        // TODO @芋艿：看看有没更优化的方案
        list.add(new SimpleGrantedAuthority("ROLE_ACTIVITI_USER"));
        return list;
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isAccountNonExpired() {
        return true; // 返回 true，不依赖 Spring Security 判断
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isAccountNonLocked() {
        return true; // 返回 true，不依赖 Spring Security 判断
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isCredentialsNonExpired() {
        return true;  // 返回 true，不依赖 Spring Security 判断
    }

}

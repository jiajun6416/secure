package com.jiajun.secure.core.advisor;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.jiajun.secure.core.annotation.Logical;
import com.jiajun.secure.core.annotation.RequiresPermissions;
import com.jiajun.secure.core.annotation.RequiresRoles;
import com.jiajun.secure.core.exception.UnauthenticatedException;
import com.jiajun.secure.service.UserService;

@Aspect
@Component
public class BaserMethodPermissionAspect implements Ordered{
	

	@Autowired
	private UserService userService;
	
	public int getOrder() {
		return 2;
	}
	
	@Before("@annotation(requiresRoles)")
	public void hasRole(JoinPoint joinPoint, RequiresRoles requiresRoles) {
		String targetName = joinPoint.getTarget().getClass().getName();
		String method = joinPoint.getSignature().getName();
		String[] roles = requiresRoles.value();
		List<String> hasRoles = userService.hasRoles();
		Logical logical = requiresRoles.logical();
		switch (logical) {
			case OR:
				boolean hasRole = false;
				for (String role : roles) {
					if (hasRoles.contains(role)) {
						hasRole = true;
						System.out.println(String.format("拦截方法%s.%s,具备%s角色", targetName, method, role));
						break;
					}
				}
				if (!hasRole) {
					System.out.println(String.format("拦截方法%s.%s,不具备%s角色", targetName, method, roles));
					throw new UnauthenticatedException("不具备" + roles + "角色之一");
				}
				break;
			default:
				for (String role : roles) {
					if (!hasRoles.contains(role)) {
						System.out.println(String.format("拦截方法%s.%s,不具备%s角色", targetName, method, role));
						throw new UnauthenticatedException("不具备" + role + "角色");
					}
					System.out.println(String.format("拦截方法%s.%s,具备%s角色", targetName, method, role));
				}
		}
	}
	
	@Before("@annotation(requiresPermissions)")
	public void hasPermission(JoinPoint joinPoint, RequiresPermissions requiresPermissions) {
		String targetName = joinPoint.getTarget().getClass().getName();
		String method = joinPoint.getSignature().getName();
		//具备的资源
		List<String> hasPers = Arrays.asList("");
		String[] permissions = requiresPermissions.value();
		Logical logical = requiresPermissions.logical();
		switch (logical) {
			case OR:
				boolean hasPermission = false;
				for (String permission : permissions) {
					if(hasPers.contains(permission)) {
						hasPermission = true;
						System.out.println(String.format("拦截方法%s.%s,具备%s资源", targetName, method, permission));
						break;
					}
				}
				if(!hasPermission) {
					throw new UnauthenticatedException("不具备" + permissions + "权限之一");
				}
				break;
			default:
				for (String permission : permissions) {
					if (!hasPers.contains(permission)) {
						System.out.println(String.format("拦截方法%s.%s,不具备%s资源", targetName, method, permission));
						throw new UnauthenticatedException("不具备" + permission + "权限");
					}
					System.out.println(String.format("拦截方法%s.%s,具备%s资源", targetName, method, permission));
				}
		}
	}
	
}

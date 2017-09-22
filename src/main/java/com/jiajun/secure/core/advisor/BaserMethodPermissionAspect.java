package com.jiajun.secure.core.advisor;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

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
		for (String role : roles) {
			if(!hasRoles.contains(role)) {
				System.out.println(String.format("拦截方法%s.%s,不具备%s角色", targetName, method,role));
				throw new UnauthenticatedException("不具备"+role+"角色");
			}
			System.out.println(String.format("拦截方法%s.%s,具备%s角色", targetName, method,role));
		}
	}
	
	@Before("@annotation(requiresPermissions)")
	public void hasPermission(JoinPoint joinPoint, RequiresPermissions requiresPermissions) {
		String targetName = joinPoint.getTarget().getClass().getName();
		String method = joinPoint.getSignature().getName();
		String[] permissions = requiresPermissions.value();
		List<String> haspers = userService.hasPermissions();
		for (String permission : permissions) {
			if(!haspers.contains(permission)) {
				System.out.println(String.format("拦截方法%s.%s,不具备%s资源", targetName, method,permission));
				throw new UnauthenticatedException("不具备"+permission+"权限");
			}
			System.out.println(String.format("拦截方法%s.%s,具备%s资源", targetName, method,permission));
		}
	}
	
}

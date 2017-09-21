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
import com.jiajun.secure.core.exception.PermissionDeniedException;
import com.jiajun.secure.service.UserService;

@Aspect
@Component
public class BaseClassPermissionAspect implements Ordered{
	
	public int getOrder() {
		return 1;
	}
	@Autowired
	private UserService userService;
	
	@Before("@within(requiresRoles)")
	public void hasRole(JoinPoint joinPoint,RequiresRoles requiresRoles ) throws Exception{
		String targetName = joinPoint.getClass().getName();
		String method = joinPoint.getSignature().getName();
		String[] roles = requiresRoles.value();
		List<String> hasRoles = userService.hasRoles();
		for (String role : roles) {
			System.out.println(String.format("拦截方法%s.%s,确认是否具备%s角色", targetName, method,role));
			if(!hasRoles.contains(role)) {
				throw new PermissionDeniedException("不具备"+role+"角色");
			}
		}
	}
	@Before("@within(requiresPermissions)")
	public void hasPermission(JoinPoint joinPoint, RequiresPermissions requiresPermissions) {
		String targetName = joinPoint.getClass().getName();
		String method = joinPoint.getSignature().getName();
		String[] permissions = requiresPermissions.value();
		List<String> haspers = userService.hasPermissions();
		for (String permission : permissions) {
			System.out.println(String.format("拦截方法%s.%s,确认是否具备%s资源", targetName, method,permission));
			if(!haspers.contains(permission)) {
				throw new PermissionDeniedException("不具备"+permission+"权限");
			}
		}
	}
}

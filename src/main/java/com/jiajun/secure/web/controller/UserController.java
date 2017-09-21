package com.jiajun.secure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiajun.secure.core.annotation.RequiresPermissions;
import com.jiajun.secure.core.annotation.RequiresRoles;
import com.jiajun.secure.service.UserService;

@RestController
@RequestMapping("user")
@RequiresRoles("vipUser")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("insert")
	@RequiresPermissions("user:insert")
	public String insert() {
		userService.insert();
		return "success";
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("user:delete")
	@RequiresRoles("admin")
	public String delete() {
		userService.delte();
		return "success";
	}
	
	@RequestMapping("list")
	public String list() {
		userService.list();
		return "success";
	}
	
	@RequestMapping("update")
	@RequiresPermissions("user:insert")
	public String update() {
		userService.update();
		return "success";
	}
}

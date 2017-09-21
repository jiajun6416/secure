package com.jiajun.secure.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public void insert() {
		System.out.println("添加用户");
	}
	
	public void delte() {
		System.out.println("删除用户");
	}
	
	public void list() {
		System.out.println("查询用户");
	}
	
	public void update() {
		System.out.println("修改用户信息");
	}
	
	public List<String> hasRoles() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("vipUser");
		return roles;
	}
	
	public List<String> hasPermissions() {
		ArrayList<String> permissions = new ArrayList<String>();
		permissions.add("user:insert");
		permissions.add("user:update");
		permissions.add("user:list");
		permissions.add("booking:insert");
		return permissions;
	}
}

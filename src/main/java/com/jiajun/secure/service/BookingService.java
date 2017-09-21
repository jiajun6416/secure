package com.jiajun.secure.service;

import org.springframework.stereotype.Service;

@Service
public class BookingService {
	public void save() {
		System.out.println("用户创建一个预约计划");
	}
	public void delete() {
		System.out.println("用户删除一个预约计划");
	}
	public void update() {
		System.out.println("用户修改一个预约计划");
	}
}

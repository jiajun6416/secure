package com.jiajun.secure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiajun.secure.core.annotation.RequiresPermissions;
import com.jiajun.secure.core.annotation.RequiresRoles;
import com.jiajun.secure.service.BookingService;

@RestController
@RequestMapping("booking")
@RequiresRoles("vipUser")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping("insert")
	@RequiresPermissions("booking:insert")
	public String save() {
		bookingService.save();
		return "保存success";
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("booking:delete")
	public String delete() {
		bookingService.delete();
		return "删除success";
	}
	
	@RequestMapping("update")
	@RequiresPermissions("booking:update")
	public String update() {
		bookingService.update();
		return "修改success";
	}
	
}

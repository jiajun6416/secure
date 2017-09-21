# secure
使用aop实现基于角色和资源的权限控制

#### 大概实现流程
- 使用Aspect的基于类注解切点 **@within** 与方法注解切点 **@annotation**
- 注解绑定,获得注解的值
- 使用**RequestContextHolder**获得request对象,获得session
- 判断是否具备权限,如果不具备丢出异常, 使用全局异常处理器拦截

#### 其他
可以整合shiro,或者参考shiro的AOP(使用权限拦截器..)

#### 不足
虽然使用了全局异常处理器,但是AOP内部还是会打印异常...

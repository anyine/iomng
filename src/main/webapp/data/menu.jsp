<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
[
<shiro:hasPermission name="waichushenqing">{
		"id" : "leavelist",
		"parentId" : "4",
		"text" : "外出申请",
		"leaf" : "1"
	},
</shiro:hasPermission>
<shiro:hasPermission name="cheliangchuruchaxun">
	 {
		"id" : "innercarrecordlist",
		"parentId" : "2",
		"text" : "车辆出入记录",
		"leaf" : "1"
	} ,
</shiro:hasPermission>
<shiro:hasPermission name="renyuanchuruchaxun">
{
		"id" : "innerpersonrecordlist",
		"parentId" : "2",
		"text" : "人员出入记录",
		"leaf" : "1"
	},
</shiro:hasPermission> 
<shiro:hasPermission name="cheliangguanli">
        {
            "id": "innercarlist",
            "parentId": "2",
            "text": "车辆管理",
            "leaf": "1"
        },</shiro:hasPermission>
<shiro:hasPermission name="renyuanguanli">
        {
            "id": "innerpersonlist",
            "parentId": "2",
            "text": "人员管理",
            "leaf": "1"
},</shiro:hasPermission>
<shiro:hasPermission name="yonghuguanli">
        {
            "id": "userlist",
            "parentId": "3",
            "text": "用户管理",
            "leaf": "1"
        },</shiro:hasPermission>
<shiro:hasPermission name="duanxintongzhishijianguanli">
        {
            "id": "notifylist",
            "parentId": "3",
            "text": "短信通知时间管理",
            "leaf": "1",
            "children": ""
        },</shiro:hasPermission>
<shiro:hasRole name="admin">
{
            "id": "",
            "parentId": "9",
            "text": "开发人员工具",
            "leaf": "0",
            "children": [
            	 {
            "id": "doorlist",
            "parentId": "2",
            "text": "门禁管理",
            "leaf": "1",
            "children": ""
        },
        {
            "id": "organizationtree",
            "parentId": "3",
            "text": "机构维护",
            "leaf": "1",
            "children": ""
        },{
            "id": "cartypelist",
            "parentId": "3",
            "text": "车辆类型维护",
            "leaf": "1",
            "children": ""
        }
            ]
        }</shiro:hasRole>
]

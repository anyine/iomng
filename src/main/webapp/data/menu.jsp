<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
[
<shiro:hasPermission name="shenqingguanli">{
	"id" : "4",
	"parentId" : "1",
	"text" : "申请管理",
	"leaf" : "0",
	"children" : [ {
		"id" : "leavelist",
		"parentId" : "4",
		"text" : "外出申请",
		"leaf" : "1",
		"children" : ""
	
	}]
} ,</shiro:hasPermission>
<shiro:hasPermission name="churuchaxun">
{
	"id" : "1",
	"parentId" : "1",
	"text" : "出入查询",
	"leaf" : "0",
	"children" : [ {
		"id" : "innercarrecordlist",
		"parentId" : "2",
		"text" : "车辆出入记录",
		"leaf" : "1",
		"children" : ""
	} ,{
		"id" : "innerpersonrecordlist",
		"parentId" : "2",
		"text" : "人员出入记录",
		"leaf" : "1",
		"children" : ""
	} ]
},</shiro:hasPermission>

{
    "id": "2",
    "parentId": "1",
    "text": "基本信息",
    "leaf": "0",
    "children": [
        {
            "id": "innercarlist",
            "parentId": "2",
            "text": "车辆管理",
            "leaf": "1",
            "children": ""
        },
        {
            "id": "innerpersonlist",
            "parentId": "2",
            "text": "人员管理",
            "leaf": "1",
            "children": ""
        }
    ]
},
{
    "id": "3",
    "parentId": "1",
    "text": "系统设置",
    "leaf": "0",
    "children": [
        {
            "id": "cartypelist",
            "parentId": "3",
            "text": "车辆类型维护",
            "leaf": "1",
            "children": ""
        },
        {
            "id": "userlist",
            "parentId": "3",
            "text": "用户管理",
            "leaf": "1",
            "children": ""
        },
        {
            "id": "notifylist",
            "parentId": "3",
            "text": "短信通知时间管理",
            "leaf": "1",
            "children": ""
        }
    ]
}
<shiro:hasRole name="admin">
,{
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
        }
            ]
        }</shiro:hasRole>
]

Ext.define('VMS.view.user.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.userlist',
	requires : [ 'Ext.grid.column.Action' ],
	title : '用户列表',
	store : 'UserStore',
	columns : [ {
		xtype : 'rownumberer'
	},{
		text : '姓名',
		dataIndex : 'realname'
	}, {
		text : '所属机构',
		dataIndex : 'organization.name'
	}, {
		text : 'email',
		dataIndex : 'email',
		width:200
	}, {
		text : '手机',
		dataIndex : 'phone'
	},{
		xtype:'actioncolumn',
		items:[{
			icon:'ext/resources/ext-theme-neptune/images/shared/icon-error.png',
			tooltip:'删除'
		}]
	} ],
	tbar : [ {
		text : '新增',
		action : 'add'
	},{
		text : '授权',
		action : 'permission'
	}]
});
Ext.define('VMS.view.organization.Menu',{
	extend: 'Ext.menu.Menu',
	xtype: 'organizationmenu',
	requires:['VMS.view.organization.Edit'],
	items:[{
		text:'编辑',
		action: 'edit'
	},{
		text:'添加',
		action: 'add'
	},{
		text: '删除',
		action: 'del'
	}]
});
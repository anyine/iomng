Ext.define('VMS.view.door.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.doorlist',

	title : '门列表',
	store : 'DoorStore',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : '所属机构名称',
		dataIndex : 'organization.name',
		width : 200
	}, {
		text : '门名称',
		dataIndex : 'name'
	} ,{
		xtype:'actioncolumn',
		items:[{
			icon:'ext/resources/ext-theme-neptune/images/shared/icon-error.png',
			tooltip:'删除'
		}]
	} ],
	tbar : [ {
		text : '新增',
		action : 'add'
	} ]
});
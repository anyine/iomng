Ext.define('VMS.view.carType.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.cartypelist',
	requires : [ 'Ext.grid.column.Action' ],
	title : '车辆类型列表',
	store : 'CarTypeStore',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : '类型名称',
		dataIndex : 'name'
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
	} ]
});
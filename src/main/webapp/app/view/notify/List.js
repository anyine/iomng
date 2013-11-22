Ext.define('VMS.view.notify.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.notifylist',
	requires : [ 'Ext.grid.column.Action' ],
	title : '短信通知时间配置',
	store : 'NotifyStore',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : '名称',
		dataIndex : 'name',
		width:200
	}, {
		xtype: 'datecolumn',
		text : '起始时间',
		dataIndex : 'startTime',
		format:'H:i:s'
	}, {
		xtype: 'datecolumn',
		text : '结束时间',
		dataIndex : 'endTime',
		format:'H:i:s'
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
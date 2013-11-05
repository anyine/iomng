Ext.define('VMS.view.innerPerson.List', {
	extend : 'Ext.grid.Panel',
	requires : [ 'Ext.grid.column.Action' ],
	alias : 'widget.innerpersonlist',
	
	title : '人员列表',
	forceFit: true,
	selType: 'rowmodel',
	store : 'InnerPersonStore',
	columns : [  {
		xtype : 'rownumberer'
	},{
		text : '姓名',
		dataIndex : 'name'
	}, {
		text : '卡号',
		dataIndex : 'card.number'
	}, {
		text : '证件号',
		dataIndex : 'certificate'
	}, {
		text : '所属机构',
		dataIndex : 'organization.name'
	},{
		xtype:'actioncolumn',
		items:[{
			icon:'ext/resources/ext-theme-neptune/images/shared/icon-error.png',
			tooltip:'删除'
		}]
	}  ],
	tbar:[{
		text:'新增',
		action:'add'
	}]
});
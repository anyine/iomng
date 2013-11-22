Ext.define('VMS.view.innerCar.List', {
	extend : 'Ext.grid.Panel',
	requires : [ 'Ext.grid.column.Action' ],
	alias : 'widget.innercarlist',
	
	title : '车辆列表',
	forceFit: true,
	selType: 'rowmodel',
	store : 'InnerCarStore',
	columns : [  {
		xtype : 'rownumberer'
	},{
		text : '号牌',
		dataIndex : 'license'
	}, {
		text : '负责人',
		dataIndex : 'user.realname'
	}, {
		text : '卡号',
		dataIndex : 'card.number'
	}, {
		text : '类型',
		dataIndex : 'type.name'
	}, {
		text : '所属机构',
		dataIndex : 'organization.name'
	},{
		text : '通行级别',
		dataIndex : 'level',
		renderer:function(val){
			if(val=='request'){
				return '干部私家车';
			}else if(val=='direct'){
				return '<span style="color:#73b51e;">内部管理车</span>';
			}else{
				return val;
			}
		}
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
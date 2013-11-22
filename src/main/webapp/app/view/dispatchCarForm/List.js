Ext.define('VMS.view.dispatchCarForm.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.dispatchcarformlist',
	requires : [ 'Ext.grid.column.Action' ],
	title : '派车单',
	store : 'DispatchCarFormStore',
	
	viewConfig : {
		forceFit : true,
		getRowClass : function(record, rowIndex, rowParams, store) {
			var agree=record.get('agree');
			if(agree){
				return 'agree-row';
			}else {
				return 'noagree-row';
			}
		}
	},
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : '车辆',
		dataIndex : 'car.license'
	}, {
		text : '车辆负责人',
		dataIndex : 'car.user.realname'
	}, {
		text : '申请人',
		dataIndex : 'user'
	},{
		text:'外出时间',
		dataIndex:'startTime',
		width : 200,
		xtype : 'datecolumn',
		format : 'y-m-d l H:i:s'		
	},{
		text:'返回时间',
		dataIndex:'endTime',
		width : 200,
		xtype : 'datecolumn',
		format : 'y-m-d l H:i:s'		
	}, {
		text : '事由',
		dataIndex : 'reason',
		flex:1
	}, {
		text : '状态',
		dataIndex : 'agree',
		xtype:'booleancolumn',
		trueText:'批准',
		falseText:'未批准'
	}],
	tbar : [ {
		text : '新增',
		action : 'add'
	} ]
});
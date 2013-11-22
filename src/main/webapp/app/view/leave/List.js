Ext.define('VMS.view.leave.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.leavelist',
	requires : ['Ext.grid.column.Action'],
	title : '请假申请',
	store : 'LeaveStore',

	viewConfig : {
		forceFit : true,
		getRowClass : function(record, rowIndex, rowParams, store) {
			var agree = record.get('agree');
			if (agree) {
				return 'agree-row';
			} else {
				return 'noagree-row';
			}
		}
	},
	columns : [{
				xtype : 'rownumberer'
			}, {
				text : '请假人',
				dataIndex : 'person.name'
			}, {
				text : '外出时间',
				dataIndex : 'startTime',
				width : 200,
				xtype : 'datecolumn',
				format : 'y-m-d l H:i:s'
			}, {
				text : '归队时间',
				dataIndex : 'endTime',
				width : 200,
				xtype : 'datecolumn',
				format : 'y-m-d l H:i:s'
			}, {
				text : '事由',
				dataIndex : 'reason',
				flex : 1
			}, {
				text : '状态',
				dataIndex : 'agree',
				xtype : 'booleancolumn',
				trueText : '批准',
				falseText : '未批准'
			}, {
				xtype : 'actioncolumn',
				text : '审批',
				items : [{
					icon : 'ext/resources/ext-theme-access/images/dd/drop-yes.gif',
					tooltip : 'agree',
					handler : function(view, rowIndex, colIndexitem, item,e, record) {
						this.up('grid').fireEvent('itemagreeclick', view, rowIndex, colIndexitem, item,e, record);
					}
				}, {
					icon : 'ext/resources/ext-theme-access/images/dd/drop-no.gif',
					tooltip : 'noagree',
					handler : function(view, rowIndex, colIndexitem,item, e, record) {
						this.up('grid').fireEvent('itemnoagreeclick', view, rowIndex, colIndexitem, item,e, record);
					}
				}]
			}],
	tbar : [{
				text : '新增',
				action : 'add'
			}],
	constructor : function(config) {
		console.log("leavelist is constructor..");
		// 自定义事件
		this.addEvents({
					itemagreeclick : true,
					itemnoagreeclick : true
				});
		this.callParent(config);
	}
});
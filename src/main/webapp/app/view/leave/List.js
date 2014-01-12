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
				text : '请假时间',
				dataIndex : 'createTime',
				width : 180,
				xtype : 'datecolumn',
				format : 'y-m-d l H:i:s'
			}, {
				text : '外出时间',
				dataIndex : 'startTime',
				width : 180,
				xtype : 'datecolumn',
				format : 'y-m-d l H:i:s'
			}, {
				text : '归队时间',
				dataIndex : 'endTime',
				width : 180,
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
					icon : 'ext/resources/ext-theme-access/images/grid/drop-yes.gif',
					tooltip : 'agree',
					getClass : function(v, m, r, ri, ci, s) {
						return r.get('agree') ? 'x-hidden' : '';
					},
					isDisabled : function(v, ri, ci, i, r) {
						return r.get('endTime') < new Date();
					},
					handler : function(view, rowIndex, colIndexitem, item, e,
							record) {
						this.up('grid').fireEvent('itemagreeclick', view,
								rowIndex, colIndexitem, item, e, record);
					}
				}, {
					icon : 'ext/resources/ext-theme-access/images/grid/drop-no.gif',
					tooltip : 'noagree',
					isDisabled : function(v, ri, ci, i, r) {
						return r.get('endTime') < new Date();
					},
					getClass : function(v, m, r, ri, ci, s) {
						return r.get('agree') ? '' : 'x-hidden';
					},
					handler : function(view, rowIndex, colIndexitem, item, e,
							record) {
						this.up('grid').fireEvent('itemnoagreeclick', view,
								rowIndex, colIndexitem, item, e, record);
					}
				}]
			}],
	tbar : [{
				text : '新增',
				action : 'add'
			}, '->', {
				xtype : 'form',
				border : false,
				layout : 'hbox',
				defaults : {
					labelWidth : 60
				},
				items : [{
							xtype : 'combo',
							store : 'LeaveStore',
							displayField : 'person.name',
							fieldLabel : '请假人员',
							queryMode : 'local',
							enableRegEx : true,
							hideTrigger : true,
							typeAhead : true,
							minChars : 2
						}, {
							xtype : 'datetimefield',
							fieldLabel : '起止时间',
							name : 'startdt',
							itemId : 'startdt',
							// vtype: 'daterange',
							endDateField : 'enddt'
						}, {
							xtype : 'datetimefield',
							fieldLabel : '截止时间',
							name : 'enddt',
							itemId : 'enddt',
							// vtype: 'daterange',
							startDateField : 'startdt'
						}]
			}, {
				text : '重置',
				action : 'reset'
			}],
	constructor : function(config) {
		this.callParent(arguments);
		// console.log("leavelist is constructor..");
		// 自定义事件
		this.addEvents({
					itemagreeclick : true,
					itemnoagreeclick : true
				});
	}
});
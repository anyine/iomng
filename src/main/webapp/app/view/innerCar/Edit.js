Ext.define('VMS.view.innerCar.Edit', {
	extend : 'Ext.window.Window',
	requires : [ 'VMS.store.OrganizationStore', 'Ext.ux.TreePicker' ],
	xtype : 'innercaredit',
	resizable:false,
	modal : true,
	height : 250,
	width : 400,
	buttonAlign : 'center',
	title : '车辆',
	items : [ {
		xtype : 'form',
		border : false,
		fieldDefaults : {
			labelWidth : 60,
			labelAlign : 'right',
			width : 300
		},
		items : [ {
			xtype : 'tabpanel',
			plain : true,
			activeTab : 0,
			border : false,
			defaults : {
				bodyPadding : 10,
				border : false
			},
			items : [ {
				title : '基本信息',
				items : [ {
					xtype : 'treepicker',
					maxPickerHeight : 160,
					allowBlank : false,
					fieldLabel : '所属机构',
					store : Ext.create('VMS.store.OrganizationStore'),
					editable : false,
					name : 'organization.id',
					valueField : 'id',
					displayField : 'text',
					emptyText : '选择所属机构。'
				}, {
					xtype : 'textfield',
					allowBlank : false,
					fieldLabel : '车牌号',
					name : 'license',
					emptyText : '填写车牌号。'
				}, {
					xtype : 'textfield',
					allowBlank : false,
					fieldLabel : '卡号',
					name : 'card.number',
					emptyText : '填写门禁卡号。'
				}, {
					xtype : 'combo',
					allowBlank : false,
					fieldLabel : '车辆类型',
					store : 'CarTypeStore',
					editable : false,
					name : 'type.id',
					valueField : 'id',
					displayField : 'name',
					emptyText : '选择车辆类型。'
				} ]
			}, {
				title : '通知策略',
				items : [ {
					xtype : 'radiogroup',
					layout : {
						autoFlex : true
					},
					defaults : {
						name : 'level',
						margin : '0 15 0 0',
						width:250
					},
					items : [ {
						inputValue : 'request',
						boxLabel : '请求通行',
						checked : true
					}, {
						inputValue : 'direct',
						boxLabel : '直接通行'
					} ]
				} , {
					xtype : 'combo',
					allowBlank : false,
					fieldLabel : '负责人',
					store : 'UserStore',
					editable : false,
					name : 'user.id',
					valueField : 'id',
					displayField : 'realname',
					emptyText : '选择车辆负责人。'
				}, {
			        xtype: 'label',
			        text: '每天18:00到次日6:00外出的车辆，如无申请则发送短信通知给车辆负责人。',
			        margin: '0 0 0 10'
			    }]
			} ]
		} ]
	} ],
	buttons : [ {
		text : '保存',
		action : 'save'
	}, {
		text : '取消',
		handler : function(me, e) {
			me.up('window').close();
		}
	} ]
});
Ext.define('VMS.view.innerCar.Edit', {
	extend : 'Ext.window.Window',
	requires : [ 'VMS.store.OrganizationStore', 'Ext.ux.TreePicker' ],
	xtype : 'innercaredit',
	resizable:false,
	modal : true,
	height : 300,
	width : 400,
	buttonAlign : 'center',
	bodyPadding:20,
	title : '车辆',
	items : [{xtype:'form',
		border:false,
		items:[ {
		xtype : 'treepicker',
		maxPickerHeight : 160,
		allowBlank : false,
		fieldLabel : '所属机构',
		store : Ext.create('VMS.store.OrganizationStore'),
		editable : false,
		name : 'organization.id',
		valueField : 'id',
		displayField : 'text',
		value:'',
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
		xtype : 'combo',
		allowBlank : false,
		fieldLabel : '短信通知时间',
		store : 'NotifyStore',
		editable : false,
		name : 'notify.id',
		valueField : 'id',
		displayField : 'name',
		emptyText : '选择短信通知策略类型。'
	} ,{
		xtype : 'radiogroup',
		layout : {
			autoFlex : true
		},
		defaults : {
			name : 'level',
			margin : '0 15 0 0',
			width:220
		},
		items : [ {
			inputValue : 'request',
			boxLabel : '干部私家车',
			checked : true
		}, {
			inputValue : 'direct',
			boxLabel : '内部管理车'
		} ]
	} ]
	}],
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
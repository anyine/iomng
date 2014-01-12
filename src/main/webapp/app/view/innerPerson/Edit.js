Ext.define('VMS.view.innerPerson.Edit', {
	extend : 'Ext.window.Window',
	requires : [ 'VMS.store.OrganizationStore', 'Ext.ux.TreePicker',
			'Ext.ux.form.ItemSelector' ],
	xtype : 'innerpersonedit',
	resizable : false,
	modal : true,
	height : 450,
	width : 400,
	buttonAlign : 'center',
	title : '人员',

	items : [ {
		xtype : 'form',
		border : false,
		bodyPadding : 10,
		fieldDefaults : {
			labelWidth : 70,
			labelAlign : 'right',
			width : 350
		},
		items : [ {
			xtype : 'treepicker',
			maxPickerHeight : 145,
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
			fieldLabel : '证件号',
			name : 'certificate',
			emptyText : '填写证件号。'
		}, {
			xtype : 'textfield',
			allowBlank : false,
			fieldLabel : '卡号',
			name : 'card.number',
			emptyText : '填写门禁卡号。'
		}, {
			xtype : 'textfield',
			fieldLabel : '手机号',
			name : 'phone',
			 regex: /^\d{11}$/,
             regexText: '必须为11位手机号。',
			emptyText : '填写要绑定的手机号。'
		}, {
			xtype : 'textfield',
			allowBlank : false,
			fieldLabel : '姓名',
			name : 'name',
			emptyText : '填写姓名。'
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
		}, {
			xtype : 'itemselector',
			name : 'userIds',
			anchor : '100%',
			fieldLabel : '请假负责人',
			height : 190,
			buttons : [ 'add', 'remove' ],
			buttonsText : {
				add : '添加到选中',
				remove : '移除'
			},
			imagePath: 'ux/css/images/',
			store : 'UserStore',
			displayField : 'realname',
			valueField : 'id',
			msgTarget : 'side',
			fromTitle : '选择',
			toTitle : '选中'
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
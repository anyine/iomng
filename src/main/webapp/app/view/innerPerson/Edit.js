Ext.define('VMS.view.innerPerson.Edit', {
	extend : 'Ext.window.Window',
	requires : [ 'VMS.store.OrganizationStore', 'Ext.ux.TreePicker' ],
	xtype : 'innerpersonedit',
	resizable : false,
	modal : true,
	height : 250,
	width : 400,
	buttonAlign : 'center',
	title : '人员',
	
	items : [ {
		xtype : 'form',
		border : false,
		bodyPadding:20,
		fieldDefaults : {
			labelWidth : 60,
			labelAlign : 'right',
			width : 300
		},
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
			allowBlank : false,
			fieldLabel : '姓名',
			name : 'name',
			emptyText : '填写姓名。'
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
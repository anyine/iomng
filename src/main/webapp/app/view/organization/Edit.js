Ext.define('VMS.view.organization.Edit', {
	extend : 'Ext.window.Window',
	xtype : 'organizationedit',
	resizable : false,
	modal : true,
	height : 200,
	width : 300,
	buttonAlign : 'center',
	title : '机构',
	items : [ {
		xtype : 'form',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border : false,
		bodyPadding : 20,
		items : [ {
			xtype : 'textfield',
			labelAlign : 'right',
			labelWidth : 60,
			allowBlank : false,
			fieldLabel : '机构名称',
			name : 'name',
			emptyText : '填写机构名称。'
		}, {
			xtype : 'numberfield',
			value : 0,
			maxValue : 99,
			minValue : 0,
			labelAlign : 'right',
			labelWidth : 60,
			allowBlank : false,
			fieldLabel : '序号',
			name : 'index',
			emptyText : '填写机构序号。'

		} ]
	} ],
	buttons : [ {
		text : '保存',
		action : 'save'
	}, {
		text : '取消',
		handler : function(me, e) {
			var win = me.up('window');
			win.close();
		}
	} ]
});
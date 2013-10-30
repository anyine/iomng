Ext.define('VMS.view.door.Edit', {
	extend : 'Ext.window.Window',
	requires : [ 'VMS.store.OrganizationStore', 'Ext.ux.TreePicker' ],
	xtype : 'dooredit',
	resizable:false,
	modal : true,
	height : 380,
	width : 750,
	buttonAlign : 'center',
	title : '门禁',
	items : [ {
		xtype : 'form',
		border : false,
		fieldDefaults : {
			labelWidth : 60,
			labelAlign : 'right',
			width : 300
		},
		bodyPadding : 10,

		items : [ {
			xtype : 'container',
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'treepicker',
				flex : 1,
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
				flex : 1,
				allowBlank : false,
				fieldLabel : '名称',
				name : 'name',
				emptyText : '填写门名称。'
			} ]
		}, {
			xtype : 'fieldset',
			title : '控制器信息',
			defaultType : 'textfield',
			height : 270,
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'controllerlist',
				flex : 35
			}, {
				xtype : 'readerlist',
				flex : 65
			} ]
		} ]
	} ],
	buttons : [ {
		text : '保存',
		action : 'save'
	}, {
		text : '取消',
		action:'close'
	} ]
});
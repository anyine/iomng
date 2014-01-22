Ext.define('VMS.view.user.PermissionEdit', {
	extend : 'Ext.window.Window',
	xtype : 'permissionedit',
	resizable : false,
	modal : true,
	height : 350,
	width : 500,
	buttonAlign : 'center',
	title : '授权管理',
	items : [ {
		xtype : 'form',
		border : false,
		bodyPadding : 20,
		fieldDefaults : {
			labelWidth : 60,
			width : 250,
			labelAlign : 'right'
		},
		items : [{
			xtype:'hiddenfield',
			name:'id'
		}, {
			xtype : 'itemselector',
			name : 'menuId',
			anchor : '100%',
			fieldLabel : '菜单项',
			height : 190,
			buttons : [ 'add', 'remove' ],
			buttonsText : {
				add : '添加到选中',
				remove : '移除'
			},
			imagePath : 'ux/css/images/',
			store : Ext.create('Ext.data.ArrayStore', {
				fields : [ 'id', 'name'],
				data:[["1",'外出申请'],
				      ["2",'车辆出入记录'],
				      ["3",'人员出入记录'],
				      ["4",'车辆管理'],
				      ["5",'人员管理'],
				      ["6",'车辆类型维护'],
				      ["7",'用户管理'],
				      ["8",'短信通知时间管理']]
			}),
			displayField : 'name',
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
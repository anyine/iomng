Ext.define('VMS.view.user.Edit', {
	extend : 'Ext.window.Window',
	xtype : 'useredit',
	resizable:false,
	modal:true,
	height : 250,
	width : 300,
	buttonAlign:'center',
	title : '用户',
	items : [ {
		xtype : 'form',
		border:false,
		bodyPadding:20,
		fieldDefaults : {
			labelWidth : 60,
			width:250,
			labelAlign : 'right'
		},
		items : [{
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
			xtype:'textfield',
			allowBlank : false,
			fieldLabel : '登陆名',
			name : 'username',
			emptyText : '登陆系统时的用户名。'
		} , {
			xtype:'textfield',
			allowBlank : false,
			fieldLabel : '真实姓名',
			name : 'realname'
		}, {
			xtype:'textfield',
			fieldLabel : 'Email',
			name : 'email',
			vtype:'email'
		}, {
			xtype:'textfield',
			fieldLabel : '手机',
			name : 'phone',
			 regex: /^\d{11}$/,
             regexText: '必须为11位手机号。'
		}]
	} ],
	buttons : [ {
		text : '保存',
		action:'save'
	}, {
		text : '取消',
		handler:function(me,e){
			me.up('window').close();
		}
	} ]
});
Ext.define('VMS.view.leave.Edit', {
	extend : 'Ext.window.Window',
	requires : [ 'Ext.ux.form.field.DateTime' ],
	xtype : 'leaveedit',
	resizable : false,
	modal : true,
	height : 280,
	width : 500,
	buttonAlign : 'center',
	title : '请假申请单',
	items : [ {
		xtype : 'form',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border : false,
		bodyPadding : 20,
		items : [ {
			xtype : 'combo',
			allowBlank : false,
			fieldLabel : '请假人',
			store : 'InnerPersonStore',
			typeAhead : true,
			name : 'person.id',
			valueField : 'id',
			displayField : 'name',
			emptyText : '选择请假人。'
		} ,{
			xtype : 'datetimefield',
			fieldLabel : '请假时间',
			name : 'startTime',
			itemId : 'startdt',
//			vtype : 'daterange',
			allowBlank : false,
			value : new Date(),
			endDateField : 'enddt' // id of the end date field
		}, {
			xtype : 'datetimefield',
			fieldLabel : '归队时间',
			name : 'endTime',
			itemId : 'enddt',
			allowBlank : false,
			//vtype : 'daterange',
			startDateField : 'startdt' // id of the start date field
		}, {
			xtype : 'textareafield',
			grow : true,
			name : 'reason',
			allowBlank : false,
			fieldLabel : '事由',
			anchor : '100%'
		}, {
			xtype:'checkboxfield',
	        boxLabel  : '批准申请',
	        checked   : true,
	        name      : 'agree'
	    }  ]
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
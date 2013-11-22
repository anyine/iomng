Ext.define('VMS.view.notify.Edit', {
	extend : 'Ext.window.Window',
	xtype : 'notifyedit',
	resizable:false,
	modal:true,
	height : 200,
	width : 300,
	buttonAlign:'center',
	title : '短信通知策略',
	items : [ {
		xtype : 'form',
		layout:{
			type:'vbox',
			align:'stretch'
		},
		border:false,
		bodyPadding:20,
		items : [ {
			xtype:'textfield',
			labelAlign:'right',
			labelWidth:60,
			allowBlank : false,
			fieldLabel : '类型名称',
			name : 'name',
			emptyText : '填写名称。'
		},{
	        xtype: 'timefield',
	        name: 'startTime',
	        allowBlank : false,
	        fieldLabel: '起始时间',
	        increment: 5,
	        format : 'H:i:s',
	        emptyText : '填写起始时间。'
	    }, {
	        xtype: 'timefield',
	        name: 'endTime',
	        allowBlank : false,
	        fieldLabel: '结束时间',
	        increment: 5,
	        format : 'H:i:s',
	        emptyText : '填写结束时间。'
	   } ]
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
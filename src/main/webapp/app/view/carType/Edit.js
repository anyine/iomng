Ext.define('VMS.view.carType.Edit', {
	extend : 'Ext.window.Window',
	xtype : 'cartypeedit',
	resizable:false,
	modal:true,
	height : 150,
	width : 300,
	buttonAlign:'center',
	title : '车辆类型',
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
			emptyText : '填写类型名称。'
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
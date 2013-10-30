Ext.define('VMS.view.Edit',{
	extend:'Ext.window.Window',
	xtype: 'Edit',
	title:'修改节点信息',
	height: 200,
	width: 300,
	layout: 'fit',
	items:[{
		xtype:'form',
		border: false,
		bodyPadding: 0,
		layout: {
            type: 'vbox',
            align: 'center',
            pack: 'center'
        },
		items:[{
			xtype: 'textfield',
			fieldLabel: '节点名称',
			name: 'node',
			allowBlank: false,
			width: 250,
			labelWidth: 70
		}],
		bbar:[{
			text: '确定',
			action: 'submit'
		},{
			text: '取消',
			handler:function(me,e){
				me.up().up().up().hide();
			}
		}]
	}]
});
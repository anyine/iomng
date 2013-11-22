Ext.define('VMS.controller.LeaveCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'leave.List', 'leave.Edit' ],
	stores : [ 'LeaveStore' ],
	models : [ 'Leave' ],

	init : function() {
		// console.log('leave controller init...');
		
		this.control({
			'leavelist button[action=add]' : {
				click : this.onAdd
			},
			'leaveedit button[action=save]' : {
				click : this.onSave
			},
			'leavelist' : {
				itemdblclick : this.onItemdblclick,
				itemagreeclick:this.onAgree,
				itemnoagreeclick:this.onNoAgree
			}
		});
	},
	onAgree:function(view, rowIndex, colIndexitem, item,e, record){
		console.log(record.getId()+"agree ");
		
	},
	onNoAgree:function(view, rowIndex, colIndexitem, item,e, record){
		console.log(record.getId()+"no agree");
	},
	onAdd : function(me, e, eo) {
		// console.log('leave add');
		var win = Ext.widget('leaveedit');
		win.show();
	},
	onSave : function(me, e, eo) {
		// console.log('leave save');
		var store = this.getLeaveStoreStore();
		var win = me.up('window');
		var form = win.down('form').getForm();
		// var v=form.getForm().getValues();
		// console.log(v);
		if (form.isValid()) {
			Ext.Msg.show({
				title : '提示',
				msg : '请求提交后不可修改、不可删除，请仔细核查申请信息是否填写准确。核查无误后点击提交按钮。',
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.WARNING,
				fn : function(button, text, opt) {
					if ('yes' == button) {
						form.submit({
							method : "post",
							url : 'leave/add',
							success : function(form, action) {
								Ext.example.msg('提示', '提交成功。');
								store.reload();
								win.close();
							},
							failure : function(form, action) {
								var result = Ext.JSON
										.decode(action.response.responseText);
								Ext.Msg.alert('提示', result.message);
							}
						});
					}
				}
			});
		}

	},
	onItemdblclick : function(me, record, item, index, e, eOpts) {
		console.log('leave itemdblclick');
	}

});
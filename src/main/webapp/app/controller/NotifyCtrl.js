Ext.define('VMS.controller.NotifyCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'notify.List','notify.Edit' ],
	stores : [ 'NotifyStore' ],
	models : [ 'Notify' ],

	init : function() {
		console.log('notify controller init...');
		this.control({
			'notifylist actioncolumn' : {
				click : this.onDelete
			},
			'notifylist button[action=add]' : {
				click : this.onAdd
			},
			'notifyedit button[action=save]' : {
				click : this.onSave
			},
			'notifylist' : {
				itemdblclick : this.onItemdblclick
			}
		});
	},
	onDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getNotifyStoreStore();
		Ext.Msg.show({
			title : '警告',
			msg : '该操作需要清除所有对该项的引用，您确认清除了吗？',
			buttons : Ext.Msg.YESNO,
			icon : Ext.Msg.WARNING,
			animateTarget : row,
			fn : function(button, text, opt) {
				if ('yes' == button) {
					store.remove(rec);
					store.sync({
						success : function(response, o) {
							Ext.example.msg('提示', '删除成功。');
						},
						failure : function(response, o) {
							Ext.Msg.alert('提示', "删除失败");
							store.reload();
						}
					});
				}
			}
		});

	},
	onAdd : function(me, e, eo) {
		var edit = Ext.widget("notifyedit");
		edit.show();
	},
	onSave : function(me, e, eo) {
		var win = me.up('window');
		var form = win.down('form');
		if (form.isValid()) {
			var store = this.getNotifyStoreStore();
			var values = form.getValues();
			var record = form.getRecord();
			//console.log(values);
			if (record) {
				// 更新
				for(p in values){
					record.set(p,values[p]);
				}
				store.sync({
					success : function(b, o) {
						Ext.example.msg('提示', '更新成功。');
						store.reload();
						win.close();
					},
					failure : function(response, o) {
						Ext.Msg.alert('提示', "更新失败");
						store.reload();
					}
				});
			} else {
				//添加
				store.add(values);
				store.sync({
					success : function(b, o) {
						Ext.example.msg('提示', '添加成功。');
						store.reload();
						win.close();
					},
					failure : function(response, o) {
						consoler.log(response);
						Ext.Msg.alert('提示', "添加失败");
						store.reload();
					}
				});
			}
		}
	},
	onItemdblclick : function(me, record, item, index, e, eOpts) {
		var edit = Ext.widget("notifyedit");
		edit.show();
		var form = edit.down('form').getForm();
		form.loadRecord(record);
	}

});
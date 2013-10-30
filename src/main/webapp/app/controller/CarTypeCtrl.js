Ext.define('VMS.controller.CarTypeCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'carType.List', 'carType.Edit' ],
	stores : [ 'CarTypeStore' ],
	models : [ 'CarType' ],

	init : function() {
		this.control({
			'cartypelist actioncolumn' : {
				click : this.onDelete
			},
			'cartypelist button[action=add]' : {
				click : this.onAdd
			},
			'cartypeedit button[action=save]' : {
				click : this.onSave
			},
			'cartypelist' : {
				itemdblclick : this.onItemdblclick
			}
		});
	},
	onDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getCarTypeStoreStore();
		Ext.Msg.show({
			title : '警告',
			msg : '该操作会将属于该类型的车辆信息删除，确认要删除吗？',
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
		var edit = Ext.widget("cartypeedit");
		edit.show();
	},
	onSave : function(me, e, eo) {
		var win = me.up('window');
		var form = win.down('form');
		if (form.isValid()) {
			var store = this.getCarTypeStoreStore();
			var values = form.getValues();
			var record = form.getRecord();
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
						Ext.Msg.alert('提示', "添加失败");
						store.reload();
					}
				});
			}
		}
	},
	onItemdblclick : function(me, record, item, index, e, eOpts) {
		var edit = Ext.widget("cartypeedit");
		edit.show();
		var form = edit.down('form').getForm();
		form.loadRecord(record);
	}

});
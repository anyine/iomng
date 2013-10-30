Ext.define('VMS.controller.DoorCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'door.List', 'door.Edit', 'door.ControllerList',
			'door.ReaderList' ],
	stores : [ 'DoorStore', 'ControllerStore', 'ReaderStore' ],
	models : [ 'Door' ],
	refs : [ {
		ref : 'addReaderBtn',
		selector : 'readerlist button[action=add]'
	} ],
	init : function() {
		this.control({
			'doorlist actioncolumn' : {
				click : this.onDelete
			},
			'controllerlist actioncolumn' : {
				click : this.onControllerDelete
			},
			'readerlist actioncolumn' : {
				click : this.onReaderDelete
			},
			'doorlist button[action=add]' : {
				click : this.onAdd
			},
			'dooredit button[action=save]' : {
				click : this.onSave
			},
			'dooredit button[action=close]' : {
				click : this.onClose
			},
			'doorlist' : {
				itemdblclick : this.onItemdblclick
			},
			'controllerlist' : {
				select : this.onSelect
			},
			'controllerlist button[action=add]' : {
				click : this.onAddController
			},
			'readerlist button[action=add]' : {
				click : this.onAddReader
			}
		});
	},
	onClose : function(me, e, eo) {
		me.up('window').close();
		delete this.loaded;
	},
	onSelect : function(me, rec, index, eOpts) {
		var form = me.view.up('window').down('form').getForm();
		var readerStore = this.getReaderStoreStore();
		readerStore.clearFilter(true);
		var record = form.getRecord();
		if (!this.loaded) {
			this.loaded = {};
		}
		console.log(this.loaded);
		if (record) {
			if (!this.loaded[rec.getId()]) {
				// 更新，加载一次
				readerStore.load({
					addRecords : true,// 追加
					params : {
						controllerId : rec.getId()
					}
				});
				this.loaded[rec.getId()] = true;
			}
		}
		readerStore.filter("sn", rec.get('sn'));
	},
	onAddReader : function(me, e, eo) {
		var controllerSm = me.up("window").down('controllerlist')
				.getSelectionModel();
		if (controllerSm.getCount() == 0) {
			Ext.Msg.alert('提示', '请选择一个控制器。');
			return;
		}
		var controller = controllerSm.getSelection()[0];
		var rowEditing = me.up('grid').getPlugin('rowplugin');
		var store = this.getReaderStoreStore();
		rowEditing.cancelEdit();
		var r = Ext.create('VMS.model.Reader', {
			sn : controller.get('sn'),
			number : 1,
			gateNumber : 1,
			type : 'OUT'
		});
		store.insert(0, r);
		rowEditing.startEdit(0, 0);
	},
	onAddController : function(me, e, eo) {
		var rowEditing = me.up('grid').getPlugin('rowplugin');
		var store = this.getControllerStoreStore();
		rowEditing.cancelEdit();
		var r = Ext.create('VMS.model.Controller', {
			sn : '000000',
			ip : '0.0.0.0'
		});
		store.insert(0, r);
		rowEditing.startEdit(0, 0);
	},
	onControllerDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getControllerStoreStore();
		store.remove(rec);
	},
	onReaderDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getReaderStoreStore();
		store.remove(rec);
	},
	onDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getDoorStoreStore();
		Ext.Msg.show({
			title : '警告',
			msg : '确认要删除吗？',
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
		this.getControllerStoreStore().removeAll();
		this.getReaderStoreStore().removeAll();
		var edit = Ext.widget('dooredit');
		edit.show();
	},
	onSave : function(me, e, eo) {
		var store = this.getDoorStoreStore();
		var win = me.up('window');
		var controllerStore = this.getControllerStoreStore();
		var readerStore = this.getReaderStoreStore();
		var params = {};
		var controllerN = 0;
		controllerStore.each(function() {
			if (this.getId() != 0)
				params['controllers[' + controllerN + '].id'] = this.getId();

			params['controllers[' + controllerN + '].sn'] = this.get('sn');
			params['controllers[' + controllerN + '].ip'] = this.get('ip');
			readerStore.clearFilter(true);
			readerStore.filter("sn", this.get('sn'));

			var readerN = 0;
			readerStore.each(function() {
				if (this.getId() != 0)
					params['controllers[' + controllerN + '].readers['
							+ readerN + '].id'] = this.getId();
				params['controllers[' + controllerN + '].readers[' + readerN
						+ '].number'] = this.get('number');
				params['controllers[' + controllerN + '].readers[' + readerN
						+ '].gateNumber'] = this.get('gateNumber');
				params['controllers[' + controllerN + '].readers[' + readerN
						+ '].type'] = this.get('type');
				readerN++;
			});
			controllerN++;

		});
		var form = win.down('form').getForm();
		var record = form.getRecord();
		if (record) {
			// 更新
			params['id']=record.getId();
			form.submit({
				clientValidation : true,
				url : 'door/update',
				method : 'post',
				params : params,
				success : function(form, action) {
					Ext.example.msg('提示', '更新成功。');
					store.reload();
					win.close();
				},
				failure : function(form, action) {
					var result = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert('提示', result.message);
				}
			});
		} else {
			//添加
			form.submit({
				clientValidation : true,
				url : 'door/add',
				method : 'post',
				params : params,
				success : function(form, action) {
					Ext.example.msg('提示', '添加成功。');
					store.reload();
					win.close();
				},
				failure : function(form, action) {
					var result = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert('提示', result.message);
				}
			});
		}
		delete this.loaded;
	},
	onItemdblclick : function(me, record, item, index, e, eOpts) {
		var controllerStore = this.getControllerStoreStore();
		controllerStore.removeAll();
		var readerStore = this.getReaderStoreStore();
		readerStore.removeAll();
		var edit = Ext.widget("dooredit");
		edit.show();
		var form = edit.down('form').getForm();
		form.loadRecord(record);
		controllerStore.load({
			params : {
				doorId : record.getId()
			}
		});
	}
});
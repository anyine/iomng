Ext.define('VMS.controller.InnerCarCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'innerCar.List' ,'innerCar.Edit'],
	stores : [ 'InnerCarStore','OrganizationStore' ],
	models : [ 'InnerCar' ],

	init : function() {
		this.control({
			'innercarlist button[action=add]' : {
				click : this.onAdd
			},
			'innercaredit button[action=save]':{
				click:this.onSave
			},
			'innercarlist actioncolumn' : {
				click : this.onDelete
			},
			'innercarlist' : {
				itemdblclick : this.onItemdblclick
			}
		});
	},
	onDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getInnerCarStoreStore();
		Ext.Msg.show({
			title : '警告',
			msg : '该操作也会删除该车辆相关的出入记录信息，确认要删除吗？',
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
	onAdd:function(me,e,eo){
		var edit=Ext.widget('innercaredit');
		edit.show();
	},
	onSave:function(me,e,eo){
		var win=me.up('window');
		var form=win.down('form').getForm();
		var store=this.getInnerCarStoreStore();
		var record = form.getRecord();
		if(record){
			//更新
			form.submit({
				clientValidation : true,
				url : 'innerCar/update',
				method : 'post',
				params : {
					id : record.getId(),
					'card.id':record.get('card.id')
				},
				success : function(form, action) {
					Ext.example.msg('提示', '更新成功。');
					store.reload();
					win.close();
				},
				failure : function(form, action) {
					var result = Ext.JSON
							.decode(action.response.responseText);
					Ext.Msg.alert('提示', result.message);
				}
			});
		}else{
			//添加
			form.submit({
				clientValidation:true,
				url:'innerCar/add',
				method:'post',
				 success: function(form, action) {
					 Ext.example.msg('提示', '添加成功。');
					 store.reload();
					 win.close();
				    },
				    failure: function(form, action){
				    	var result = Ext.JSON
						.decode(action.response.responseText);
				Ext.Msg.alert('提示', result.message);
				    }
			});
		}
	
	},
	onItemdblclick : function(me, record, item, index, e, eOpts) {
		var edit = Ext.widget("innercaredit");
		edit.show();
		var form = edit.down('form').getForm();
		form.loadRecord(record);
	}
});
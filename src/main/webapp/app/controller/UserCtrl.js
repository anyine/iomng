Ext.define('VMS.controller.UserCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'user.List', 'user.Edit','user.PermissionEdit' ],
	stores : [ 'UserStore','OrganizationStore' ],
	models : [ 'User' ],

	init : function() {
		this.control({
			'userlist actioncolumn' : {
				click : this.onDelete
			},
			'userlist button[action=add]' : {
				click : this.onAdd
			},'userlist button[action=permission]' : {
				click : this.onPermission
			},
			'useredit button[action=save]' : {
				click : this.onSave
			},
			'permissionedit button[action=save]' : {
				click : this.onpermissionSave
			},
			'userlist' : {
				itemdblclick : this.onItemdblclick
			},
			'permissionedit':{
				show:this.showInit
			}
		});
	},
	showInit:function(me,e){
		var f=me.down('itemselector');
		Ext.Ajax.request({
		    url: 'user/getMenuPermissionId',
		    async : false,
		    params: {
		        id: me.user.get('id')
		    },
		    success: function(response){
		        var text = response.responseText;
		        var r=Ext.decode(text);
		        f.setValue(r.ids);
		    }
		});
		
	},
	onAdd : function(me, e, eo) {
		var edit = Ext.widget("useredit");
		edit.show();
	},
	onPermission:function(me,e,o){
		var list=me.up("userlist");
		var users=list.getSelectionModel().getSelection();
		if(users.length){
			var user=users[0];
			var edit = Ext.widget("permissionedit",{user:user});
			edit.show();
			edit.setTitle("授权-"+user.get('realname'));
			var form = edit.down('form').getForm();
			form.loadRecord(user);
			
		}else{
			Ext.Msg.alert('提示','请选择要授权的对象。');
		}
		
	},
	onDelete : function(v, ri, ci, i, e, rec, row) {
		var store = this.getUserStoreStore();
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
onpermissionSave:function(me,e,eo){
	var win = me.up('window');
	var form = win.down('form');
	form.submit({
		url : 'user/permission',
		method : 'post',
		success : function(form, action) {
			Ext.example.msg('提示', '授权成功。');
			win.close();
		},
		failure : function(form, action) {
			var result = Ext.JSON
					.decode(action.response.responseText);
			Ext.Msg.alert('提示', result.message);
		}
	});
	},
	onSave : function(me, e, eo) {
		var win = me.up('window');
		var form = win.down('form');
		if (form.isValid()) {
			var store = this.getUserStoreStore();
			var values = form.getValues();
			var record = form.getRecord();
			if (record) {

				form.submit({
					clientValidation : true,
					url : 'user/update',
					method : 'post',
					params : {
						id : record.getId()
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

			} else {
				form.submit({
					clientValidation : true,
					url : 'user/add',
					method : 'post',
					success : function(form, action) {
						Ext.example.msg('提示', '添加成功。');
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
	},
	onItemdblclick : function(me, record, item, index, e, eOpts) {
		var edit = Ext.widget("useredit");
		edit.show();
		var form = edit.down('form').getForm();
		form.loadRecord(record);
	}

});
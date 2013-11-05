Ext.define('VMS.controller.OrganizationCtrl', {
	extend : 'Ext.app.Controller',
	views : [ 'organization.Tree', 'organization.Edit' ],
	stores : [ 'OrganizationStore' ],

	init : function() {
		this.control({
			'organizationtree' : {
				'itemcontextmenu' : this.showContextMenu
			},
			'organizationmenu [action=edit]' : {
				'click' : this.editNode
			},
			'organizationmenu [action=add]' : {
				'click' : this.addNode
			},
			'organizationmenu [action=del]' : {
				'click' : this.delNode
			},
			'organizationedit [action=save]' : {
				'click' : this.saveNodeText
			}
		});
	},
	showContextMenu : function(me, rec, item, index, e) {
		var menu = Ext.create('VMS.view.organization.Menu', {
			node : rec
		});
		e.preventDefault();
		e.stopEvent();
		menu.showAt(e.getXY());
	},

	saveNodeText : function(me, e) {
		var win = me.up('window');
		var form = win.down('form').getForm();
		var store = this.getOrganizationStoreStore();
		node = win.node;
		var tree = node.getOwnerTree();
		value = form.findField('name').getValue();
		if (win.isUpdate) {
			form.submit({
				clientValidation : true,
				url : 'organization/update',
				method : 'post',
				params : {
					id : node.getId(),
					'parent.id' : node.parentNode.getId()
				},
				success : function(form, action) {
					Ext.example.msg('提示', '更新成功。');
					store
							.reload({
								callback : function() {
									tree.expandNode(store.getNodeById(node
											.getId()).parentNode);
								}
							});
					win.close();
				},
				failure : function(form, action) {
					var result = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert('提示', result.message);
				}
			});
		} else {
			form.submit({
				clientValidation : true,
				url : 'organization/add',
				method : 'post',
				params : {
					'parent.id' : node.getId()
				},
				success : function(form, action) {
					Ext.example.msg('提示', '添加成功。');
					store.reload({
						callback : function(records) {
							tree.expandAll();
						}
					});
					win.close();
				},
				failure : function(form, action) {
					var result = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert('提示', result.message);
				}
			});
		}
	},

	editNode : function(me, e) {
		var node = me.up().node;
		edit = Ext.create('VMS.view.organization.Edit', {
			node : node,
			isUpdate : true
		});
		edit.down('form').getForm().findField('name')
				.setValue(node.get('text'));
		edit.down('form').getForm().findField('index').setValue(
				node.get('index'));
		edit.show();
	},

	addNode : function(me, e) {
		var node = me.up().node;
		// 继续在页面之间传递 操作节点的信息
		edit = Ext.create('VMS.view.organization.Edit', {
			node : node,
			title : '在' + node.get('text') + '增加'
		});
		edit.show();
	},

	delNode : function(me, e) {
		var store = this.getOrganizationStoreStore();
		var node = me.up().node;
		var tree = node.getOwnerTree();
		Ext.Msg.show({
			title : '警告',
			msg : '该操作也会删除该机构下所有子机构，确认要删除吗？',
			buttons : Ext.Msg.YESNO,
			icon : Ext.Msg.WARNING,
			fn : function(button, text, opt) {
				if ('yes' == button) {
					Ext.Ajax.request({
						url : 'organization/delete',
						method : 'post',
						params : {
							id : node.getId()
						},
						success : function(response) {
							Ext.example.msg('提示', '删除成功。');
							store.reload({
								callback : function(records) {
									tree.expandAll();
								}
							});
						}
					});
				}
			}
		});
	}

});
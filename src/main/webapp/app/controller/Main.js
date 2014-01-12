Ext.define('VMS.controller.Main', {
	extend : 'Ext.app.Controller',
	views : [ 'Viewport', 'Header', 'Footer', 'Tree', 'Content' ],
	stores : [ 'TreeStore' ],
	models : [ 'InnerCar' ],
	refs : [ {
		ref : 'pageContent',
		selector : 'viewport > #pageContent'
	} ],

	init : function() {
		// 页面载入成功后去除loadmask
		this.onLaunch = function(app) {
			var loadingMask = Ext.get("loading");
			// loadingMask.fadeOut({
			// opacity : 0,
			// easing : 'easeOut',
			// duration : 500,
			// remove : true,
			// useDisplay : false
			// });
			loadingMask.slideOut('b', {
				easing : 'easeOut',
				duration : 500,
				remove : true,
				useDisplay : false
			});
		};
		this.control({
			'#changePwd' : {
				'click' : this.onChangePwd
			},
			'#logout' : {
				'click' : this.onLogout
			},
			'#treemenu' : {
				'select' : this.changePage
			},
			'viewport grid' : {
				'show' : this.onGridShow
			}
		});
	},
	onChangePwd : function() {
		var win = Ext.create("Ext.Window", {
			title : '修改密码',
			width : 350,
			items : [ {
				xtype : 'form',
				items : [
						{
							xtype : 'textfield',
							name : 'oldPassword',
							fieldLabel : '原密码',
							inputType : 'password',
							style : 'margin-top:15px',
							allowBlank : false
						},
						{
							xtype : 'textfield',
							name : 'newPassword',
							fieldLabel : '新密码',
							inputType : 'password',
							allowBlank : false
						} ],
				buttons:[{
					text:'提交',
						handler:function(){
						var win=this.up('window');
						var form=this.up('form').getForm();
						form.submit({
							 clientValidation: true,
							 method:"post",
							    url: 'changePassword',
							    success: function(form, action) {
							        Ext.Msg.alert('修改成功', action.result.msg);
							        window.close();
							     },
							     
							     failure: function(form, action) {
							    	 Ext.Msg.alert('修改失败', action.result.msg);
							     }
							
						});
						}
				},{
					text:'取消',
					handler:function(){
						this.up('window').close();
					}
				}]
			} ]
		});
		win.show();
	},
	onLogout : function() {
		window.location.href = 'logout';
	},
	onGridShow : function(me, eo) {
		me.getStore().load();
	},
	// 点击树形节点 切换页面
	changePage : function(me, rec, index, e) {
		// 如果点击的节点为非叶子节点，则不作任何操作
		if (!rec.isLeaf()) {
			return;
		}

		var id = rec.get('id');
		console.log('menu--' + id);
		var title = rec.get('text');
		var pageContent = this.getPageContent();
		var tab = pageContent.getComponent(id);
		if (!tab) {
			pageContent.add({
				id : id,
				xtype : id,
				title : title,
				closable : true,
				closeAction : 'destory',
				iconCls : id
			}).show();
		} else {
			pageContent.setActiveTab(tab);
		}

	}
});
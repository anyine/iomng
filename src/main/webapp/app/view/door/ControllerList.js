Ext.define('VMS.view.door.ControllerList',{
					extend : 'Ext.grid.Panel',
					alias : 'widget.controllerlist',
					store : 'ControllerStore',
					plugins : [ {
						ptype : 'rowediting',
						saveBtnText: '保存',  
			            cancelBtnText: "取消", 
						clicksToEdit : 2,
						pluginId : 'rowplugin'
					} ],
					columns : [							
							{
								text : 'SN',
								allowBlank : false,
								dataIndex : 'sn',
								editor:'textfield'
							},
							{
								text : 'IP',
								allowBlank : false,
								dataIndex : 'ip',
								editor:'textfield'
								
							},
							{
								xtype : 'actioncolumn',
								width:20,
								items : [ {
									icon : 'ext/resources/ext-theme-neptune/images/shared/icon-error.png',
									tooltip : '删除'
								} ]
							} ],
					tbar : [ {
						text : '添加控制器',
						action : 'add'
					} ]
				});
Ext.define('VMS.view.door.ReaderList',	{
					extend : 'Ext.grid.Panel',
					alias : 'widget.readerlist',
					store : 'ReaderStore',
					plugins : [ {
						ptype : 'rowediting',
						saveBtnText: '保存',  
			            cancelBtnText: "取消", 
						clicksToEdit : 2,
						pluginId : 'rowplugin'
					} ],
					columns : [
							{
								text : '控制器SN',
								dataIndex : 'sn'
							},
							{
								text : '读卡器编号',
								dataIndex : 'number',
								editor:{
									 xtype: 'numberfield',
								        value: 1,
								        maxValue: 8,
								        minValue: 1
								},
								allowBlank : false
							},
							{
								text : '控制道闸编号',
								editor:{
									 xtype: 'numberfield',
								        value: 1,
								        maxValue: 8,
								        minValue: 1
								},
								dataIndex : 'gateNumber'
							},{
								text : '读卡类型',
								dataIndex : 'type',
								editor:{
									xtype:'combo',
									 store:  Ext.create('Ext.data.Store', {
										    fields: ['id', 'name'],
										    data : [
										        {"id":"IN", "name":"进门"},
										        {"id":"OUT", "name":"出门"},
										    ]
										}),
									    queryMode: 'local',
									    displayField: 'name',
									    valueField: 'id'
								},
								renderer:function(v){
									if('IN'==v){
										return '进门';
									}else if('OUT'==v){
										return '出门';
									}else {
										return v;
									}
								}
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
						text : '添加读卡器',
						action : 'add'
					} ]
				});
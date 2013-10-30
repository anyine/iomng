Ext.define('VMS.view.innerCarRecord.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.innercarrecordlist',
	title : '内部车辆出入记录列表',
	store : 'InnerCarRecordStore',
	columns : [ {
		xtype : 'rownumberer'
	},{
		text : '类型',
		dataIndex : 'type',
		renderer:function(v){
			if('OUT'==v){
				return '外出';
			}else if('IN'==v){
				return '进入';
			}else{
				return v;
			}
		}
	}, {
		text : '车辆',
		dataIndex : 'car.license'
	}, {
		text : '时间',
		dataIndex : 'date',
		width:200,
		xtype: 'datecolumn',
		format:'y-m-d l H:i:s'
	}, {
		text : '状态',
		dataIndex : 'status',
		renderer:function(v){
			if('normal'==v){
				return '正常';
			}else if('waring'==v){
				return '警告';
			}else{
				return v;
			}
		}
		
	} ],
	tbar : [ {
		text : '新增',
		action : 'add'
	}]
});
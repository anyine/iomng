Ext.define('VMS.view.innerPersonRecord.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.innerpersonrecordlist',
	title : '内部人员出入记录列表',
	store : 'InnerPersonRecordStore',

	viewConfig : {
		forceFit : true,
		getRowClass : function(record, rowIndex, rowParams, store) {
			var type=record.get('type');
			if(record.get('status')=='warning'){
				return 'waring-row';
			}else if('IN'==type){
				return 'in-row';
			}else if('OUT'==type){
				return 'out-row';
			}else{
				return ;
			}
		}
	},
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : '类型',
		dataIndex : 'type',
		renderer : function(v) {
			if ('OUT' == v) {
				return '外出';
			} else if ('IN' == v) {
				return '进入';
			} else {
				return v;
			}
		}
	}, {
		text : '人员',
		dataIndex : 'person.name'
	}, {
		text : '时间',
		dataIndex : 'date',
		width : 200,
		xtype : 'datecolumn',
		format : 'y-m-d l H:i:s'
	}, {
		text : '状态',
		dataIndex : 'status',
		renderer : function(v) {
			if ('normal' == v) {
				return '正常';
			} else if ('warning' == v) {
				return '警告';
			} else {
				return v;
			}
		}
	} ],
	tbar : [{
		xtype : 'form',
		border : false,
		layout: 'hbox',
		defaults : {
			labelWidth : 60
		},
		items : [{
			xtype : 'combo',
			store :'InnerPersonRecordStore',
			displayField : 'person.name',
			fieldLabel :'人员',
			queryMode: 'local',
			enableRegEx : true,
			hideTrigger : true,
			typeAhead : true,
			minChars : 3
		},{
			xtype:'datetimefield',
            fieldLabel: '起止时间',
            name: 'startdt',
            itemId: 'startdt',
//            vtype: 'daterange',
            endDateField: 'enddt' 
        }, {
        	xtype:'datetimefield',
            fieldLabel: '截止时间',
            name: 'enddt',
            itemId: 'enddt',
//            vtype: 'daterange',
            startDateField: 'startdt' 
        }]
	},{
		text:'重置',
		action:'reset'
	}]/*,
		dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'InnerPersonRecordStore',  
        dock: 'bottom',
        displayInfo: true
    }]*/
});
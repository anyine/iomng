Ext.define('VMS.model.Notify',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'int'},
	        {name:'name',type:'varchar'},
	        {name:'startTime',type:'date',dateReadFormat : 'H:i:s',dateWriteFormat : 'c'},
	        {name:'endTime',type:'date',dateReadFormat : 'H:i:s',dateWriteFormat :'c'}
	        ],
	        idProperty:'id'
});
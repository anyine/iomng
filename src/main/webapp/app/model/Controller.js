Ext.define('VMS.model.Controller',{
	extend:'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'sn',
		type : 'varchar'
	}, {
		name : 'ip',
		type : 'varchar'
	} ],
	        idProperty:'id'
});
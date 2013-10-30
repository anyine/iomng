Ext.define('VMS.model.Reader', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'sn',
		type : 'varchar'
	}, {
		name : 'number',
		type : 'varchar'
	}, {
		name : 'gateNumber',
		type : 'varchar'
	}, {
		name : 'type',
		type : 'varchar'
	} ],
	idProperty : 'id'
});
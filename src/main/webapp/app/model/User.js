Ext.define('VMS.model.User', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'username',
		type : 'varchar'
	}, {
		name : 'organization.id',
		type : 'int'
	},{
		name : 'organization.name',
		type : 'varchar'
	}, {
		name : 'realname',
		type : 'varchar'
	}, {
		name : 'email',
		type : 'varchar'
	}, {
		name : 'phone',
		type : 'varchar'
	}, {
		name : 'status',
		type : 'varchar'
	} ],
	validations : [ {
		type : 'email',
		field : 'email'
	} ],
	idProperty : 'id'
});
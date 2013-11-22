Ext.define('VMS.model.InnerPerson', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'long'
	}, {
		name : 'name',
		type : 'varchar'
	}, {
		name : 'phone',
		type : 'varchar'
	}, {
		name : 'card.id',
		type : 'int'
	}, {
		name : 'card.number',
		type : 'varchar'
	}, {
		name : 'organization.id',
		type : 'varchar'
	}, {
		name : 'organization.name',
		type : 'varchar'
	}, {
		name : 'certificate',
		type : 'varchar'
	}, {
		name : 'userIds'
	}, {
		name : 'notify.id',
		type : 'int'
	}, {
		name : 'notify.name',
		type : 'varchar'
	} ],
	idProperty : 'id'

});
Ext.define('VMS.model.InnerPerson', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'long'
	}, {
		name : 'name',
		type : 'varchar'
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
	} ],
	idProperty : 'id',

});
Ext.define('VMS.store.DoorStore', {
	extend : 'Ext.data.Store',
	model : 'VMS.model.Door',
	//autoLoad : true,
	proxy : {
		type : 'ajax',
		api : {
			read : 'door',
			destroy : 'door/delete'
		},
		reader : 'json',
		writer : {
			type : 'json',
			writeAllFields : false
		}
	}
});

Ext.define('VMS.store.InnerPersonRecordStore', {
	extend : 'Ext.data.Store',
	model : 'VMS.model.InnerPersonRecord',
	autoLoad : true,
	storeId : 'innerCarRecordStore',
	sorters : [ {
		property : 'date',
		direction : 'DESC'
	} ],
	proxy : {
		type : 'ajax',
		api : {
			read : 'record/innerPersonRecords'
		},
		reader : 'json'
	}
});

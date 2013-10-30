Ext.define('VMS.store.ReaderStore', {
	extend : 'Ext.data.Store',
	model:'VMS.model.Reader',
	proxy : {
		//需要传参数 controllerId
		type : 'ajax',
		url : 'door/getReaders',
		reader : 'json'
	}
});

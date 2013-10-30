Ext.define('VMS.store.ControllerStore', {
	extend : 'Ext.data.Store',
	model:'VMS.model.Controller',
	proxy : {
		//需要传参数 doorId
		type : 'ajax',
		url : 'door/getControllers',
		reader : 'json'
	}
});

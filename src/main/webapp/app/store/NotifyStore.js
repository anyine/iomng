Ext.define('VMS.store.NotifyStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.Notify',
    autoLoad:true,
    proxy:{
    	type:'ajax',
    	api:{
    		create:'notify/add',
    		read:'notify',
    		update:'notify/update',
    		destroy:'notify/delete'
    	},
    	reader:'json'
    }
});

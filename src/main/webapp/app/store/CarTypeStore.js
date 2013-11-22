Ext.define('VMS.store.CarTypeStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.CarType',
    //autoLoad:true,
    storeId:'carTypeStore',
    proxy:{
    	type:'ajax',
    	api:{
    		create:'carType/add',
    		read:'carType',
    		update:'carType/update',
    		destroy:'carType/delete'
    	},
    	reader:'json'
    }
});
